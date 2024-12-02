package entity;

/**
 * Class representing a User.
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String status;

    /**
     * Constructs a new User with the specified ID, username, and password.
     * Sets the status to "active" by default.
     *
     * @param id the ID of the user
     * @param username the username of the user
     * @param password the password of the user
     */
    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = "active";
    }

    /**
     * Constructs a new User with the specified username and password.
     * Sets the ID to 0 and the status to "active" by default.
     *
     * @param username the username of the user
     * @param password the password of the user
     */
    public User(String username, String password) {
        // Default value
        this.id = 0;
        this.username = username;
        this.password = password;
        this.status = "active";
    }

    /**
     * Gets the ID of the user.
     *
     * @return the ID of the user
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the user.
     *
     * @param id the new ID of the user
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the username of the user.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the new username of the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the new password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the status of the user.
     *
     * @return the status of the user
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the user.
     *
     * @param status the new status of the user
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns a string representation of the user.
     *
     * @return a string representation of the user
     */
    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", username='" + username + '\''
                + ", password='" + password + '\''
                + '}';
    }
}
