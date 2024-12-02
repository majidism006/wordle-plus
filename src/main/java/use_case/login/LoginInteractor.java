package use_case.login;

import entity.User;
import use_case.service.UserService;

/**
 * The Login Interactor.
 */
public class LoginInteractor implements LoginInputBoundary {
    private final UserService userService;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(UserService userService,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userService = userService;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        final String username = loginInputData.getUsername();
        final String password = loginInputData.getPassword();
        User user = userService.loginUser(username, password);
        if (user == null && userService.getUserByUsername(username) != null) {
            loginPresenter.prepareFailView("Invalid password.");
        }
        else if (user == null) {
            loginPresenter.prepareFailView("Invalid username.");
        }
        else {
            final LoginOutputData loginOutputData = new LoginOutputData(user.getUsername(), false);
            loginPresenter.prepareSuccessView(loginOutputData);
        }
    }

    @Override
    public void switchToSignupView() {
        loginPresenter.switchToSignupView();

    }

    @Override
    public void switchToInstructionsView() {
        loginPresenter.switchToInstructionsView();

    }
}
