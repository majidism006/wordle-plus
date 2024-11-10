package use_case.service;

import entity.User;
import data_access.repository.UserRepositoryImpl;
import interface_adapter.security.PasswordHasher;

public class UserService {
    private final UserRepositoryImpl userRepository;
    private final PasswordHasher passwordHasher;

    public UserService(UserRepositoryImpl userRepository, PasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    public User registerUser(String username, String password) {
        if (userRepository.findUserByUsername(username) != null) {
            return null; // User already exists
        }
        String hashedPassword = passwordHasher.hashPassword(password);
        User newUser = new User(0, username, hashedPassword);
        userRepository.saveUser(newUser);
        return newUser;
    }

    public User loginUser(String username, String password) {
        User user = userRepository.findUserByUsername(username);
        if (user != null && passwordHasher.verifyPassword(password, user.getPassword())) {
            return user;
        }
        return null; // Invalid credentials
    }
}

