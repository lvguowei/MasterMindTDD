package com.guowei.lv.gameplay;

public interface GuessChecker {

    /**
     * Validate a guess generated by guesser.
     */
    boolean shouldTry(String guess);

    /**
     * Keep track of the guess and user's score.
     */
    void addScore(String guess, Score score);
}
