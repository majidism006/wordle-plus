package use_case.login;

/**
 * The Input Data for the Login Use Case.
 */
public class LoginInputData {

    /**
     * The username for the login use case.
     */
    private final String username;

    /**
     * The password for the login use case.
     */
    private final String password;

    /**
     * Constructs a LoginInputData object with the specified username and password.
     *
     * @param username the username for the login use case
     * @param password the password for the login use case
     */
    public LoginInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Returns the username for the login use case.
     *
     * @return the username
     */
    String getUsername() {
        return username;
    }

    /**
     * Returns the password for the login use case.
     *
     * @return the password
     */
    String getPassword() {
        return password;
    }
}
