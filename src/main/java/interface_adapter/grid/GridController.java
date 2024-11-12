package interface_adapter.grid;

import use_case.grid.GridInputBoundary;
import use_case.grid.GridInputData;

/**
 * The controller for the Grid Use Case.
 */
public class GridController {

    private final GridInputBoundary gridInteractor;

    public GridController(GridInputBoundary gridInteractor) {
        this.gridInteractor = gridInteractor;
    }

    /**
     * Executes the Grid Use Case.
     * @param row the row index in the grid
     * @param col the column index in the grid
     * @param letter the letter entered in the grid
     */
    public void execute(int row, int col, String letter) {
        final GridInputData gridInputData = new GridInputData(row, col, letter);
        gridInteractor.execute(gridInputData);
    }

    /**
     * Requests a transition to signout view when implemented.
     */
    // public void switchToSignoutView() {
     //   gridInteractor.switchToSignoutView();
    //}
}
