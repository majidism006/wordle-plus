package use_case.grid;

/**
 * The Input Data for the Grid Use Case.
 */
public class GridInputData {

    private final int row;
    private final int col;
    private final String letter;

    /**
     * Constructs a new GridInputData with the specified row, column, and letter.
     *
     * @param row the row index
     * @param col the column index
     * @param letter the letter to be placed in the grid
     */
    public GridInputData(int row, int col, String letter) {
        this.row = row;
        this.col = col;
        this.letter = letter;
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
     * Gets the column index.
     *
     * @return the column index
     */
    public int getCol() {
        return col;
    }

    /**
     * Gets the letter to be placed in the grid.
     *
     * @return the letter
     */
    public String getLetter() {
        return letter;
    }

}
