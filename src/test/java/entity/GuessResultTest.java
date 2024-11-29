package entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class GuessResultTest {

    @Test
    public void testConstructor() {
        GuessResult guessResult = new GuessResult();
        assertFalse(guessResult.isCorrect(), "Default isCorrect should be false");
        assertNotNull(guessResult.getCellResults(), "cellResults should be initialized");
        assertEquals(0, guessResult.getCellResults().size(), "cellResults list should be empty by default");
    }

    @Test
    public void testSetCorrect() {
        GuessResult guessResult = new GuessResult();
        guessResult.setCorrect(true);
        assertTrue(guessResult.isCorrect(), "isCorrect should be true after setting");

        guessResult.setCorrect(false);
        assertFalse(guessResult.isCorrect(), "isCorrect should be false after setting");
    }

    @Test
    public void testAddCellResult() {
        GuessResult guessResult = new GuessResult();
        CellResult cellResult = new CellResult('A', true, false);
        guessResult.addCellResult(cellResult);

        List<CellResult> cellResults = guessResult.getCellResults();
        assertEquals(1, cellResults.size(), "cellResults list should contain one item after adding");
        assertSame(cellResult, cellResults.get(0), "The added CellResult should match the retrieved one");
    }

    @Test
    public void testMultipleCellResults() {
        GuessResult guessResult = new GuessResult();
        CellResult cellResult1 = new CellResult('A', true, false);
        CellResult cellResult2 = new CellResult('B', false, true);

        guessResult.addCellResult(cellResult1);
        guessResult.addCellResult(cellResult2);

        List<CellResult> cellResults = guessResult.getCellResults();
        assertEquals(2, cellResults.size(), "cellResults list should contain two items after adding");
        assertSame(cellResult1, cellResults.get(0), "First added CellResult should match");
        assertSame(cellResult2, cellResults.get(1), "Second added CellResult should match");
    }
}
