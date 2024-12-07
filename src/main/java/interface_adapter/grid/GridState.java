package interface_adapter.grid;

import entity.CellResult;
import entity.GuessResult;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Class representing the state of the grid in the game.
 */
public class GridState {

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private final String[][] gridContent = new String[6][5];
    private final boolean[][] correctPosition = new boolean[6][5];
    private final boolean[][] correctLetter = new boolean[6][5];
    private int currentRow;
    private String targetWord;

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public void notifyListeners(String propertyName, Object oldValue, Object newValue) {
        support.firePropertyChange(propertyName, oldValue, newValue);
    }

    /**
     * Gets the content of the entire grid.
     *
     * @return a 2D array representing the grid content
     */
    public String[][] getGridContent() {
        return gridContent;
    }

    /**
     * Gets the target word for the game.
     *
     * @return the target word
     */
    public String getTargetWord() {
        return targetWord;
    }

    /**
     * Gets the content of a specific cell in the grid.
     *
     * @param row the row index of the cell
     * @param col the column index of the cell
     * @return the content of the specified cell
     */
    public String getCellContent(int row, int col) {
        return gridContent[row][col];
    }

    /**
     * Sets the content of a specific cell in the grid.
     *
     * @param row the row index of the cell
     * @param col the column index of the cell
     * @param letter the content to set in the cell
     */
    public void setCellContent(int row, int col, String letter) {
        this.gridContent[row][col] = letter;
    }

    /**
     * Sets the target word for the game.
     *
     * @param targetWord the target word to set
     */
    public void setTargetWord(String targetWord) {
        this.targetWord = targetWord;
    }

    /**
     * Checks the user's guess against the target word.
     *
     * @param guess the user's guess
     * @return the result of the guess check
     */
    public GuessResult checkGuess(String guess) {
        GuessResult guessResult = new GuessResult();
        boolean isCorrect = true;

        // Check each letter in the guess
        for (int i = 0; i < guess.length(); i++) {
            char guessedLetter = guess.charAt(i);
            char targetLetter = targetWord.charAt(i);

            boolean isCorrectPosition = guessedLetter == targetLetter;
            boolean isCorrectLetter = targetWord.contains(String.valueOf(guessedLetter));

            // Set the correctness indicators
            setCellCorrectPosition(currentRow, i, isCorrectPosition);
            setCellCorrectLetter(currentRow, i, isCorrectLetter);

            // Update the guess result for the letter
            CellResult cellResult = new CellResult(guessedLetter, isCorrectPosition, isCorrectLetter);
            guessResult.addCellResult(cellResult);

            if (!isCorrectPosition) {
                isCorrect = false;
            }
        }

        // Set the overall correctness for the guess
        guessResult.setCorrect(isCorrect);

        return guessResult;
    }

    /**
     * Sets whether a specific cell is in the correct position.
     *
     * @param row the row index of the cell
     * @param col the column index of the cell
     * @param isCorrect true if the cell is in the correct position, false otherwise
     */
    public void setCellCorrectPosition(int row, int col, boolean isCorrect) {
        this.correctPosition[row][col] = isCorrect;
    }

    /**
     * Sets whether a specific cell contains the correct letter.
     *
     * @param row the row index of the cell
     * @param col the column index of the cell
     * @param isCorrect true if the cell contains the correct letter, false otherwise
     */
    public void setCellCorrectLetter(int row, int col, boolean isCorrect) {
        this.correctLetter[row][col] = isCorrect;
    }

    /**
     * Checks if a specific cell is in the correct position.
     *
     * @param row the row index of the cell
     * @param col the column index of the cell
     * @return true if the cell is in the correct position, false otherwise
     */
    public boolean isCellCorrectPosition(int row, int col) {
        return correctPosition[row][col];
    }

    /**
     * Checks if a specific cell contains the correct letter.
     *
     * @param row the row index of the cell
     * @param col the column index of the cell
     * @return true if the cell contains the correct letter, false otherwise
     */
    public boolean isCellCorrectLetter(int row, int col) {
        return correctLetter[row][col];
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }
}
