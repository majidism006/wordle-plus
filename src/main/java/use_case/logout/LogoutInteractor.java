package use_case.logout;

import use_case.service.UserService;

/**
 * The Logout Interactor.
 */
public class LogoutInteractor implements LogoutInputBoundary {
    private final UserService userService;
    private final LogoutOutputBoundary logoutPresenter;

    public LogoutInteractor(UserService userService,
                            LogoutOutputBoundary logoutOutputBoundary) {
        this.userService = userService;
        this.logoutPresenter = logoutOutputBoundary;
    }

    @Override
    public void execute(LogoutInputData logoutInputData) {

        final String name = logoutInputData.getUsername();
        userService.setCurrentUsername(null);
        final LogoutOutputData logoutOutputData = new LogoutOutputData(name, false);
        logoutPresenter.prepareSuccessView(logoutOutputData);
    }

    @Override
    public void switchToInstructionView() {
        logoutPresenter.switchToInstructionView();

    }
}

