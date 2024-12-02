package entity;

/**
 * Holds results for each cell.
 */
public class CellResult {
    private char letter;
    private boolean isCorrectPosition;
    private boolean isCorrectLetter;

    public CellResult(char letter, boolean isCorrectPosition, boolean isCorrectLetter) {
        this.letter = letter;
        this.isCorrectPosition = isCorrectPosition;
        this.isCorrectLetter = isCorrectLetter;
    }

    /**
     * Returns letter.
     * @return letter char.
     */
    public char getLetter() {
        return letter;
    }

    /**
     * Returns whether the letter is in the correct position.
     * @return true or false.
     */
    public boolean isCorrectPosition() {
        return isCorrectPosition;
    }

    /**
     * Returns whether the letter is correct.
     * @return true or false.
     */
    public boolean isCorrectLetter() {
        return isCorrectLetter;
    }
}
