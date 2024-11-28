package use_case.logout;


/**
 * The Input Data for the Logout Use Case.
 */
public class GameEndInputData {

    private final String username;

    public GameEndInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
