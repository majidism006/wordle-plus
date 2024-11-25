package view;

import interface_adapter.grid.GridController;
import interface_adapter.grid.GridState;
import interface_adapter.grid.GridViewModel;
import interface_adapter.logout.LogoutController;
import entity.GuessResult;
import entity.CellResult;
import use_case.WordleInstructions.InstructionsOutputData;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for the Wordle Grid.
 */
public class GridView extends JPanel implements PropertyChangeListener {

    private static final String viewName = "grid";
    private final GridViewModel gridViewModel;
    private final JTextField[][] gridCells;
    private GridController gridController;
//    private LogoutController logoutController;

    public GridView(GridViewModel gridViewModel) {
        this.gridViewModel = gridViewModel;
        this.gridViewModel.addPropertyChangeListener(this);

        int rows = 6;
        int cols = 5;
        gridCells = new JTextField[rows][cols];

        // Main layout with BorderLayout to include the title and grid
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        // Title label
        JLabel titleLabel = new JLabel("WORDLE!!!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE); // White text for contrast
        add(titleLabel, BorderLayout.NORTH);

        // Grid panel
        JPanel gridPanel = new JPanel(new GridLayout(rows, cols, 10, 10));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        gridPanel.setBackground(Color.BLACK);

        // Initialize grid cells with DocumentFilters for user input
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                JTextField cell = new JTextField(1); // Each cell holds one letter
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setFont(new Font("Arial", Font.BOLD, 18));

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
                        } else if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
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
     */
    private void updateGrid(GridState state) {
        for (int row = 0; row < gridCells.length; row++) {
            for (int col = 0; col < gridCells[row].length; col++) {
                String letter = state.getCellContent(row, col);
                gridCells[row][col].setText(letter);

                if (state.isCellCorrectPosition(row, col)) {
                    gridCells[row][col].setBackground(Color.GREEN); // Correct letter, correct position
                } else if (state.isCellCorrectLetter(row, col)) {
                    gridCells[row][col].setBackground(Color.YELLOW); // Correct letter, incorrect position
                } else {
                    gridCells[row][col].setBackground(Color.GRAY); // Incorrect letter
                }
            }
        }
    }

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
                if (fb.getDocument().getLength() + string.length() <= 1) {
                    super.insertString(fb, offset, string, attr);
                    shiftFocus();
                }
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text != null) {
                text = text.toUpperCase();
                if (fb.getDocument().getLength() - length + text.length() <= 1) {
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
                    gridCells[row][col].setBackground(Color.YELLOW);
                } else {
                    gridCells[row][col].setBackground(Color.GRAY);
                }
            }

            // Display success or failure messages
//            if (guessResult.isCorrect()) {
//                JOptionPane.showMessageDialog(this, "Congratulations! You guessed the word!");
//            } else if (row == gridCells.length - 1) {
//                GridState gridState;
//                JOptionPane.showMessageDialog(this, "Game Over! The word was: " +
//                        gridState.getTargetWord());
//            }
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
        return viewName;
    }

    /**
     * Set the GridController for this view.
     * @param gridController the GridController to set
     */
    public void setGridController(GridController gridController) {
        this.gridController = gridController;
    }
}