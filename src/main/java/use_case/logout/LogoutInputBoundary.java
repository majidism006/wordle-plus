package use_case.logout;


public interface LogoutInputBoundary {

    /**
     * Executes the Logout use case.
     * @param logoutInputData the input data
     */
    void execute(LogoutInputData logoutInputData);

    void switchToInstructionView();
}
