package com.guowei.lv.gameplay;

public interface Console {

    /**
     * Prompt user for scoring the guess.
     */
    Score scoreGuess(String guess);

    /**
     * Display the game over message.
     */
    void announceGameOver();

    /**
     * Display the winning code.
     */
    void announceWinningCode(String code);

    /**
     * Display how many time the player tried.
     */
    void announceTries(int tries);

    /**
     * Display a error message if the user's scoring is not consistent.
     */
    void announceBadScoring();
}
