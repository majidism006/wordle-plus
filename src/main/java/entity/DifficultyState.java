package entity;

/**
 * The state for the difficulty for the game.
 */
public class DifficultyState {
    private String difficulty = "easy";

    /**
     * Returns difficulty.
     * @return difficulty string.
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Sets difficulty.
     * @param difficulty difficulty level to set.
     */
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

}
