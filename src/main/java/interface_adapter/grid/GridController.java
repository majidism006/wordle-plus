package interface_adapter.grid;

import entity.GuessResult;
import use_case.grid.GridInputBoundary;
import use_case.grid.GridInputData;

import javax.swing.*;
import java.awt.*;

/**
 * The controller for the Grid Use Case.
 */
public class GridController {

    private final GridInputBoundary gridInteractor;
    // Reference to GridState
    private final GridState gridState;
    private final GridViewModel gridViewModel;

    public GridController(GridInputBoundary gridInteractor, GridState gridState, GridViewModel gridViewModel) {
        this.gridInteractor = gridInteractor;
        this.gridState = gridState;
        this.gridViewModel = gridViewModel;
    }

    /**
     * Executes the Grid Use Case.
     * @param row the row index in the grid
     * @param col the column index in the grid
     * @param letter the letter entered in the grid
     */
    public void execute(int row, int col, String letter) {
        gridState.setCellContent(row, col, letter);
        final GridInputData gridInputData = new GridInputData(row, letter);
        gridInteractor.execute(gridInputData);
    }

    /**
     * Requests a transition to signout view when implemented.
     */
    public void switchToGameEndView() {
        gridInteractor.switchToGameEndView();
    }

    /**
     * Sets the target word for the game by delegating to GridState.
     * @param targetWord the target word to be guessed
     */
    public void setTargetWord(String targetWord) {
        gridState.setTargetWord(targetWord);
    }

    /**
     * Checks if the guessed word matches the target word by delegating to GridState.
     * @param guessedWord the word guessed by the player
     * @return the GuessResult object containing feedback
     */
    public GuessResult checkWord(String guessedWord) {
        return gridInteractor.checkGuess(guessedWord);
    }

    /**
     * Records win/loss for the user.
     * @param userWon whether the user won or not
     */
    public void recordGameResult(boolean userWon) {
        gridInteractor.recordGameResult(userWon);
    }

    public void submitGuess(int row, String guessedWord) {
        // Update the current row in the gridState
        gridState.setCurrentRow(row);
        GuessResult result = checkWord(guessedWord);
        // Set the content for the specific row and columns, update only the current row
        for (int col = 0; col < 5; col++) {
            gridState.setCellContent(row, col, guessedWord.substring(col, col + 1));
        }

        // Notify listeners of the update to the specific row
        gridViewModel.notifyListeners("cellUpdate", null, gridState);
        if (result.isCorrect()) {
            gridViewModel.notifyListeners("isCorrect", null, gridState);
        }
        // Handle the guess with the gridInteractor
        GridInputData inputData = new GridInputData(row, guessedWord);
        gridInteractor.handleGuess(inputData);
    }
}
