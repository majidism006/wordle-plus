package use_case.grid;

/**
 * The Input Data for the Grid Use Case.
 */
public class GridInputData {

    private final int row;
    private final String guess;

    /**
     * Constructs a new GridInputData with the specified row, column, and letter.
     *
     * @param row   the row index
     * @param guess the guessed word
     */
    public GridInputData(int row, String guess) {
        this.row = row;
        this.guess = guess;
    }

    /**
     * Gets the row index.
     *
     * @return the row index
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the guessed word.
     * (Currently returns an empty string, to be implemented)
     *
     * @return the guessed word
     */
    public String getGuess() {
        return guess;
    }
}
