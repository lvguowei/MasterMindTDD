package com.guowei.lv.strategy;

import com.guowei.lv.gameplay.GuessChecker;
import com.guowei.lv.gameplay.Score;

public class RememberingGuessChecker implements GuessChecker {
    @Override
    public boolean shouldTry(String guess) {
        return false;
    }

    @Override
    public void addScore(String guess, Score score) {

    }
}
