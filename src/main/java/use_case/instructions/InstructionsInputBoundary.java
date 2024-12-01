package use_case.instructions;

public interface InstructionsInputBoundary {
    /**
     * Executes the WordleInstructions use case.
     */
    void switchToGridView(String randomWord);

    void execute(InstructionsInputData instructionsInputData);

    String getRandomWord(String difficulty);

    void switchToDiscussionBoardView();

    void switchToProfileView();
}
