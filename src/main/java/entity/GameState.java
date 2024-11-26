package entity;

import interface_adapter.grid.GridState;

public class GameState {
    private final int level;
    private int remainingAttempts;
    private final GridState gridState;

    public GameState(int level, GridState gridState) {
        this.level = level;
        this.gridState = gridState;
    }

    // Getters and setters
    public void setRemainingAttempts(int remainingAttempts) {
        this.remainingAttempts = remainingAttempts;
    }
    public int getRemainingAttempts() {
        return remainingAttempts;
    }
    public int getLevel() {
        return level;
    }
    public GridState getGridState() {
        return gridState;
    }

    public GuessResult checkGuess(String guess) {
        // Delegate the guess checking to GridState
        return gridState.checkGuess(guess);
    }
}