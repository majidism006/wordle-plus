package use_case.History;

public interface HistoryInputBoundary {

    /**
     * Executes the History use case.
     * @param historyInputData the input data
     */
    void execute(HistoryInputData historyInputData);

    void updatestatus(HistoryInputData historyInputData);
}
