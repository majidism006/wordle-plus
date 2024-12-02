package use_case.login;

/**
 * Output Data for the Login Use Case.
 */
public class LoginOutputData {

    /**
     * The username for the login use case.
     */
    private final String username;

    /**
     * Indicates whether the use case failed.
     */
    private final boolean useCaseFailed;

    /**
     * Constructs a LoginOutputData object with the specified username and use case failure status.
     *
     * @param username the username for the login use case
     * @param useCaseFailed indicates whether the use case failed
     */
    public LoginOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Returns the username for the login use case.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }
}
