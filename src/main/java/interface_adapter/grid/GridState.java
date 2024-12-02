package interface_adapter.grid;

import entity.CellResult;
import entity.GuessResult;

/**
 * Class representing the state of the grid in the game.
 */
public class GridState {

    private final String[][] gridContent = new String[6][5];
    private final boolean[][] correctPosition = new boolean[6][5];
    private final boolean[][] correctLetter = new boolean[6][5];
    private String targetWord;

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
     * Gets the content of a specific cell in the grid.
     * (Currently returns null, can be implemented as needed)
     *
     * @param row the row index of the cell
     * @param col the column index of the cell
     * @return the content of the specified cell
     */
    public String getCell(int row, int col) {
        return null;
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

        for (int i = 0; i < guess.length(); i++) {
            char guessedLetter = guess.charAt(i);
            char targetLetter = targetWord.charAt(i);

            boolean isCorrectPosition = guessedLetter == targetLetter;
            boolean isCorrectLetter = targetWord.contains(String.valueOf(guessedLetter));

            if (!isCorrectPosition) {
                isCorrect = false;
            }

            CellResult cellResult = new CellResult(guessedLetter, isCorrectPosition, isCorrectLetter);
            guessResult.addCellResult(cellResult);
        }

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
}
