package use_case.signup;

/**
 * Represents the output data for the signup use case.
 */
public class SignupOutputData {

    /**
     * The username for the signup use case.
     */
    private final String username;

    /**
     * Indicates whether the use case failed.
     */
    private final boolean useCaseFailed;

    /**
     * Constructs a SignupOutputData object with the specified username and use case failure status.
     *
     * @param username the username for the signup use case
     * @param useCaseFailed indicates whether the use case failed
     */
    public SignupOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Returns the username for the signup use case.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Indicates whether the use case failed.
     *
     * @return true if the use case failed, false otherwise
     */
    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
