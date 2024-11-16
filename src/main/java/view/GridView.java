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

        setLayout(new GridLayout(rows, cols, 5, 5));

        // Initialize grid cells with DocumentListeners for user input
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                JTextField cell = new JTextField(1); // Each cell holds one letter
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setFont(new Font("Arial", Font.BOLD, 18));
                cell.getDocument().addDocumentListener(new CellDocumentListener(row, col, cell));
                gridCells[row][col] = cell;
                add(cell);
            }
        }
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
        }

        private void documentListenerHelper() {
            String letter = cell.getText().toUpperCase();
            if (letter.length() > 1) {
                cell.setText(letter.substring(0, 1)); // Limit input to one letter
            }
            gridController.execute(row, col, cell.getText()); // Send input to the controller
            gridController.switchToLogoutView(); // TODO: Remove this and implement an actual game end checker
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
