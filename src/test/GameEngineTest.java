package test;

import com.guowei.lv.gameplay.Console;
import com.guowei.lv.gameplay.GameEngine;
import com.guowei.lv.gameplay.GuessChecker;
import com.guowei.lv.gameplay.Score;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameEngineTest {

    private MockGuessChecker checker;
    private MockConsole console;

    @Before
    public void setup() throws Exception {
        checker = new MockGuessChecker();
        GameEngine.guessChecker = checker;
    }

    private void runGame(MockConsole mockConsole) {
        console = mockConsole;
        GameEngine.console = console;
        GameEngine engine = new GameEngine();
        engine.play();
    }

    @Test
    public void gotItOnFirstGuess() throws Exception {
        runGame(new FirstTryMockConsole());
        assertTrue(console.announcedGameOver());
    }

    @Test
    public void gotItOnSecondGuess() throws Exception {
        runGame(new SecondTryMockConsole());
        assertTrue(console.announcedGameOver());
        assertEquals("AAAB", console.getWinningCode());
        assertEquals(2, console.getTries());
        assertEquals(1, checker.guessesAdded());
    }
}

class MockGuessChecker implements GuessChecker {

    private int guessesAdded;

    @Override
    public boolean shouldTry(String guess) {
        return true;
    }

    @Override
    public void addScore(String guess, Score score) {
        guessesAdded++;
    }

    public int guessesAdded() {
        return guessesAdded;
    }
}

class FirstTryMockConsole extends MockConsole {

    @Override
    public Score scoreGuess(String guess) {
        return new Score(4, 0);
    }
}

class SecondTryMockConsole extends MockConsole {
    boolean guessed = false;

    @Override
    public Score scoreGuess(String guess) {
        if (guessed) {
            return new Score(4, 0);
        } else {
            guessed = true;
            return new Score(0, 0);
        }
    }
}

class MockConsole implements Console {
    private boolean gameOver;
    private String winningCode;
    private boolean badScoring;
    protected int tries;

    @Override
    public Score scoreGuess(String guess) {
        return null;
    }

    @Override
    public void announceGameOver() {
        gameOver = true;
    }

    @Override
    public void announceWinningCode(String code) {
        this.winningCode = code;
    }

    @Override
    public void announceTries(int tries) {
        this.tries = tries;
    }

    @Override
    public void announceBadScoring() {
        badScoring = true;
    }

    public boolean announcedGameOver() {
        return gameOver;
    }

    public String getWinningCode() {
        return winningCode;
    }

    public int getTries() {
        return tries;
    }
}
