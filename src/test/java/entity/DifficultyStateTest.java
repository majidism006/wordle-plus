package entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DifficultyStateTest {

    @Test
    public void testDefaultDifficulty() {
        DifficultyState difficultyState = new DifficultyState();
        assertEquals("easy", difficultyState.getDifficulty(), "Default difficulty should be 'easy'");
    }

    @Test
    public void testSetDifficulty() {
        DifficultyState difficultyState = new DifficultyState();

        // Setting difficulty to "medium"
        difficultyState.setDifficulty("medium");
        assertEquals("medium", difficultyState.getDifficulty(), "Difficulty should be set to 'medium'");

        // Setting difficulty to "hard"
        difficultyState.setDifficulty("hard");
        assertEquals("hard", difficultyState.getDifficulty(), "Difficulty should be set to 'hard'");
    }

}