package test;

import com.guowei.lv.gameplay.GameEngine;
import com.guowei.lv.gameplay.Score;
import com.guowei.lv.strategy.RememberingGuessChecker;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RememberingGuessCheckerTest {

    private RememberingGuessChecker checker;

    @Before
    public void setUp() throws Exception {
        checker = new RememberingGuessChecker();
    }

    @Test
    public void withNoGuesses_everyGuessPasses() throws Exception {
        for (int i = 0; i < GameEngine.MAX_CODES; i++) {
            assertTrue(checker.shouldTry(GameEngine.Guesser.makeGuess(i)));
        }
    }

    @Test
    public void anyGuessThatDoesNotMatch_willFailAgain() throws Exception {
        checker.addScore("AAAA", new Score(0, 0));
        checker.addScore("BBBB", new Score(1, 1));
        assertGuesses("~AAAA", "~BBBB");
    }

    @Test
    public void ifThereAreNoAs_onlyGuessesWithAsWillFail() throws Exception {
        checker.addScore("AAAA", new Score(0, 0));
        assertGuesses("~ABCD", "~BBAB", "~CCCA", "BBBB");
    }

    @Test
    public void ifThereAreNoAsOrBs_onlyGuessesWithAsAndBsWillFail() throws Exception {
        checker.addScore("AAAA", new Score(0, 0));
        checker.addScore("BBBB", new Score(0, 0));
        assertGuesses("~CCCA", "~CCCB", "CCCD");
    }

    @Test
    public void ifTheresOneInTheRightPosition_thenOnlyThoseThatHaveJustOneInPositionWillPass() throws Exception {
        checker.addScore("ABCD", new Score(1,0));
        assertGuesses("AEEE", "EBEE", "EECE", "EEED", "~DCBA", "~EEEE", "~ABCD", "~ABEE");
    }

    @Test
    public void ifTheresOneOutOfPosition_thenOnlyThoseThatHaveJustOneInAnotherPositionWillPass() throws Exception {
        checker.addScore("ABCD", new Score(0, 1));
        assertGuesses("EAEE", "BEEE", "ECEE", "DEEE", "EEEA", "~AEEE", "~EECE", "~EEAB");
    }

    @Test
    public void thereIsOneA() throws Exception {
        checker.addScore("AAAA", new Score(1,0));
        assertGuesses("ABBB");
    }

    private void assertGuesses(String... guesses) {
        for (String guess : guesses) {
            if (guess.startsWith("~")) {
                String myGuess = guess.substring(1);
                assertFalse(guess, checker.shouldTry(myGuess));
            } else {
                assertTrue(guess, checker.shouldTry(guess));
            }
        }
    }

}
