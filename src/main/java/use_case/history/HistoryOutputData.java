package use_case.history;

/**
 * Represents the output data for a history use case.
 */
public class HistoryOutputData {

    /**
     * The username associated with the history output data.
     */
    private final String username;

    /**
     * The number of wins associated with the history output data.
     */
    private final int win;

    /**
     * The number of losses associated with the history output data.
     */
    private final int loss;

    /**
     * The status associated with the history output data.
     */
    private String status;

    /**
     * Indicates whether the use case failed.
     */
    private final boolean useCaseFailed;

    /**
     * Constructs a HistoryOutputData object with the specified parameters.
     *
     * @param name the username associated with the history output data
     * @param win the number of wins
     * @param loss the number of losses
     * @param status the status
     * @param useCaseFailed indicates whether the use case failed
     */
    public HistoryOutputData(String name, int win, int loss, String status, boolean useCaseFailed) {
        this.username = name;
        this.win = win;
        this.loss = loss;
        this.useCaseFailed = useCaseFailed;
        this.status = status;
    }

    /**
     * Returns the status associated with the history output data.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns the number of wins associated with the history output data.
     *
     * @return the number of wins
     */
    public int getWin() {
        return win;
    }

    /**
     * Returns the number of losses associated with the history output data.
     *
     * @return the number of losses
     */
    public int getLoss() {
        return loss;
    }

    /**
     * Returns the username associated with the history output data.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Indicates whether the use case failed.
     *
     * @return true if the use case failed, false otherwise
     */
    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
