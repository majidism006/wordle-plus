package entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class GuessResultTest {

    @Test
    public void testGuessResult() {
        GuessResult guessResult = new GuessResult();
        CellResult cellResult1 = new CellResult('a', true, true);
        CellResult cellResult2 = new CellResult('b', false, true);

        guessResult.addCellResult(cellResult1);
        guessResult.addCellResult(cellResult2);

        List<CellResult> cellResults = guessResult.getCellResults();

        assertEquals(2, cellResults.size());
        assertEquals('a', cellResults.get(0).getLetter());
        assertTrue(cellResults.get(0).isCorrectPosition());
        assertTrue(cellResults.get(0).isCorrectLetter());

        assertEquals('b', cellResults.get(1).getLetter());
        assertFalse(cellResults.get(1).isCorrectPosition());
        assertTrue(cellResults.get(1).isCorrectLetter());
    }

    @Test
    public void testIsCorrect() {
        GuessResult guessResult = new GuessResult();
        guessResult.setCorrect(true);
        assertTrue(guessResult.isCorrect());

        guessResult.setCorrect(false);
        assertFalse(guessResult.isCorrect());
    }
}
