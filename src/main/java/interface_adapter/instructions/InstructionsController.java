package interface_adapter.instructions;

import use_case.WordleInstructions.InstructionsInputBoundary;
import use_case.WordleInstructions.InstructionsInputData;
import use_case.WordleInstructions.InstructionsOutputData;

/**
 * The controller for the Instructions Use Case.
 */
public class InstructionsController {

    private final InstructionsInputBoundary instructionsUseCaseInteractor;
    private InstructionsInputBoundary instructionsInputBoundary;

    public InstructionsController(InstructionsInputBoundary instructionsUseCaseInteractor) {
        this.instructionsUseCaseInteractor = instructionsUseCaseInteractor;
    }

    // Setter for InstructionsInputBoundary
    public void setInstructionsInputBoundary(InstructionsInputBoundary instructionsInputBoundary) {
        this.instructionsInputBoundary = instructionsInputBoundary;
    }
    
    /**
     * Executes the Instructions Use Case.
     * This could involve initializing or setting up the instructions state.
     */
    public void execute() {
        final InstructionsInputData instructionsInputData = new InstructionsInputData();

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
}
