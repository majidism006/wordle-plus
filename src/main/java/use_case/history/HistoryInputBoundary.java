package use_case.history;

public interface HistoryInputBoundary {

    /**
     * Executes the History use case.
     * @param historyInputData the input data
     */
    void execute(HistoryInputData historyInputData);

    void updateStatus(HistoryInputData historyInputData);
}
