package use_case.signup;

/**
 * Represents the input data for the signup use case.
 */
public class SignupInputData {

    /**
     * The username for the signup use case.
     */
    private final String username;

    /**
     * The password for the signup use case.
     */
    private final String password;

    /**
     * The repeated password for the signup use case.
     */
    private final String repeatPassword;

    /**
     * Constructs a SignupInputData object with the specified username, password, and repeated password.
     *
     * @param username the username for the signup use case
     * @param password the password for the signup use case
     * @param repeatPassword the repeated password for the signup use case
     */
    public SignupInputData(String username, String password, String repeatPassword) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    /**
     * Returns the username for the signup use case.
     *
     * @return the username
     */
    String getUsername() {
        return username;
    }

    /**
     * Returns the password for the signup use case.
     *
     * @return the password
     */
    String getPassword() {
        return password;
    }

    /**
     * Returns the repeated password for the signup use case.
     *
     * @return the repeated password
     */
    public String getRepeatPassword() {
        return repeatPassword;
    }
}
