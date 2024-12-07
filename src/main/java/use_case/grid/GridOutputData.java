package use_case.grid;

import entity.CellResult;
import entity.GuessResult;

import java.util.List;

public class GridOutputData {
    private final int row;
    private final GuessResult guessResult;
    private final List<CellResult> cellResults;
    private final boolean isCorrect;

    public GridOutputData(int row, GuessResult guessResult) {
        this.row = row;
        this.cellResults = guessResult.getCellResults();
        this.isCorrect = guessResult.isCorrect();
        this.guessResult = guessResult;
    }

    public int getRow() {
        return row;
    }

    public List<CellResult> getCellResults() {
        return cellResults;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public GuessResult getGuessResult() {
        return guessResult;
    }
}
