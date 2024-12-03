package use_case;

import data_access.repository.UserRepositoryImpl;
import interface_adapter.ViewManagerModel;
import interface_adapter.discussion.DiscussionPostViewModel;
import interface_adapter.gameend.GameEndViewModel;
import interface_adapter.instructions.InstructionsViewModel;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.security.PasswordHasher;
import interface_adapter.signup.SignupViewModel;
import org.junit.jupiter.api.Test;
import use_case.login.*;
import use_case.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class LoginInteractorTest {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String WRONG = "wrong";
    public static final String NONEXIST = "hauefibcjbwilev";

    LoginInputData inputData = new LoginInputData(USERNAME, PASSWORD);
    UserRepositoryImpl userRepository = new UserRepositoryImpl();
    PasswordHasher passwordHasher = new PasswordHasher();
    UserService userService = new UserService(userRepository, passwordHasher);

    @Test
    void successTest() {
        // For the success test, we need to add username to the data access repository before we log in.
        userService.registerUser(USERNAME, PASSWORD);
        userService.setCurrentUsername(USERNAME);

        // This creates a successPresenter that tests whether the test case is as we expect.
        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals(USERNAME, user.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToSignupView() {
                return;
            }

            @Override
            public void switchToInstructionsView() {return;}

        };

        LoginInputBoundary interactor = new LoginInteractor(userService, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void successUserLoggedInTest() {
        LoginInputData inputData = new LoginInputData(USERNAME, PASSWORD);
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        PasswordHasher passwordHasher = new PasswordHasher();
        UserService userService = new UserService(userRepository, passwordHasher);

        userService.registerUser(USERNAME, PASSWORD);
        userService.setCurrentUsername(USERNAME);

        // This creates a successPresenter that tests whether the test case is as we expect.
        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals(USERNAME, userService.getCurrentUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToSignupView() {
                return;
            }

            @Override
            public void switchToInstructionsView() {return;}

        };

        LoginInputBoundary interactor = new LoginInteractor(userService, successPresenter);
        assertEquals(USERNAME, userService.getCurrentUsername());

        interactor.execute(inputData);
    }

    @Test
    void failurePasswordMismatchTest() {
        LoginInputData inputData = new LoginInputData(USERNAME, WRONG);
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        PasswordHasher passwordHasher = new PasswordHasher();
        UserService userService = new UserService(userRepository, passwordHasher);

        // For this failure test, we need to add user to the data access repository before we log in, and
        // the passwords should not match.
        userService.registerUser(USERNAME, PASSWORD);
        userService.setCurrentUsername(USERNAME);

        // This creates a presenter that tests whether the test case is as we expect.
        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid password.", error);
            }

            @Override
            public void switchToSignupView() {
                return;
            }

            @Override
            public void switchToInstructionsView() {return;}

        };

        LoginInputBoundary interactor = new LoginInteractor(userService, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureUserDoesntExistTest() {
        LoginInputData inputData = new LoginInputData(NONEXIST, PASSWORD);
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        PasswordHasher passwordHasher = new PasswordHasher();
        UserService userService = new UserService(userRepository, passwordHasher);

        // This creates a presenter that tests whether the test case is as we expect.
        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid username.", error);
            }

            @Override
            public void switchToSignupView() {
                return;
            }

            @Override
            public void switchToInstructionsView() {return;}

        };

        LoginInputBoundary interactor = new LoginInteractor(userService, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void switchToSignupViewTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        InstructionsViewModel instructionsViewModel = new InstructionsViewModel();
        GameEndViewModel gameEndViewModel = new GameEndViewModel();
        DiscussionPostViewModel postViewModel = new DiscussionPostViewModel();
        ProfileViewModel profileViewModel = new ProfileViewModel();
        LoginPresenter loginPresenter = new LoginPresenter(viewManagerModel, loginViewModel, signupViewModel,
                instructionsViewModel, gameEndViewModel, postViewModel, profileViewModel);
        LoginInputBoundary interactor = new LoginInteractor(userService, loginPresenter);
        interactor.switchToSignupView();

        assertEquals("sign up", viewManagerModel.getState());
    }

    @Test
    void switchToInstructionsViewTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        InstructionsViewModel instructionsViewModel = new InstructionsViewModel();
        GameEndViewModel gameEndViewModel = new GameEndViewModel();
        DiscussionPostViewModel postViewModel = new DiscussionPostViewModel();
        ProfileViewModel profileViewModel = new ProfileViewModel();
        LoginPresenter loginPresenter = new LoginPresenter(viewManagerModel, loginViewModel, signupViewModel,
                instructionsViewModel, gameEndViewModel, postViewModel, profileViewModel);
        LoginInputBoundary interactor = new LoginInteractor(userService, loginPresenter);
        interactor.switchToInstructionsView();

        assertEquals("instructions", viewManagerModel.getState());
    }
}