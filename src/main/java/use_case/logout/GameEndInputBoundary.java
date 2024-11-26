package use_case.logout;


public interface GameEndInputBoundary {

    /**
     * Executes the Logout use case.
     * @param gameEndInputData the input data
     */
    void execute(GameEndInputData gameEndInputData);

    void switchToInstructionView();

    void loadUserHistory();
}
