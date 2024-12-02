package data_access.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.config.DatabaseConfig;
import entity.User;

/**
 * Defines CRUD operations for the user operations.
 */
public class UserRepositoryImpl {

    /**
     * Finds User by username.
     *
     * @param username is the username to find the user by.
     * @return User object.
     * @throws RuntimeException if no user found.
     */
    public User findUserByUsername(String username) {
        String query = "SELECT id, username, password FROM users WHERE username = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getInt("id"),
                            rs.getString("username"), rs.getString("password"));
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error finding user", e);
        }
        return null;
    }

    /**
     * Saves new user into the database table.
     *
     * @param user User object to insert into database.
     * @throws RuntimeException if no user found.
     */
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
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving user", e);
        }
    }

    /**
     * Sets the number of wins for the user.
     *
     * @param username username of the user.
     * @param wins number of wins to set.
     * @throws RuntimeException if no user found.
     */
    public void setUserWins(String username, int wins) {
        String query = "UPDATE users SET wins = ? WHERE username = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, wins);
            stmt.setString(2, username);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating user wins", e);
        }
    }

    /**
     * Sets the number of losses for the user.
     *
     * @param username username of the user.
     * @param losses number of losses to set.
     * @throws RuntimeException if no user found.
     */
    public void setUserLosses(String username, int losses) {
        String query = "UPDATE users SET losses = ? WHERE username = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, losses);
            stmt.setString(2, username);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating user losses", e);
        }
    }

    /**
     * Retrieves the number of losses for a given user.
     *
     * @param username the username of the user
     * @return the number of losses for the user, or 0 if an error occurs
     * @throws RuntimeException if no user found.
     */
    public int getUserLosses(String username) {
        String query = "SELECT losses FROM users WHERE username = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("losses");
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving user losses", e);
        }
        return 0;
    }

    /**
     * Retrieves the number of wins for a given user.
     *
     * @param username the username of the user
     * @return the number of wins for the user, or 0 if an error occurs
     * @throws RuntimeException if no user found.
     */
    public int getUserWins(String username) {
        String query = "SELECT wins FROM users WHERE username = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("wins");
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving user wins", e);
        }
        return 0;
    }

    /**
     * Sets the status for a given user.
     *
     * @param username the username of the user
     * @param text the new status text to set
     * @throws RuntimeException if no user found.
     */
    public void setUserStatus(String username, String text) {
        String query = "UPDATE users SET status = ? WHERE username = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, text);
            stmt.setString(2, username);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating user status", e);
        }
    }

    /**
     * Retrieves the status for a given user.
     *
     * @param username the username of the user
     * @return the status of the user, or an empty string if an error occurs
     * @throws RuntimeException if no user found.
     */
    public String getUserStatus(String username) {
        String query = "SELECT status FROM users WHERE username = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("status");
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving user status", e);
        }
        return "";
    }
}
