package use_case.history;

public interface HistoryOutputBoundary {
    void prepareSuccessView(HistoryOutputData historyOutputData);

    void updateStatus(HistoryOutputData historyOutputData);
}
