package use_case.logout;

/**
 * Represents the input boundary for the logout use case.
 */
public interface LogoutInputBoundary {

    /**
     * Executes the Logout use case.
     *
     * @param logoutInputData the input data
     */
    void execute(LogoutInputData logoutInputData);

    /**
     * Switches to the instruction view.
     */
    void switchToInstructionView();
}
