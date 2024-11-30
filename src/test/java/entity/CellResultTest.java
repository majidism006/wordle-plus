package entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CellResultTest {

    @Test
    public void testConstructorAndGetters() {
        CellResult cellResult = new CellResult('a', true, true);
        assertEquals('a', cellResult.getLetter());
        assertTrue(cellResult.isCorrectPosition());
        assertTrue(cellResult.isCorrectLetter());

        cellResult = new CellResult('b', false, true);
        assertEquals('b', cellResult.getLetter());
        assertFalse(cellResult.isCorrectPosition());
        assertTrue(cellResult.isCorrectLetter());

        cellResult = new CellResult('c', true, false);
        assertEquals('c', cellResult.getLetter());
        assertTrue(cellResult.isCorrectPosition());
        assertFalse(cellResult.isCorrectLetter());
    }

    @Test
    public void testEdgeCases() {
        // Testing non-alphabet characters
        CellResult cellResult = new CellResult('1', false, false);
        assertEquals('1', cellResult.getLetter());
        assertFalse(cellResult.isCorrectPosition());
        assertFalse(cellResult.isCorrectLetter());

        cellResult = new CellResult('!', false, true);
        assertEquals('!', cellResult.getLetter());
        assertFalse(cellResult.isCorrectPosition());
        assertTrue(cellResult.isCorrectLetter());
    }

    @Test
    public void testDifferentInputs() {
        CellResult cellResult = new CellResult('Z', true, false);
        assertEquals('Z', cellResult.getLetter());
        assertTrue(cellResult.isCorrectPosition());
        assertFalse(cellResult.isCorrectLetter());

        cellResult = new CellResult('m', false, false);
        assertEquals('m', cellResult.getLetter());
        assertFalse(cellResult.isCorrectPosition());
        assertFalse(cellResult.isCorrectLetter());
    }

    @Test
    public void testConsistency() {
        CellResult cellResult = new CellResult('x', true, true);
        assertEquals('x', cellResult.getLetter());
        assertTrue(cellResult.isCorrectPosition());
        assertTrue(cellResult.isCorrectLetter());
    }
}
