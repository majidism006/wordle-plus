package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the result of a guess in the game.
 */
public class GuessResult {
    private boolean isCorrect;
    private List<CellResult> cellResults;

    /**
     * Constructs a new GuessResult with an empty list of cell results.
     */
    public GuessResult() {
        this.cellResults = new ArrayList<>();
    }

    /**
     * Checks if the guess is correct.
     *
     * @return true if the guess is correct, false otherwise
     */
    public boolean isCorrect() {
        return isCorrect;
    }

    /**
     * Sets whether the guess is correct.
     *
     * @param correct true if the guess is correct, false otherwise
     */
    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    /**
     * Gets the list of cell results for the guess.
     *
     * @return the list of cell results
     */
    public List<CellResult> getCellResults() {
        return cellResults;
    }

    /**
     * Adds a cell result to the list of cell results.
     *
     * @param cellResult the cell result to add
     */
    public void addCellResult(CellResult cellResult) {
        this.cellResults.add(cellResult);
    }
}
