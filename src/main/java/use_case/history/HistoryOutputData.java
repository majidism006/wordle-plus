package use_case.history;

public class HistoryOutputData {

    private final String username;
    private final int win;
    private final int loss;
    private String status;
    private final boolean useCaseFailed;

    public HistoryOutputData(String name, int win, int loss, String status, boolean useCaseFailed) {
        this.username = name;
        this.win = win;
        this.loss = loss;
        this.useCaseFailed = useCaseFailed;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public int getWin() {
        return win;
    }

    public int getLoss() {
        return loss;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
