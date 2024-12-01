package use_case;

import data_access.repository.UserRepositoryImpl;
import entity.User;
import interface_adapter.security.PasswordHasher;
import org.junit.jupiter.api.Test;
import use_case.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserServiceTest {

    public static final String USERNAME = "ServiceTest";
    public static final String PASSWORD = "ServiceTestPassword";
    public static final String UNREGIS = "UnregisteredUser";
    public static final int NUM1 = 11;
    public static final int NUM2 = 22;
    public static final String TEXT = "OnlyForTesting";

    UserRepositoryImpl userRepository = new UserRepositoryImpl();
    PasswordHasher passwordHasher = new PasswordHasher();
    UserService userService = new UserService(userRepository, passwordHasher);

    @Test
    void registerUserTest_registered() {
        userService.registerUser(USERNAME, PASSWORD);
        User user = userService.registerUser(USERNAME, PASSWORD);
        assertNull(user);
    }

    @Test
    void registerUserTest_Unregistered() {
        class TempUserService extends UserService{

            public TempUserService(UserRepositoryImpl userRepository, PasswordHasher passwordHasher) {
                super(userRepository, passwordHasher);
            }

            @Override
            public User registerUser(String username, String password) {
                if (userRepository.findUserByUsername(username) != null) {
                    return null; // User already exists
                }
                String hashedPassword = passwordHasher.hashPassword(password);
                User newUser = new User(0, username, hashedPassword);
                // Don't save user for present test
                return newUser;
            }

        }

        TempUserService userService = new TempUserService(userRepository, passwordHasher);
        User user = userService.registerUser(UNREGIS, PASSWORD);
        assertEquals(UNREGIS, user.getUsername());
        assert(passwordHasher.verifyPassword(PASSWORD, user.getPassword()));
    }

    @Test
    void loginUserTest_Registered() {
        userService.registerUser(USERNAME, PASSWORD);
        User user = userService.loginUser(USERNAME, PASSWORD);
        assertEquals(USERNAME, user.getUsername());
        assert(passwordHasher.verifyPassword(PASSWORD, user.getPassword()));
    }

    @Test
    void loginUserTest_Unregistered() {
        User user = userService.loginUser(UNREGIS, PASSWORD);
        assertNull(user);
    }

    @Test
    void getCurrentUsernameTest() {
        // Since we didn't set currentUsername, it should be null.
        assertNull(userService.getCurrentUsername());
    }

    @Test
    void setCurrentUsernameTest() {
        userService.setCurrentUsername(USERNAME);
        assertEquals(USERNAME, userService.getCurrentUsername());
    }

    @Test
    void getUserWinsTest() {
        userRepository.setUserWins(USERNAME, NUM1);
        assert userService.getUserWins(USERNAME) == NUM1;
    }

    @Test
    void setUserWinsTest() {
        userService.setUserWins(USERNAME, NUM2);
        assertEquals(NUM2, userRepository.getUserWins(USERNAME));
    }

    @Test
    void getUserLossesTest() {
        userRepository.setUserWins(USERNAME, NUM1);
        assert userService.getUserWins(USERNAME) == NUM1;
    }

    @Test
    void setUserLossesTest() {
        userService.setUserLosses(USERNAME, NUM2);
        assertEquals(NUM2, userService.getUserLosses(USERNAME));
    }

    @Test
    void getStatusTest() {
        assertEquals(userRepository.getUserStatus(USERNAME), userService.getStatus(USERNAME));
    }

    @Test
    void setStatusTest() {
        userService.setStatus(USERNAME, TEXT);
        assertEquals(TEXT, userRepository.getUserStatus(USERNAME));
    }

}