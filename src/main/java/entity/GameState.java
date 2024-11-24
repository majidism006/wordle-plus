package entity;

import interface_adapter.grid.GridState;

public class GameState {
    private int level;
    private int remainingAttempts;
    private GridState gridState;

    // Getters and setters

    public GuessResult checkGuess(String guess) {
        // Delegate the guess checking to GridState
        return gridState.checkGuess(guess);
    }
}