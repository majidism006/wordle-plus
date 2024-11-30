package interface_adapter.instructions;

import interface_adapter.ViewManagerModel;
import interface_adapter.discussion.DiscussionPostViewModel;
import interface_adapter.grid.GridState;
import interface_adapter.grid.GridViewModel;
import interface_adapter.profile.ProfileViewModel;
import use_case.WordleInstructions.InstructionsOutputBoundary;
import use_case.WordleInstructions.InstructionsOutputData;
import view.ProfileView;
import view.WordleInstructionsGUI;

/**
 * The Presenter for the Instructions Use Case.
 */
public class InstructionsPresenter implements InstructionsOutputBoundary {

    private final InstructionsViewModel instructionsViewModel;
    private final ViewManagerModel viewManagerModel;
    private final GridViewModel gridViewModel;
    private final DiscussionPostViewModel discussionPostViewModel;
    private final ProfileViewModel profileViewModel;

    public InstructionsPresenter(ViewManagerModel viewManagerModel, InstructionsViewModel instructionsViewModel,
                                 GridViewModel gridViewModel, DiscussionPostViewModel discussionPostViewModel,
                                 ProfileViewModel profileViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.instructionsViewModel = instructionsViewModel;
        this.gridViewModel = gridViewModel;
        this.discussionPostViewModel = discussionPostViewModel;
        this.profileViewModel = profileViewModel;
    }

    @Override
    public void prepareSuccessView(InstructionsOutputData response) {
        // Update the grid view model with the random word
        String randomWord = response.getTargetWord();
        gridViewModel.setTargetWord(randomWord);
            // Switch to the grid view in the view manager
            this.viewManagerModel.setState(gridViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
        }

    @Override
    public void prepareFailView(String error) {
        // Handle failure (if needed), e.g., show an error message in the instructions view
        System.out.println("Error: " + error);
    }

    @Override
    public void switchToGridView() {
        this.viewManagerModel.setState(gridViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Switches to Discussion Board
     */
    @Override
    public void switchToDiscussionBoardView() {
        this.viewManagerModel.setState(discussionPostViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToProfileView() {
        this.viewManagerModel.setState(profileViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
