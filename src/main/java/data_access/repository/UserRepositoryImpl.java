package data_access.repository;

import app.config.DatabaseConfig;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl {

    public User findUserByUsername(String username) {
        String query = "SELECT id, username, password FROM users WHERE username = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error finding user", e);
        }
        return null;
    }

    public void saveUser(User user) {
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.executeUpdate();

            // Retrieve the generated ID
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                }
            }

            System.out.println("User saved successfully with ID: " + user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving user", e);
        }
    }

    // Other CRUD methods...
}

