package use_case.grid;

import entity.GuessResult;

/**
 * Interface defining the input boundary for grid-related use cases.
 */
public interface GridInputBoundary {

    /**
     * Executes the grid action use case.
     *
     * @param gridInputData the input data for the grid action
     */
    void execute(GridInputData gridInputData);

    /**
     * Switches the view to the game end view.
     */
    void switchToGameEndView();

    /**
     * Checks the user's guessed word against the target word.
     *
     * @param guessedWord the word guessed by the user
     * @return the result of the guess check
     */
    GuessResult checkGuess(String guessedWord);

    /**
     * Records the result of the game.
     *
     * @param userWon true if the user won the game, false otherwise
     */
    void recordGameResult(boolean userWon);
}
