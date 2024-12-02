package interface_adapter.grid;

import entity.CellResult;
import entity.GuessResult;
import interface_adapter.ViewManagerModel;
import use_case.grid.GridOutputBoundary;
import interface_adapter.gameend.GameEndViewModel;
import java.beans.PropertyChangeSupport;

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
    public void presentGuessResult(GuessResult result) {
        // Update the GridViewModel with the guess result
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 5; col++) {
                CellResult cellResult = result.getCellResults().get(col);
                gridViewModel.setCellContent(row, col, String.valueOf(cellResult.getLetter()));
                gridViewModel.setCellCorrectPosition(row, col, cellResult.isCorrectPosition());
                gridViewModel.setCellCorrectLetter(row, col, cellResult.isCorrectLetter());
            }
        }
        support.firePropertyChange("gridUpdate", null, gridViewModel);
    }
}