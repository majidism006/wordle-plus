import entity.CellResult;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CellResultTest {

    @Test
    public void testCellResult() {
        CellResult cellResult = new CellResult('a', true, true);

        assertEquals('a', cellResult.getLetter());
        assertTrue(cellResult.isCorrectPosition());
        assertTrue(cellResult.isCorrectLetter());

        cellResult = new CellResult('b', false, true);

        assertEquals('b', cellResult.getLetter());
        assertFalse(cellResult.isCorrectPosition());
        assertTrue(cellResult.isCorrectLetter());
    }
}