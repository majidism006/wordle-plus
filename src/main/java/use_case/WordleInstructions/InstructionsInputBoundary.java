package use_case.WordleInstructions;

public interface InstructionsInputBoundary {
    /**
     * Executes the WordleInstructions use case.
     */
    void switchToGridView();

    void execute(InstructionsInputData instructionsInputData);
}
