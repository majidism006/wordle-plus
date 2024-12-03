package interface_adapter.grid;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import interface_adapter.ViewModel;

/**
 * The View Model for the Grid View.
 */
public class GridViewModel extends ViewModel<GridState> {

    private final PropertyChangeSupport support;
    private String targetWord;

    /**
     * Constructs a new GridViewModel.
     * Initializes the PropertyChangeSupport and sets the initial state.
     */
    public GridViewModel() {
        super("grid");
        setState(new GridState());
        this.support = new PropertyChangeSupport(this);
    }

    /**
     * Sets the target word for the grid.
     *
     * @param targetWord the target word to set
     */
    public void setTargetWord(String targetWord) {
        this.targetWord = targetWord;
    }

    /**
     * Gets the target word for the grid.
     *
     * @return the target word
     */
    public String getTargetWord() {
        return targetWord;
    }

    /**
     * Gets the name of the view associated with this ViewModel.
     *
     * @return the name of the view
     */
    public String getViewName() {
        return "grid";
    }

    /**
     * Adds a PropertyChangeListener to the listener list.
     *
     * @param listener the PropertyChangeListener to be added
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Removes a PropertyChangeListener from the listener list.
     *
     * @param listener the PropertyChangeListener to be removed
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public void notifyListeners(String propertyName, Object oldValue, Object newValue) {
        support.firePropertyChange(propertyName, oldValue, newValue);
    }

    /**
     * Sets the content and color of a specific cell in the grid and notifies listeners.
     *
     * @param row the row index of the cell
     * @param col the column index of the cell
     * @param content the content to set in the cell
     * @param isCorrectPosition true if the cell is in the correct position
     * @param isCorrectLetter true if the cell contains the correct letter
     */
    public void setCell(int row, int col, String content, boolean isCorrectPosition, boolean isCorrectLetter) {
        getState().setCellContent(row, col, content);
        getState().setCellCorrectPosition(row, col, isCorrectPosition);
        getState().setCellCorrectLetter(row, col, isCorrectLetter);

        // Fire change for both content and appearance
        support.firePropertyChange("cellUpdate", null, getState());
    }

    /**
     * Resets the grid to its initial state and notifies listeners of the change.
     */
    public void resetGrid() {
        setState(new GridState());
        support.firePropertyChange("reset", null, getState());
    }

    /**
     * Applies coloring logic based on correctness flags for UI purposes.
     * This method maps the correctness flags to color values that can
     * be used by the view layer.
     *
     * @param isCorrectPosition true if the cell is in the correct position
     * @param isCorrectLetter true if the cell contains the correct letter
     * @return the corresponding color as a string (e.g., "green", "yellow", "grey")
     */
    public String getCellColor(boolean isCorrectPosition, boolean isCorrectLetter) {
        if (isCorrectPosition) {
            return "green";
        } else if (isCorrectLetter) {
            return "yellow";
        } else {
            return "grey";
        }
    }

}
