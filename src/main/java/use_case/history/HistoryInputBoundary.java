package use_case.history;

/**
 * Interface defining the input boundary for history-related use cases.
 */
public interface HistoryInputBoundary {

    /**
     * Executes the History use case.
     *
     * @param historyInputData the input data for the history use case
     */
    void execute(HistoryInputData historyInputData);

    /**
     * Updates the status in the history use case.
     *
     * @param historyInputData the input data for updating the status
     */
    void updateStatus(HistoryInputData historyInputData);
}
