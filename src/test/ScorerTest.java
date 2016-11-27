package test;

import com.guowei.lv.strategy.Scorer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScorerTest {

    @Test
    public void withNoMatches_scoreIsNull() throws Exception {
        assertInPosition(0, "AAAA", "BBBB");
    }

    @Test
    public void inPosition() throws Exception {
        assertInPosition(0, "ABCD", "DCBA");
        assertInPosition(1, "ABBB", "ACCC");
        assertInPosition(2, "AABB", "AACC");
        assertInPosition(2, "ABAB", "ACAC");
        assertInPosition(3, "DEEE", "AEEE");
        assertInPosition(3, "BABB", "BCBB");
        assertInPosition(4, "AAAA", "AAAA");
        assertInPosition(1, "ABBB", "AAAA");
    }

    @Test
    public void inCode() throws Exception {
        assertInCode(0, "AAAA", "BBBB");
        assertInCode(0, "ABBB", "ACCC");
        assertInCode(1, "ABBB", "CACC");
        assertInCode(4, "ABCD", "DCBA");
        assertInCode(2, "ABBA", "CAAC");
        assertInCode(3, "ABCD", "CADF");
        assertInCode(0, "ABBB", "AAAA");
        assertInCode(1, "ABBB", "CAAA");
        assertInCode(1, "AABB", "CCAC");
    }

    private void assertInPosition(int inPosition, String code, String guess) {
        assertEquals(inPosition, Scorer.scoreGuess(code, guess).getInPosition());
    }

    private void assertInCode(int inCode, String code, String guess) {
        assertEquals(inCode, Scorer.scoreGuess(code, guess).getInCode());
    }
}
