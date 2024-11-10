package use_case.signup;

import use_case.service.UserService;
import data_access.repository.UserRepositoryImpl;
import entity.User;
import entity.UserFactory;

/**
 * The Signup Interactor.
 */
public class SignupInteractor implements SignupInputBoundary {
    private final UserService userService;
    private final SignupOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public SignupInteractor(UserService userService,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userService = userService;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        }
        else if (userService.registerUser(signupInputData.getUsername(), signupInputData.getPassword())) {

            final SignupOutputData signupOutputData = new SignupOutputData(signupInputData.getUsername(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
        else {
            userPresenter.prepareFailView("User already exists.");
        }
    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }
}
