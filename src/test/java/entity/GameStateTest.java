package entity;

import interface_adapter.grid.GridState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameStateTest {

    private GameState gameState;
    private StubGridState stubGridState;

    @BeforeEach
    public void setUp() {
        stubGridState = new StubGridState("hello");
        gameState = new GameState(stubGridState);
    }

    @Test
    public void testInitialRemainingAttempts() {
        assertEquals(6, gameState.getRemainingAttempts(), "Initial remaining attempts should be 6");
    }

    @Test
    public void testSetRemainingAttempts() {
        gameState.setRemainingAttempts(4);
        assertEquals(4, gameState.getRemainingAttempts(), "Remaining attempts should be set to 4");
    }

    @Test
    public void testCheckGuessCorrectGuess() {
        GuessResult result = gameState.checkGuess("hello");
        assertTrue(result.isCorrect(), "Guess should be correct when it matches the target word");
    }

    @Test
    public void testCheckGuessIncorrectGuess() {
        GuessResult result = gameState.checkGuess("world");
        assertFalse(result.isCorrect(), "Guess should be incorrect when it does not match the target word");
    }

    @Test
    public void testTargetWordRetrieval() {
        assertEquals("hello", stubGridState.getTargetWord(), "The target word should be 'hello'");
    }
}

class StubGridState extends GridState {

    private final String targetWord;

    public StubGridState(String targetWord) {
        this.targetWord = targetWord;
    }

    @Override
    public String getTargetWord() {
        return targetWord;
    }

    @Override
    public GuessResult checkGuess(String guess) {
        GuessResult result = new GuessResult();
        result.setCorrect(guess.equals(targetWord));
        return result;
    }
}
