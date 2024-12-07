package use_case.grid;

import entity.GuessResult;

/**
 * Interface defining the output boundary for grid-related use cases.
 */
public interface GridOutputBoundary {

    /**
     * Switches the view to the game end view.
     */
    void switchToGameEndView();

    /**
     * Presents the result of a guess.
     *
     * @param outputData the outputData
     */
    void presentGuessResult(GridOutputData outputData);

    void presentOutput(GridOutputData outputData);
}
