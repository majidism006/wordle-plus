package view;

import interface_adapter.grid.GridController;
import interface_adapter.grid.GridState;
import interface_adapter.grid.GridViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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

        // Initialize grid cells with DocumentListeners for user input
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

                cell.getDocument().addDocumentListener(new CellDocumentListener(row, col, cell));
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
    }

    /**
     * Updates the grid display based on the provided GridState.
     */
    private void updateGrid(GridState state) {
        for (int row = 0; row < gridCells.length; row++) {
            for (int col = 0; col < gridCells[row].length; col++) {
                String letter = state.getCell(row, col);
                gridCells[row][col].setText(letter);
            }
        }
    }

    /**
     * Helper class to listen to changes in individual grid cells.
     */
    private class CellDocumentListener implements DocumentListener {
        private final int row;
        private final int col;
        private final JTextField cell;

        public CellDocumentListener(int row, int col, JTextField cell) {
            this.row = row;
            this.col = col;
            this.cell = cell;

            // Key listener to handle backspace and Enter key functionality
            cell.addKeyListener(new java.awt.event.KeyAdapter() {
                @Override
                public void keyPressed(java.awt.event.KeyEvent e) {
                    if (e.getKeyCode() == java.awt.event.KeyEvent.VK_BACK_SPACE && cell.getText().isEmpty()) {
                        // Move to the previous cell on Backspace if the current cell is empty
                        if (col > 0) {
                            gridCells[row][col - 1].requestFocus();
                        }
                    }
                    // TODO: implement game logic here, right now it just goes to logout when row is filled and ener is pressed
                    else if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                        // If Enter is pressed after filling the row, switch to the logout view
                        handleEnterKey(row);
                    }
                }
            });
        }

        private void documentListenerHelper() {
            String text = cell.getText().toUpperCase(); // Convert input to uppercase
            if (text.length() > 1) {
                cell.setText(text.substring(0, 1)); // Limit input to 1 letter per cell
            }

            gridController.execute(row, col, cell.getText()); // Send input to the controller

            // Move to the next cell if current cell is filled
            if (!cell.getText().isEmpty() && col < gridCells[row].length - 1) {
                    gridCells[row][col + 1].requestFocus();
                }

        }

        private void handleEnterKey(int row) {
            if (isRowComplete(row)) {
                gridController.switchToLogoutView(); // Switch to logout view when row is complete
            }
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

        @Override
        public void insertUpdate(DocumentEvent e) {
            documentListenerHelper();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            documentListenerHelper();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            documentListenerHelper();
        }
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
