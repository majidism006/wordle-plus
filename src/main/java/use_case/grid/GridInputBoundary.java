package use_case.grid;

import use_case.grid.GridInputData;

public interface GridInputBoundary {
    /**
     * Executes the grid action use case.
     * @param gridInputData the input data
     */
    void execute(GridInputData gridInputData);

    // yet to be implemented void switchToSignoutView();
}


