package interface_adapter.instructions;

import use_case.WordleInstructions.InstructionsInputBoundary;
import use_case.WordleInstructions.InstructionsInputData;

/**
 * The controller for the Instructions Use Case.
 */
public class InstructionsController {

    private final InstructionsInputBoundary instructionsUseCaseInteractor;

    public InstructionsController(InstructionsInputBoundary instructionsUseCaseInteractor) {
        this.instructionsUseCaseInteractor = instructionsUseCaseInteractor;
    }

    /**
     * Executes the Instructions Use Case.
     * This could involve initializing or setting up the instructions state.
     */
    public void execute(String difficulty) {
        // Create InstructionsInputData with the necessary data
        InstructionsInputData instructionsInputData = new InstructionsInputData(difficulty);

        // Execute the use case with the input data
        instructionsUseCaseInteractor.execute(instructionsInputData);
    }

    /**
     * Switches from the instructions view to the game grid view.
     */
    public void switchToGridView(String difficulty) {
        // Fetch a random word based on the difficulty
        String randomWord = instructionsUseCaseInteractor.getRandomWord(difficulty);

        // Use the interactor to update the state and switch to the grid view
        instructionsUseCaseInteractor.switchToGridView(randomWord);
    }

    /**
     * Switches from instructions view to profile view.
     */
    public void switchToProfileView() {
        instructionsUseCaseInteractor.switchToProfileView();
    }

    /**
     * Switches from instructions view to discussion board view.
     */
    public void switchToDiscussionBoardView() {
        instructionsUseCaseInteractor.switchToDiscussionBoardView();
    }
}