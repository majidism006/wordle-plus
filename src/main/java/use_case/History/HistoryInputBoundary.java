package use_case.History;

import use_case.logout.GameEndInputData;

public interface HistoryInputBoundary {

    /**
     * Executes the History use case.
     * @param historyInputData the input data
     */
    void execute(HistoryInputData historyInputData);

    void updatestatus(HistoryInputData historyInputData);

}
