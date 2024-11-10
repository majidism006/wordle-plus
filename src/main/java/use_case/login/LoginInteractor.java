package use_case.login;

import data_access.repository.UserRepositoryImpl;
import entity.User;

/**
 * The Login Interactor.
 */
public class LoginInteractor implements LoginInputBoundary {
    private final UserRepositoryImpl userRepository;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(UserRepositoryImpl userRepository,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userRepository = userRepository;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        final String username = loginInputData.getUsername();
        final String password = loginInputData.getPassword();
        if (userRepository.findUserByUsername(username) == null) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        }
        else {
            final String pwd = userRepository.findUserByUsername(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for \"" + username + "\".");
            }
            else {

                final User user = userRepository.findUserByUsername(loginInputData.getUsername());

                final LoginOutputData loginOutputData = new LoginOutputData(user.getUsername(), false);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }
}
