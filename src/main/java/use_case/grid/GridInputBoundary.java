package use_case.grid;


public interface GridInputBoundary {

    /**
     * Executes the grid action use case.
     * @param gridInputData the input data
     */
    void execute(GridInputData gridInputData);
    void switchToLogoutView();
}


