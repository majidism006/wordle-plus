package use_case.History;

public class HistoryOutputData {

    private final String username;
    private final int win;
    private final int loss;
    private String state;
    private final boolean useCaseFailed;

    public HistoryOutputData(String name, int win, int loss, String state, boolean useCaseFailed) {
        this.username = name;
        this.win = win;
        this.loss = loss;
        this.useCaseFailed = useCaseFailed;
        this.state = state;
    }

    public String getState() {
        return state;
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
