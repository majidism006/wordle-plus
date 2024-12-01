package use_case;

import data_access.repository.UserRepositoryImpl;
import entity.User;
import interface_adapter.security.PasswordHasher;
import org.junit.jupiter.api.Test;
import use_case.service.UserService;
import use_case.signup.*;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class SignupInteractorTest {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String WRONG = "wrong";
    public static final String UNEXISTED = "unexistedUsername";

    @Test
    void successTest() {
        SignupInputData inputData = new SignupInputData(UNEXISTED, PASSWORD, PASSWORD);
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        PasswordHasher passwordHasher = new PasswordHasher();
        UserService userService = new UserService(userRepository, passwordHasher);

        // This creates a successPresenter that tests whether the test case is as we expect.
        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                assertEquals(UNEXISTED, user.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToLoginView() {
                // This is expected
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userService, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void failurePasswordMismatchTest() {
        SignupInputData inputData = new SignupInputData(USERNAME, PASSWORD, WRONG);
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        PasswordHasher passwordHasher = new PasswordHasher();
        UserService userService = new UserService(userRepository, passwordHasher);

        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Passwords don't match.", error);
            }

            @Override
            public void switchToLoginView() {
                // This is expected
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userService, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureUserExistsTest() {
        SignupInputData inputData = new SignupInputData(USERNAME, WRONG, WRONG);
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        PasswordHasher passwordHasher = new PasswordHasher();
        UserService userService = new UserService(userRepository, passwordHasher);

        // Add Paul to the repo so that when we check later they already exist
        userService.registerUser(USERNAME, WRONG);
        userService.setCurrentUsername(USERNAME);

        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User already exists.", error);
            }

            @Override
            public void switchToLoginView() {
                // This is expected
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userService, failurePresenter);
        interactor.execute(inputData);
    }
}