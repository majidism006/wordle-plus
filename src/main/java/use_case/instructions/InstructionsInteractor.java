package use_case.instructions;

import data_access.repository.WordRepository;
import interface_adapter.grid.GridState;
import use_case.service.UserService;

/**
 * This is the Interactor for the Instructions Use Case.
 * It handles the business logic for the instructions page.
 */
public class InstructionsInteractor implements InstructionsInputBoundary {

    private final UserService userService;
    private final InstructionsOutputBoundary instructionsPresenter;
    private final WordRepository wordRepository;
    private final GridState gridState;

    public InstructionsInteractor(UserService userService,
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
     */
    @Override
    public void execute(InstructionsInputData inputData) {
        // Use the inputData to perform any necessary logic
        String difficulty = inputData.getDifficulty();
        String randomWord = wordRepository.getRandomWord(difficulty);

        // Prepare the output data
        InstructionsOutputData outputData = new InstructionsOutputData(
                "Instructions Use Case executed with difficulty: " + difficulty);

        // Update the game state with the selected word
        gridState.setTargetWord(randomWord);

        // Pass the output data to the presenter
        instructionsPresenter.present(outputData);
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
     * Switches to Discussion Board.
     */
    @Override
    public void switchToDiscussionBoardView() {
        instructionsPresenter.switchToDiscussionBoardView();
    }

    @Override
    public void switchToProfileView() {
        instructionsPresenter.switchToProfileView();
    }
}
