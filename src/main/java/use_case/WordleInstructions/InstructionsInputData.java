package use_case.WordleInstructions;

/**
 * Data transfer object that holds the necessary information for the Instructions Use Case.
 */
public class InstructionsInputData {
    private String difficulty;

    public InstructionsInputData(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}

