package use_case.logout;

/**
 * The output boundary for the Logout Use Case.
 */
public interface GameEndOutputBoundary {
    /**
     * Prepares the success view for the Login Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(GameEndOutputData outputData);
  
    void switchToInstructionView();

    void loadUserHistory(int win, int loss);
}
