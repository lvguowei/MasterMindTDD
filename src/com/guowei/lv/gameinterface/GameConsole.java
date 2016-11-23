package com.guowei.lv.gameinterface;

import com.guowei.lv.gameplay.Console;
import com.guowei.lv.gameplay.Score;

public class GameConsole implements Console {
    @Override
    public Score scoreGuess(String guess) {
        return null;
    }

    @Override
    public void announceGameOver() {

    }

    @Override
    public void announceWinningCode(String code) {

    }

    @Override
    public void announceTries(int tries) {

    }

    @Override
    public void announceBadScoring() {

    }
}
