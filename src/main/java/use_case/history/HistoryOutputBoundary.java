package use_case.history;

/**
 * Represents the output boundary for a history use case.
 */
public interface HistoryOutputBoundary {

    /**
     * Prepares the success view with the given history output data.
     *
     * @param historyOutputData the data to be used for preparing the success view
     */
    void prepareSuccessView(HistoryOutputData historyOutputData);

    /**
     * Updates the status with the given history output data.
     *
     * @param historyOutputData the data to be used for updating the status
     */
    void updateStatus(HistoryOutputData historyOutputData);
}
