package use_case.WordleInstructions;

public interface InstructionsInputBoundary {
    /**
     * Executes the WordleInstructions use case.
     */
    void switchToGridView(String randomWord);

    void execute(InstructionsInputData instructionsInputData);

    String getRandomWord(String difficulty);
}
