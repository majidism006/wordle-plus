package use_case.History;

public class HistoryInputData {

    private final String username;
    private String state = "";

    public HistoryInputData(String username) {
        this.username = username;
    }

    public HistoryInputData(String username, String state) {
        this.username = username;
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public String getUsername() {
        return username;
    }
}
