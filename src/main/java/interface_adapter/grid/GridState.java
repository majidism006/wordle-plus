package interface_adapter.grid;

import entity.CellResult;
import entity.GuessResult;

public class GridState {

    private final String[][] gridContent = new String[6][5];
    private String targetWord;
    // private String gridError = "";

    // Getter for grid content
    public String[][] getGridContent() {
        return gridContent;
    }

    // Getter for a specific cell's content
    public String getCellContent(int row, int col) {
        return gridContent[row][col];
    }

    // Setter for a specific cell's content
    public void setCellContent(int row, int col, String letter) {
        this.gridContent[row][col] = letter;
    }
    // Setter for the target word
    public void setTargetWord(String targetWord) {
        this.targetWord = targetWord;
    }
    public String getCell(int row, int col) {
        return null;
    }

    // Getter for grid error (When needed)

    // public String getGridError() {
      //  return gridError;
    //}

    // Setter for grid error (When needed)
    // public void setGridError(String gridError) {
       // this.gridError = gridError;
    //}
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
}
