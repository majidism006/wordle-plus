package use_case.instructions;

/**
 * Data transfer object that holds the necessary information for the Instructions Use Case.
 */
public class InstructionsInputData {

    /**
     * The difficulty level for the instructions use case.
     */
    private String difficulty;

    /**
     * Constructs an InstructionsInputData object with the specified difficulty.
     *
     * @param difficulty the difficulty level for the instructions use case
     */
    public InstructionsInputData(String difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Returns the difficulty level for the instructions use case.
     *
     * @return the difficulty level
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Sets the difficulty level for the instructions use case.
     *
     * @param difficulty the difficulty level to set
     */
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
