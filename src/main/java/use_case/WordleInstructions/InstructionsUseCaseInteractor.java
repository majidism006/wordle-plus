package use_case.WordleInstructions;

import interface_adapter.grid.GridState;
import use_case.service.UserService;
import data_access.repository.WordRepository;

/**
 * This is the Interactor for the Instructions Use Case.
 * It handles the business logic for the instructions page.
 */
public class InstructionsUseCaseInteractor implements InstructionsInputBoundary {

    private final UserService userService;
    private final InstructionsOutputBoundary instructionsPresenter;
    private final WordRepository wordRepository;
    private final GridState gridState;

    public InstructionsUseCaseInteractor(UserService userService,
                                         InstructionsOutputBoundary instructionsPresenter,
                                         WordRepository wordRepository, GridState gridState) {
        this.userService = userService;
        this.instructionsPresenter = instructionsPresenter;
        this.wordRepository = wordRepository;
        this.gridState = gridState;
    }

    /**
     * Executes the instructions use case.
     * This method could be used to set up any necessary data or state for the instructions view.
     * For simplicity, we assume it just sets the initial state or prepares the UI.
     */
    @Override
    public void execute(InstructionsInputData inputData) {
        // You could initialize or update the state for the Instructions ViewModel here.
        // For now, we don't have specific logic, but you can set any state if needed.
        System.out.println("Instructions Use Case executed!");
    }

    /**
     * Switches from the instructions view to the game grid view.
     */
    @Override
    public void switchToGridView(String randomWord) {
        // Update the game state with the selected word
        gridState.setTargetWord(randomWord);
        instructionsPresenter.switchToGridView();

    }

    @Override
    public String getRandomWord(String difficulty) {
        return wordRepository.getRandomWord(difficulty);
    }

    /**
     * Switches to Discussion Board
     */
    @Override
    public void switchToDiscussionBoardView() {
        instructionsPresenter.switchToDiscussionBoardView();
    }

    @Override
    public void switchToProfileView() {instructionsPresenter.switchToProfileView();

    }


}
