package test;

import com.guowei.lv.gameplay.GameEngine;
import com.guowei.lv.gameplay.GuessChecker;
import com.guowei.lv.gameplay.Score;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GuesserTest {

    private GuessChecker failingChecker;

    @Before
    public void setUp() throws Exception {
        failingChecker = new GuessChecker() {
            @Override
            public boolean shouldTry(String guess) {
                return false;
            }

            @Override
            public void addScore(String guess, Score score) {
            }
        };
    }

    @Test
    public void whenNoGuessesAreValid_nextGuessIsNil() throws Exception {
        GameEngine.Guesser guesser = new GameEngine.Guesser(failingChecker);
        assertNull(guesser.getNextGuess());
    }

    @Test
    public void whenOnlyOneGuessIsValid_nextGuessIsThatOne() throws Exception {
        GameEngine.Guesser guesser = new GameEngine.Guesser(new SingleChecker("BEEF"));
        assertEquals("BEEF", guesser.getNextGuess());
        assertNull(guesser.getNextGuess());
    }
}

class SingleChecker implements GuessChecker {

    private String validGuess;

    SingleChecker(String validGuess) {
        this.validGuess = validGuess;
    }

    @Override
    public boolean shouldTry(String guess) {
        return guess.equals(validGuess);
    }

    @Override
    public void addScore(String guess, Score score) {

    }
}
