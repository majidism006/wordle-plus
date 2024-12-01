package use_case.instructions;

public class InstructionsOutputData {
    private final String targetWord;

    // Constructor to initialize the target word
    public InstructionsOutputData(String targetWord) {
        this.targetWord = targetWord;
    }

    // Getter for the target word
    public String getTargetWord() {
        return targetWord;
    }
}


