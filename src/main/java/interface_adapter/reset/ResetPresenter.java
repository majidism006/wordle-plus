package interface_adapter.reset;

import interface_adapter.ViewManagerModel;
import interface_adapter.grid.GridViewModel;
import interface_adapter.instructions.InstructionsViewModel;
import use_case.reset.ResetOutBoundary;
import use_case.reset.ResetOutputData;

public class ResetPresenter implements ResetOutBoundary {

    private final GridViewModel gridViewModel;
    private final ViewManagerModel viewManagerModel;
    private final InstructionsViewModel instructionsViewModel;

    public ResetPresenter(ViewManagerModel viewManagerModel, GridViewModel gridViewModel,
                          InstructionsViewModel instructionsviewmodel) {
        this.gridViewModel = gridViewModel;
        this.viewManagerModel = viewManagerModel;
        this.instructionsViewModel = instructionsviewmodel;
    }

    @Override
    public void prepareSuccessView(ResetOutputData resetOutputData) {
        gridViewModel.firePropertyChanged("reset");
    }

    @Override
    public void switchToInstructionView() {
        viewManagerModel.setState(instructionsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
