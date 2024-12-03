package interface_adapter.grid;

import java.beans.PropertyChangeSupport;

import entity.CellResult;
import entity.GuessResult;
import interface_adapter.ViewManagerModel;
import interface_adapter.gameend.GameEndViewModel;
import use_case.grid.GridOutputBoundary;
import use_case.grid.GridOutputData;

/**
 * The Presenter for the Grid Use Case.
 */
public class GridPresenter implements GridOutputBoundary {

    private final GridViewModel gridViewModel;
    private final ViewManagerModel viewManagerModel;
    private final GameEndViewModel gameEndViewModel;
    private final PropertyChangeSupport support;

    public GridPresenter(ViewManagerModel viewManagerModel,
                         GridViewModel gridViewModel, GameEndViewModel gameEndViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.gridViewModel = gridViewModel;
        this.gameEndViewModel = gameEndViewModel;
        this.support = new PropertyChangeSupport(this);
    }

    @Override
    public void switchToGameEndView() {
        gameEndViewModel.firePropertyChange("viewDisplayed", false, true);
        viewManagerModel.setState(gameEndViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void presentGuessResult(GridOutputData outputData) {
        GuessResult result = outputData.getGuessResult();
        int currentRow = outputData.getRow();

        // Iterate over the cell results for this row and update only those cells
        for (int col = 0; col < 5; col++) {
            // Get the result for this column in the current guess
            CellResult cellResult = result.getCellResults().get(col);
            String letter = String.valueOf(cellResult.getLetter());
            boolean isCorrectPosition = cellResult.isCorrectPosition();
            boolean isCorrectLetter = cellResult.isCorrectLetter();

            // Update the GridViewModel with the letter and color data for this cell
            gridViewModel.setCell(currentRow, col, letter, isCorrectPosition, isCorrectLetter);
        }
        support.firePropertyChange("gridUpdate", null, gridViewModel);
    }

    /**
     * Presents output to the grid view.
     * @param outputData is the GridOutputData.
     */
    @Override
    public void presentOutput(GridOutputData outputData) {
        // Use the existing presentGuessResult method to update the GridViewModel
        presentGuessResult(outputData);
    }
}
