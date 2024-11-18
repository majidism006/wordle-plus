package interface_adapter.instructions;

import use_case.WordleInstructions.InstructionsInputBoundary;
import use_case.WordleInstructions.InstructionsInputData;

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
    public void switchToGridView() {
        instructionsUseCaseInteractor.switchToGridView();
    }
}
