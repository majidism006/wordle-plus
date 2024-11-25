package use_case.logout;


public interface LogoutInputBoundary {

    /**
     * Executes the Logout use case.
     * @param LogoutInputData the input data
     */
    void execute(LogoutInputData LogoutInputData);

    void switchToInstructionView();
}
