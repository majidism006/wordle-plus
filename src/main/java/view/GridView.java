package view;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import entity.GuessResult;
import interface_adapter.grid.GridController;
import interface_adapter.grid.GridState;
import interface_adapter.grid.GridViewModel;

public class GridView extends JPanel {

    private static final String viewName = "grid";
    private final GridViewModel gridViewModel;
    private final JTextField[][] gridCells;
    private GridController gridController;

    public GridView(GridViewModel gridViewModel) {
        this.gridViewModel = gridViewModel;
        gridViewModel.addPropertyChangeListener(new GridViewListener());

        int rows = 6;
        int cols = 5;
        gridCells = new JTextField[rows][cols];

        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        JLabel titleLabel = new JLabel("WORDLE!!!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(rows, cols, 10, 10));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        gridPanel.setBackground(Color.BLACK);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                JTextField cell = new JTextField(1);
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setFont(new Font("Arial", Font.BOLD, 18));
                cell.setBackground(Color.BLACK);
                cell.setForeground(Color.WHITE);
                cell.setCaretColor(Color.WHITE);
                cell.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                ((AbstractDocument) cell.getDocument()).setDocumentFilter(new UppercaseDocumentFilter(row, col));

                int finalRow = row;
                int finalCol = col;
                cell.addKeyListener(new java.awt.event.KeyAdapter() {
                    @Override
                    public void keyPressed(java.awt.event.KeyEvent e) {
                        if (e.getKeyCode() == java.awt.event.KeyEvent.VK_BACK_SPACE && cell.getText().isEmpty()) {
                            if (finalCol > 0) {
                                gridCells[finalRow][finalCol - 1].requestFocus();
                            }
                        } else if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                            handleEnterKey(finalRow);
                        }
                    }
                });

                gridCells[row][col] = cell;
                gridPanel.add(cell);
            }
        }
        add(gridPanel, BorderLayout.CENTER);
    }

    private void updateCell(int row, int col, String content, Color color) {
        JTextField cell = gridCells[row][col];
        cell.setText(content);
        cell.setBackground(color);
    }

    private Color mapColor(String colorName) {
        return switch (colorName.toLowerCase()) {
            case "green" -> Color.GREEN;
            case "yellow" -> Color.YELLOW;
            case "grey" -> Color.GRAY;
            default -> Color.BLACK;
        };
    }

    public void clear() {
        for (int row = 0; row < gridCells.length; row++) {
            for (int col = 0; col < gridCells[row].length; col++) {
                updateCell(row, col, "", Color.BLACK);
            }
        }
    }

    private class GridViewListener implements PropertyChangeListener {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if ("cellUpdate".equals(evt.getPropertyName())) {
                GridState state = (GridState) evt.getNewValue();
                int currentRow = state.getCurrentRow();

                for (int col = 0; col < 5; col++) {
                    String content = state.getCellContent(currentRow, col);
                    String colorName = gridViewModel.getCellColor(
                            state.isCellCorrectPosition(currentRow, col),
                            state.isCellCorrectLetter(currentRow, col)
                    );
                    Color color = mapColor(colorName);
                    updateCell(currentRow, col, content, color);
                }
            } else if ("reset".equals(evt.getPropertyName())) {
                clear();
            }
        }
    }

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

    private boolean isValidCharacter(String text) {
        for (char c : text.toCharArray()) {
            if (!Character.isLetter(c)) {
                Toolkit.getDefaultToolkit().beep();
                return false;
            }
        }
        return true;
    }

    private void handleEnterKey(int row) {
        if (isRowComplete(row)) {
            String guessedWord = getRowWord(row);
            gridController.submitGuess(row, guessedWord);
            GuessResult guessResult = gridController.checkWord(guessedWord);

            if (guessResult.isCorrect()) {
                gridController.recordGameResult(true);
                JOptionPane.showMessageDialog(this, "Congratulations! You guessed the word!");
                gridController.switchToGameEndView();
            } else if (row == gridCells.length - 1) {
                gridController.recordGameResult(false);
                JOptionPane.showMessageDialog(this, "Game Over! Try again!");
                gridController.switchToGameEndView();
            } else {
                focusNextRow(row + 1);
            }
        }
    }

    private boolean isRowComplete(int row) {
        for (int col = 0; col < gridCells[row].length; col++) {
            if (gridCells[row][col].getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private String getRowWord(int row) {
        StringBuilder guessedWord = new StringBuilder();
        for (int col = 0; col < gridCells[row].length; col++) {
            guessedWord.append(gridCells[row][col].getText());
        }
        return guessedWord.toString();
    }

    private void focusNextRow(int nextRow) {
        if (nextRow < gridCells.length) {
            gridCells[nextRow][0].requestFocus();
        }
    }

    public void setGridController(GridController gridController) {
        this.gridController = gridController;
    }

    public String getViewName() {
        return viewName;
    }
}
