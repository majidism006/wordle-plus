package interface_adapter.difficulty;

import interface_adapter.ViewManagerModel;
import use_case.difficulty.DifficultyOutputBoundary;
import use_case.difficulty.DifficultyOutputData;

public class DifficultyPresenter implements DifficultyOutputBoundary {

    private ViewManagerModel viewManagerModel;
    private DifficultyViewModel difficultyViewModel;

    public DifficultyPresenter(ViewManagerModel viewManagerModel, DifficultyViewModel difficultyViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.difficultyViewModel = difficultyViewModel;
    }

    @Override
    public void prepareSuccessView(DifficultyOutputData response) {
        this.viewManagerModel.setState(difficultyViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

}
