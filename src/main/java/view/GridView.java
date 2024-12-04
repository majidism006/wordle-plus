package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import entity.CellResult;
import entity.GuessResult;
import interface_adapter.grid.GridController;
import interface_adapter.grid.GridState;
import interface_adapter.grid.GridViewModel;

/**
 * The View for the Wordle Grid.
 */
public class GridView extends JPanel implements PropertyChangeListener {

    private static final String VIEW_NAME = "grid";
    private final GridViewModel gridViewModel;
    private final JTextField[][] gridCells;
    private GridController gridController;
    private static final int ROWS = 6;
    private static final int COLS = 5;
    private static final int TWENTY = 20;
    private static final int TEN = 10;
    private static final int EIGHTEEN = 18;

    public GridView(GridViewModel gridViewModel) {
        this.gridViewModel = gridViewModel;
        this.gridViewModel.addPropertyChangeListener(this);

        gridCells = new JTextField[ROWS][COLS];

        // Main layout with BorderLayout to include the title and grid
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        // Title label
        JLabel titleLabel = new JLabel("WORDLE!!!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, TWENTY));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.NORTH);

        // Grid panel
        JPanel gridPanel = new JPanel(new GridLayout(ROWS, COLS, TEN, TEN));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(TWENTY, TWENTY, TWENTY, TWENTY));
        gridPanel.setBackground(Color.BLACK);

        // Initialize grid cells with DocumentFilters for user input
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                // Each cell holds one letter
                JTextField cell = new JTextField(1);
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setFont(new Font("Arial", Font.BOLD, EIGHTEEN));

                // Set dark mode colors: black background and white text
                cell.setBackground(Color.BLACK);
                cell.setForeground(Color.WHITE);
                cell.setCaretColor(Color.WHITE);

                // Add grey border
                cell.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                // Apply the custom DocumentFilter
                ((AbstractDocument) cell.getDocument()).setDocumentFilter(new UppercaseDocumentFilter(row, col));

                int finalCol = col;
                int finalRow = row;
                cell.addKeyListener(new java.awt.event.KeyAdapter() {
                    @Override
                    public void keyPressed(java.awt.event.KeyEvent e) {
                        if (e.getKeyCode() == java.awt.event.KeyEvent.VK_BACK_SPACE && cell.getText().isEmpty()) {
                            // Move to the previous cell on Backspace if the current cell is empty
                            if (finalCol > 0) {
                                gridCells[finalRow][finalCol - 1].requestFocus();
                            }
                        }
                        else if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                            // If Enter is pressed after filling the row, switch to the logout view
                            handleEnterKey(finalRow);
                        }
                    }
                });

                gridCells[row][col] = cell;
                gridPanel.add(cell);
            }
        }
        // Add grid panel to the center of the layout
        add(gridPanel, BorderLayout.CENTER);
    }

    /**
     * Listens to property changes and updates the grid cells when state changes.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final GridState state = (GridState) evt.getNewValue();
        updateGrid(state);
        if (evt.getPropertyName().equals("reset")) {
            clear();
        }
    }

    /**
     * Updates the grid display based on the provided GridState.
     * @param state of the grid
     */
    private void updateGrid(GridState state) {
        for (int row = 0; row < gridCells.length; row++) {
            for (int col = 0; col < gridCells[row].length; col++) {
                String letter = state.getCellContent(row, col);
                gridCells[row][col].setText(letter);

                if (state.isCellCorrectPosition(row, col)) {
                    gridCells[row][col].setBackground(Color.GREEN);
                }
                else if (state.isCellCorrectLetter(row, col)) {
                    gridCells[row][col].setBackground(Color.ORANGE);
                }
                else {
                    gridCells[row][col].setBackground(Color.GRAY);
                }
            }
        }
    }

    /**
    * Clears the row s when we play again
     **/

    public void clear() {
        for (int row = 0; row < gridCells.length; row++) {
            for (int col = 0; col < gridCells[row].length; col++) {
                gridCells[row][col].setText("");
                gridCells[row][col].setBackground(Color.BLACK);
            }
        }
    }

    /**
     * Custom DocumentFilter to enforce uppercase letters and single character input.
     */
    private class UppercaseDocumentFilter extends DocumentFilter {
        private final int row;
        private final int col;

        public UppercaseDocumentFilter(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string != null) {
                string = string.toUpperCase();
                if (isValidCharacter(string) && fb.getDocument().getLength() + string.length() <= 1) {
                    super.insertString(fb, offset, string, attr);
                    shiftFocus();
                }
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text != null) {
                text = text.toUpperCase();
                if (isValidCharacter(text) && fb.getDocument().getLength() - length + text.length() <= 1) {
                    super.replace(fb, offset, length, text, attrs);
                    shiftFocus();
                }
            }
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            super.remove(fb, offset, length);
        }

        private void shiftFocus() {
            if (col < gridCells[row].length - 1) {
                gridCells[row][col + 1].requestFocus();
            }
        }
    }

    /**
     * Validates if the input string contains only alphabetic characters.
     *
     * @param text The input string.
     * @return True if the text contains only alphabetic characters, false otherwise.
     */
    private boolean isValidCharacter(String text) {
        for (char c : text.toCharArray()) {
            if (!Character.isLetter(c)) {
                Toolkit.getDefaultToolkit().beep(); // Provide feedback for invalid input
                return false;
            }
        }
        return true;
    }

        private void handleEnterKey(int row) {
        if (isRowComplete(row)) {
            StringBuilder guessedWord = new StringBuilder();

            // Collect the guessed word from the current row
            for (int col = 0; col < gridCells[row].length; col++) {
                guessedWord.append(gridCells[row][col].getText());
            }

            // Get the feedback for the guessed word
            GuessResult guessResult = gridController.checkWord(guessedWord.toString());

            // Update cell backgrounds based on feedback
            for (int col = 0; col < guessResult.getCellResults().size(); col++) {
                CellResult cellResult = guessResult.getCellResults().get(col);

                if (cellResult.isCorrectPosition()) {
                    gridCells[row][col].setBackground(Color.GREEN);
                } else if (cellResult.isCorrectLetter()) {
                    gridCells[row][col].setBackground(Color.ORANGE);
                } else {
                    gridCells[row][col].setBackground(Color.GRAY);
                }
            }

            if (guessResult.isCorrect()) {
                gridController.recordGameResult(true);
                JOptionPane.showMessageDialog(this, "Congratulations! You guessed the word!");
                gridController.switchToGameEndView();
            } else if (row == gridCells.length - 1) {
                gridController.recordGameResult(false);
                JOptionPane.showMessageDialog(this, "Game Over! Try again!" );
                gridController.switchToGameEndView();
            } else {
                focusNextRow(row + 1);
            }

        }
    }

    private void focusNextRow(int nextRow) {
        if (nextRow < gridCells.length) {
            gridCells[nextRow][0].requestFocus();
        }
    }

    private String getRowWord(int row) {
        StringBuilder word = new StringBuilder();
        for (int col = 0; col < gridCells[row].length; col++) {
            word.append(gridCells[row][col].getText());
        }
        return word.toString();
    }


    private boolean isRowComplete(int row) {
        // Check if all cells in the row are filled
        for (int col = 0; col < gridCells[row].length; col++) {
            if (gridCells[row][col].getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public String getViewName() {
        return VIEW_NAME;
    }

    /**
     * Set the GridController for this view.
     * @param gridController the GridController to set
     */
    public void setGridController(GridController gridController) {
        this.gridController = gridController;
    }
}
