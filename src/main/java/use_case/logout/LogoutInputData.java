package use_case.logout;

/**
 * The Input Data for the Logout Use Case.
 */
public class LogoutInputData {

    /**
     * The username for the logout use case.
     */
    private final String username;

    /**
     * Constructs a LogoutInputData object with the specified username.
     *
     * @param username the username for the logout use case
     */
    public LogoutInputData(String username) {
        this.username = username;
    }

    /**
     * Returns the username for the logout use case.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }
}
