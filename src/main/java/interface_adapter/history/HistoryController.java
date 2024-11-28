package interface_adapter.history;

import use_case.History.HistoryInputBoundary;
import use_case.History.HistoryInputData;
import use_case.logout.GameEndInputData;

public class HistoryController {
    private final HistoryInputBoundary historyInteractor;

    public HistoryController(HistoryInputBoundary historyInteractor) {
        this.historyInteractor = historyInteractor;
    }

    /**
     * Executes the History Use Case.
     *
     * @param username the username of the user logging in
     */
    public void execute(String username) {
        final HistoryInputData historyInputData = new HistoryInputData(username);
        historyInteractor.execute(historyInputData);
    }
}
