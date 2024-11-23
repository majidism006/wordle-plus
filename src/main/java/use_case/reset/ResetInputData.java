package use_case.reset;

public class ResetInputData {
    private final String username;

    public ResetInputData(String username) {
        this.username = username;
    }

    String getUsername() {
        return username;
    }
}
