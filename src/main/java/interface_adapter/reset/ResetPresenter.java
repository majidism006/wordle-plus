package interface_adapter.reset;

import interface_adapter.ViewManagerModel;
import interface_adapter.grid.GridViewModel;
import use_case.reset.ResetOutBoundary;
import use_case.reset.ResetOutputData;

public class ResetPresenter implements ResetOutBoundary {

    private final GridViewModel gridViewModel;
    private final ViewManagerModel viewManagerModel;

    public ResetPresenter(ViewManagerModel viewManagerModel, GridViewModel gridViewModel) {
        this.gridViewModel = gridViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ResetOutputData resetOutputData) {
        gridViewModel.firePropertyChanged("reset");
    }

    @Override
    public void switchToGridView() {
        viewManagerModel.setState(gridViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
