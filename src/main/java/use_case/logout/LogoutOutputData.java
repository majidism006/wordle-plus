package use_case.logout;

/**
 * Output Data for the Logout Use Case.
 */
public class LogoutOutputData {

    /**
     * The username for the logout use case.
     */
    private final String username;

    /**
     * Indicates whether the use case failed.
     */
    private final boolean useCaseFailed;

    /**
     * Constructs a LogoutOutputData object with the specified username and use case failure status.
     *
     * @param username the username for the logout use case
     * @param useCaseFailed indicates whether the use case failed
     */
    public LogoutOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Returns the username for the logout use case.
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
