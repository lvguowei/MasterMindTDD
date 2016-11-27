package com.guowei.lv.strategy;

import com.guowei.lv.gameplay.GuessChecker;
import com.guowei.lv.gameplay.Score;

import java.util.ArrayList;
import java.util.List;

public class RememberingGuessChecker implements GuessChecker {

    private List<ScoreRecord> scoreHistory = new ArrayList<>();

    @Override
    public boolean shouldTry(String guess) {
        return isGuessConsistentWithHistory(guess);
    }

    private boolean isGuessConsistentWithHistory(String guess) {
        for (ScoreRecord previous : scoreHistory) {
            if (!Scorer.scoreGuess(guess, previous.guess).equals(previous.score)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void addScore(String guess, Score score) {
        scoreHistory.add(new ScoreRecord(guess, score));
    }

    private class ScoreRecord {
        private String guess;
        private Score score;

        ScoreRecord(String guess, Score score) {
            this.guess = guess;
            this.score = score;
        }
    }
}