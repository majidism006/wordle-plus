package use_case.login;

import use_case.service.UserService;
import entity.User;

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
        if (user == null) {
            loginPresenter.prepareFailView("Invalid Credentials.");
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
}
