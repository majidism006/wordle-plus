package use_case.logout;

public class LogoutInputData {
    private String username;

    public LogoutInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
