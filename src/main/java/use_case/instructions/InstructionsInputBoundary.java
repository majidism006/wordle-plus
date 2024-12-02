package use_case.instructions;

/**
 * Represents the input boundary for instructions use cases.
 */
public interface InstructionsInputBoundary {

    /**
     * Switches to the grid view with the specified random word.
     *
     * @param randomWord the random word to be used in the grid view
     */
    void switchToGridView(String randomWord);

    /**
     * Executes the instructions use case with the given input data.
     *
     * @param instructionsInputData the data to be used for executing the instructions use case
     */
    void execute(InstructionsInputData instructionsInputData);

    /**
     * Returns a random word based on the specified difficulty.
     *
     * @param difficulty the difficulty level for generating the random word
     * @return a random word based on the specified difficulty
     */
    String getRandomWord(String difficulty);

    /**
     * Switches to the discussion board view.
     */
    void switchToDiscussionBoardView();

    /**
     * Switches to the profile view.
     */
    void switchToProfileView();
}
