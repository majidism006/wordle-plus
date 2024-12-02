package interface_adapter.history;

import use_case.history.HistoryInputBoundary;
import use_case.history.HistoryInputData;

/**
 * Controller class for handling history-related actions.
 */
public class HistoryController {
    private final HistoryInputBoundary historyInteractor;

    /**
     * Constructs a new HistoryController with the specified interactor.
     *
     * @param historyInteractor the interactor to handle history use cases
     */
    public HistoryController(HistoryInputBoundary historyInteractor) {
        this.historyInteractor = historyInteractor;
    }

    /**
     * Executes the History Use Case.
     *
     * @param username the username of the user
     */
    public void execute(String username) {
        final HistoryInputData historyInputData = new HistoryInputData(username);
        historyInteractor.execute(historyInputData);
    }

    /**
     * Executes the History Use Case with a specific state.
     *
     * @param username the username of the user
     * @param state the state to update
     */
    public void execute(String username, String state) {
        final HistoryInputData historyInputData = new HistoryInputData(username, state);
        historyInteractor.updateStatus(historyInputData);
    }
}
