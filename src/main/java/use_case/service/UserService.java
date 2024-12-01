package use_case.service;

import entity.User;
import data_access.repository.UserRepositoryImpl;
import interface_adapter.security.PasswordHasher;

public class UserService {
    private final UserRepositoryImpl userRepository;
    private final PasswordHasher passwordHasher;
    private String currentUsername;

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
            this.currentUsername = user.getUsername();
            return user;
        }
        return null; // Invalid credentials
    }


    public String getCurrentUsername() {return currentUsername;}
  
    public void setCurrentUsername(String name) {this.currentUsername = name;}

    // This line seems unused. I keep it for safe.
    public User getUserByUsername(String username) {return userRepository.findUserByUsername(username);}

    public int getUserWins(String username) {return userRepository.getUserWins(username);}

    public int getUserLosses(String username) {return userRepository.getUserLosses(username);}

    public void setUserWins(String username, int i) {
        userRepository.setUserWins(username, i);
    }
    public void setUserLosses(String username, int i) {
        userRepository.setUserLosses(username, i);
    }

    public void setStatus(String username, String text) {
        userRepository.setUserStatus(username, text);
    }

    public String getStatus(String username) {
        return userRepository.getUserStatus(username);
    }
}

