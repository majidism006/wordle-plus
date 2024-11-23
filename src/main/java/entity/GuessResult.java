package entity;

import java.util.ArrayList;
import java.util.List;

public class GuessResult {
    private boolean isCorrect;
    private List<CellResult> cellResults;

    public GuessResult() {
        this.cellResults = new ArrayList<>();
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public List<CellResult> getCellResults() {
        return cellResults;
    }

    public void addCellResult(CellResult cellResult) {
        this.cellResults.add(cellResult);
    }
}

