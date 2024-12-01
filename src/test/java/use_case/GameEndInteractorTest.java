package use_case;

import data_access.repository.UserRepositoryImpl;
import interface_adapter.security.PasswordHasher;
import org.junit.jupiter.api.Test;
import use_case.logout.*;
import use_case.service.UserService;

import static org.junit.jupiter.api.Assertions.*;

class GameEndInteractorTest {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    @Test
    void successTest() {
        GameEndInputData inputData = new GameEndInputData(USERNAME);
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        PasswordHasher passwordHasher = new PasswordHasher();
        UserService userService = new UserService(userRepository, passwordHasher);

        // For the success test, we need to add user to the data access repository before we log in.
        userService.registerUser(USERNAME, PASSWORD);
        userService.setCurrentUsername(USERNAME);

        // This creates a successPresenter that tests whether the test case is as we expect.
        GameEndOutputBoundary successPresenter = new GameEndOutputBoundary() {
            @Override
            public void prepareSuccessView(GameEndOutputData user) {
                // check that the output data contains the username of who logged out
                assertEquals(USERNAME, user.getUsername());
            }

            @Override
            public void switchToInstructionView() {
                return;
            }

        };

        GameEndInputBoundary interactor = new GameEndInteractor(userService, successPresenter);
        interactor.execute(inputData);

        // check that the user was logged out
        assertNull(userService.getCurrentUsername());
    }

}