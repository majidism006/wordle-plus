package use_case.grid;

public class GridInteractor implements GridInputBoundary {
    private final GridOutputBoundary gridPresenter;

    public GridInteractor(GridOutputBoundary outputBoundary) {
        this.gridPresenter = outputBoundary;
    }

    @Override
    public void switchToLogoutView() {
        gridPresenter.switchToLogoutView();
    }

    @Override
    public void execute(GridInputData gridInputData) {
        // TODO: implement this
    }

}