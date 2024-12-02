package use_case.instructions;

/**
 * Represents the output boundary for instructions use cases.
 */
public interface InstructionsOutputBoundary {

    /**
     * Presents the output data for the instructions use case.
     *
     * @param outputData the data to be presented
     */
    void present(InstructionsOutputData outputData);

    /**
     * Prepares the success view with the given output data.
     *
     * @param outputData the data to be used for preparing the success view
     */
    void prepareSuccessView(InstructionsOutputData outputData);

    /**
     * Prepares the fail view with the given error message.
     *
     * @param errorMessage the error message to be used for preparing the fail view
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the grid view.
     */
    void switchToGridView();

    /**
     * Switches to the discussion board view.
     */
    void switchToDiscussionBoardView();

    /**
     * Switches to the profile view.
     */
    void switchToProfileView();
}
