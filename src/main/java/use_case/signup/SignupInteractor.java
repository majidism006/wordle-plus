package use_case.signup;

import entity.User;
import use_case.service.UserService;

public class SignupInteractor implements SignupInputBoundary {
    private final UserService userService;
    private final SignupOutputBoundary userPresenter;
//    private final UserFactory userFactory;

    public SignupInteractor(UserService userService,
                            SignupOutputBoundary signupOutputBoundary) {
        this.userService = userService;
        this.userPresenter = signupOutputBoundary;
//        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        final String username = signupInputData.getUsername();
        final String password = signupInputData.getPassword();
        User user = userService.registerUser(username, password);
        if (user == null) {
            userPresenter.prepareFailView("User already exists.");
        }
        else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        }
        else {

            final SignupOutputData signupOutputData = new SignupOutputData(user.getUsername(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }
}
