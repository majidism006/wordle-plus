package entity;

public class CellResult {
    private char letter;
    private boolean isCorrectPosition;
    private boolean isCorrectLetter;

    public CellResult(char letter, boolean isCorrectPosition, boolean isCorrectLetter) {
        this.letter = letter;
        this.isCorrectPosition = isCorrectPosition;
        this.isCorrectLetter = isCorrectLetter;
    }

    public char getLetter() {
        return letter;
    }

    public boolean isCorrectPosition() {
        return isCorrectPosition;
    }

    public boolean isCorrectLetter() {
        return isCorrectLetter;
    }
}
