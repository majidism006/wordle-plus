package use_case.service;

import data_access.repository.UserRepositoryImpl;
import entity.User;
import interface_adapter.security.PasswordHasher;

/**
 * Service class for managing user-related operations.
 */
public class UserService {

    /**
     * Repository for accessing user data.
     */
    private final UserRepositoryImpl userRepository;

    /**
     * Utility for hashing passwords.
     */
    private final PasswordHasher passwordHasher;

    /**
     * The username of the currently logged-in user.
     */
    private String currentUsername;

    /**
     * Constructs a UserService with the specified user repository and password hasher.
     *
     * @param userRepository the repository for accessing user data
     * @param passwordHasher the utility for hashing passwords
     */
    public UserService(UserRepositoryImpl userRepository, PasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    /**
     * Registers a new user with the specified username and password.
     *
     * @param username the username of the new user
     * @param password the password of the new user
     * @return the newly registered user, or null if the user already exists
     */
    public User registerUser(String username, String password) {
        if (userRepository.findUserByUsername(username) != null) {
            // User already exists
            return null;
        }
        String hashedPassword = passwordHasher.hashPassword(password);
        User newUser = new User(0, username, hashedPassword);
        userRepository.saveUser(newUser);
        return newUser;
    }

    /**
     * Logs in a user with the specified username and password.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return the logged-in user, or null if the credentials are invalid
     */
    public User loginUser(String username, String password) {
        User user = userRepository.findUserByUsername(username);
        if (user != null && passwordHasher.verifyPassword(password, user.getPassword())) {
            this.currentUsername = user.getUsername();
            return user;
        }
        // Invalid credentials
        return null;
    }

    /**
     * Returns the username of the currently logged-in user.
     *
     * @return the current username
     */
    public String getCurrentUsername() {
        return currentUsername;
    }

    /**
     * Sets the username of the currently logged-in user.
     *
     * @param name the username to set
     */
    public void setCurrentUsername(String name) {
        this.currentUsername = name;
    }

    /**
     * Returns the user with the specified username.
     *
     * @param username the username of the user
     * @return the user with the specified username
     */
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    /**
     * Returns the number of wins for the user with the specified username.
     *
     * @param username the username of the user
     * @return the number of wins
     */
    public int getUserWins(String username) {
        return userRepository.getUserWins(username);
    }

    /**
     * Returns the number of losses for the user with the specified username.
     *
     * @param username the username of the user
     * @return the number of losses
     */
    public int getUserLosses(String username) {
        return userRepository.getUserLosses(username);
    }

    /**
     * Sets the number of wins for the user with the specified username.
     *
     * @param username the username of the user
     * @param wins the number of wins to set
     */
    public void setUserWins(String username, int wins) {
        userRepository.setUserWins(username, wins);
    }

    /**
     * Sets the number of losses for the user with the specified username.
     *
     * @param username the username of the user
     * @param losses the number of losses to set
     */
    public void setUserLosses(String username, int losses) {
        userRepository.setUserLosses(username, losses);
    }

    /**
     * Sets the status for the user with the specified username.
     *
     * @param username the username of the user
     * @param text the status to set
     */
    public void setStatus(String username, String text) {
        userRepository.setUserStatus(username, text);
    }

    /**
     * Returns the status for the user with the specified username.
     *
     * @param username the username of the user
     * @return the status
     */
    public String getStatus(String username) {
        return userRepository.getUserStatus(username);
    }
}
