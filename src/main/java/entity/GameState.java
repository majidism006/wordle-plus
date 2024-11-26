package entity;

import interface_adapter.grid.GridState;

public class GameState {
    private int remainingAttempts;
    private final String targetWord;
    private final GridState gridState;

    public GameState(GridState gridState) {
        remainingAttempts = 6;
        this.gridState = gridState;
        this.targetWord = gridState.getTargetWord();
    }

    // Getters and setters
    public void setRemainingAttempts(int remainingAttempts) {
        this.remainingAttempts = remainingAttempts;
    }
    public int getRemainingAttempts() {
        return remainingAttempts;
    }
    public String getTargetWord() {
        return targetWord;
    }
    public GuessResult checkGuess(String guess) {
        // Delegate the guess checking to GridState
        return gridState.checkGuess(guess);
    }
}