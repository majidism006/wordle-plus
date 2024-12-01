package use_case.history;

public class HistoryInputData {

    private final String username;
    private String status = "";

    public HistoryInputData(String username) {
        this.username = username;
    }

    public HistoryInputData(String username, String status) {
        this.username = username;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getUsername() {
        return username;
    }
}
