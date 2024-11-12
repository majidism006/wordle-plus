package use_case.grid;

/**
 * The Input Data for the Grid Use Case.
 */
public class GridInputData {

    private final int row;
    private final int col;
    private final String letter;

    public GridInputData(int row, int col, String letter) {
        this.row = row;
        this.col = col;
        this.letter = letter;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getLetter() {
        return letter;
    }
}
