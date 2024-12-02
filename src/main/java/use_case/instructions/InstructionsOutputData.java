package use_case.instructions;

/**
 * Represents the output data for the instructions use case.
 */
public class InstructionsOutputData {

    /**
     * The target word for the instructions use case.
     */
    private final String targetWord;

    /**
     * Constructs an InstructionsOutputData object with the specified target word.
     *
     * @param targetWord the target word for the instructions use case
     */
    public InstructionsOutputData(String targetWord) {
        this.targetWord = targetWord;
    }

    /**
     * Returns the target word for the instructions use case.
     *
     * @return the target word
     */
    public String getTargetWord() {
        return targetWord;
    }
}
