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
    public static final String WRONG = "Wrong";

    // need to be changed for each time
    public static final String UNREGIS = "wirgheriogn";

    public static final String UNREGIS4LOGIN = "aoehfiwoehf";
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
    void loginUserTest_RegisteredWithWrongPassword() {
        userService.registerUser(USERNAME, PASSWORD);
        User user = userService.loginUser(USERNAME, WRONG);
        assertNull(user);
    }

    @Test
    void loginUserTest_Unregistered() {
        User user = userService.loginUser(UNREGIS4LOGIN, PASSWORD);
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
        userRepository.setUserLosses(USERNAME, NUM1);
        assert userService.getUserLosses(USERNAME) == NUM1;
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

    @Test
    void getUserbyUsernameTest() {
        assertEquals(userRepository.findUserByUsername(USERNAME).getUsername(),
                userService.getUserByUsername(USERNAME).getUsername());
        assertEquals(userRepository.findUserByUsername(USERNAME).getPassword(),
                userService.getUserByUsername(USERNAME).getPassword());
    }

}