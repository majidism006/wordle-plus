import entity.CellResult;
import entity.GuessResult;
import interface_adapter.grid.GridState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GridStateTest {

    private GridState gridState;

    @BeforeEach
    public void setUp() {
        gridState = new GridState();
        gridState.setTargetWord("apple");
    }

    @Test
    public void testCheckGuessCorrect() {
        GuessResult result = gridState.checkGuess("apple");

        assertTrue(result.isCorrect());
        assertEquals(5, result.getCellResults().size());

        for (CellResult cellResult : result.getCellResults()) {
            assertTrue(cellResult.isCorrectPosition());
            assertTrue(cellResult.isCorrectLetter());
        }
    }

    @Test
    public void testCheckGuessIncorrect() {
        GuessResult result = gridState.checkGuess("apric");

        assertFalse(result.isCorrect());
        assertEquals(5, result.getCellResults().size());

        assertTrue(result.getCellResults().get(0).isCorrectPosition());
        assertTrue(result.getCellResults().get(0).isCorrectLetter());

        assertTrue(result.getCellResults().get(1).isCorrectPosition());
        assertTrue(result.getCellResults().get(1).isCorrectLetter());

        assertFalse(result.getCellResults().get(2).isCorrectPosition());
        assertFalse(result.getCellResults().get(2).isCorrectLetter());

        assertFalse(result.getCellResults().get(3).isCorrectPosition());
        assertFalse(result.getCellResults().get(3).isCorrectLetter());

        assertFalse(result.getCellResults().get(4).isCorrectPosition());
        assertFalse(result.getCellResults().get(4).isCorrectLetter());
    }

    @Test
    public void testCheckGuessPartial() {
        GuessResult result = gridState.checkGuess("appla");

        assertFalse(result.isCorrect());
        assertEquals(5, result.getCellResults().size());

        assertTrue(result.getCellResults().get(0).isCorrectPosition());
        assertTrue(result.getCellResults().get(0).isCorrectLetter());

        assertTrue(result.getCellResults().get(1).isCorrectPosition());
        assertTrue(result.getCellResults().get(1).isCorrectLetter());

        assertTrue(result.getCellResults().get(2).isCorrectPosition());
        assertTrue(result.getCellResults().get(2).isCorrectLetter());

        assertTrue(result.getCellResults().get(3).isCorrectPosition());
        assertTrue(result.getCellResults().get(3).isCorrectLetter());

        assertFalse(result.getCellResults().get(4).isCorrectPosition());
        assertTrue(result.getCellResults().get(4).isCorrectLetter());
    }
}