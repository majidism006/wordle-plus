package use_case.logout;

/**
 * Output Data for the Logout Use Case.
 */
public class GameEndOutputData {

    private final String username;
    private final boolean useCaseFailed;

    public GameEndOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
