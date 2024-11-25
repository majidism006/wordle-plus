package interface_adapter.logout;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInputData;

public class LogoutController {
    private static final Logger log = LoggerFactory.getLogger(LogoutController.class);
    private final LogoutInputBoundary logoutUseCaseInteractor;


    public LogoutController(LogoutInputBoundary logoutUseCaseInteractor) {
        this.logoutUseCaseInteractor = logoutUseCaseInteractor;
    }


    /**
     * Executes the Logout Use Case.
     * @param username the username of the user logging in
     */
    public void execute(String username) {
        final LogoutInputData logoutInputData = new LogoutInputData(username);
        logoutUseCaseInteractor.execute(logoutInputData);
    }


    public void switchToInstructionView() {
        logoutUseCaseInteractor.switchToInstructionView();
    }
}
