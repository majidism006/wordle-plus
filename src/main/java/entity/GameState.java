package entity;

import interface_adapter.grid.GridState;

/**
 * Class representing the state of the game.
 */
public class GameState {
    private int remainingAttempts;
    private final String targetWord;
    private final GridState gridState;

    /**
     * Constructs a new GameState with the specified GridState.
     * Initializes the remaining attempts to 6 and sets the target word from the GridState.
     *
     * @param gridState the GridState associated with the game
     */
    public GameState(GridState gridState) {
        remainingAttempts = 6;
        this.gridState = gridState;
        this.targetWord = gridState.getTargetWord();
    }

    /**
     * Sets the number of remaining attempts.
     *
     * @param remainingAttempts the new number of remaining attempts
     */
    public void setRemainingAttempts(int remainingAttempts) {
        this.remainingAttempts = remainingAttempts;
    }

    /**
     * Gets the number of remaining attempts.
     *
     * @return the number of remaining attempts
     */
    public int getRemainingAttempts() {
        return remainingAttempts;
    }

    /**
     * Checks the user's guess against the target word.
     * Delegates the guess checking to the GridState.
     *
     * @param guess the user's guess
     * @return the result of the guess check
     */
    public GuessResult checkGuess(String guess) {
        // Delegate the guess checking to GridState
        return gridState.checkGuess(guess);
    }
}