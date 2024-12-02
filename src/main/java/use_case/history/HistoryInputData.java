package use_case.history;

/**
 * Represents the input data for a history use case.
 */
public class HistoryInputData {

    /**
     * The username associated with the history input data.
     */
    private final String username;

    /**
     * The status associated with the history input data.
     */
    private String status = "";

    /**
     * Constructs a HistoryInputData object with the specified username.
     *
     * @param username the username associated with the history input data
     */
    public HistoryInputData(String username) {
        this.username = username;
    }

    /**
     * Constructs a HistoryInputData object with the specified username and status.
     *
     * @param username the username associated with the history input data
     * @param status the status associated with the history input data
     */
    public HistoryInputData(String username, String status) {
        this.username = username;
        this.status = status;
    }

    /**
     * Returns the status associated with the history input data.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns the username associated with the history input data.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }
}
