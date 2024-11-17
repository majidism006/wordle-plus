package interface_adapter.grid;

import interface_adapter.ViewManagerModel;
import use_case.grid.GridOutputBoundary;
import interface_adapter.logout.LogoutViewModel;

/**
 * The Presenter for the Grid Use Case.
 */
public class GridPresenter implements GridOutputBoundary {

    private final GridViewModel gridViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LogoutViewModel logoutViewModel;

    public GridPresenter(ViewManagerModel viewManagerModel,
                         GridViewModel gridViewModel, LogoutViewModel logoutViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.gridViewModel = gridViewModel;
        this.logoutViewModel = logoutViewModel;
    }

    @Override
    public void switchToLogoutView() {
        viewManagerModel.setState(logoutViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}