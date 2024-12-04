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

    /**
     * Sets the content of a specific cell in the grid and notifies listeners of the change.
     *
     * @param row the row index of the cell
     * @param col the column index of the cell
     * @param content the content to set in the cell
     */
    public void setCellContent(int row, int col, String content) {
        getState().setCellContent(row, col, content);
        support.firePropertyChange("cellContent", null, getState());
    }

    /**
     * Sets whether a specific cell is in the correct position and notifies listeners of the change.
     *
     * @param row the row index of the cell
     * @param col the column index of the cell
     * @param isCorrect true if the cell is in the correct position, false otherwise
     */
    public void setCellCorrectPosition(int row, int col, boolean isCorrect) {
        getState().setCellCorrectPosition(row, col, isCorrect);
        support.firePropertyChange("cellCorrectPosition", null, getState());
    }

    /**
     * Sets whether a specific cell contains the correct letter and notifies listeners of the change.
     *
     * @param row the row index of the cell
     * @param col the column index of the cell
     * @param isCorrect true if the cell contains the correct letter, false otherwise
     */
    public void setCellCorrectLetter(int row, int col, boolean isCorrect) {
        getState().setCellCorrectLetter(row, col, isCorrect);
        support.firePropertyChange("cellCorrectLetter", null, getState());
    }

    /**
     * Checks if a specific cell is in the correct position.
     *
     * @param row the row index of the cell
     * @param col the column index of the cell
     * @return true if the cell is in the correct position, false otherwise
     */
    public boolean isCellCorrectPosition(int row, int col) {
        return getState().isCellCorrectPosition(row, col);
    }

    /**
     * Checks if a specific cell contains the correct letter.
     *
     * @param row the row index of the cell
     * @param col the column index of the cell
     * @return true if the cell contains the correct letter, false otherwise
     */
    public boolean isCellCorrectLetter(int row, int col) {
        return getState().isCellCorrectLetter(row, col);
    }

    /**
     * Resets the grid to its initial state and notifies listeners of the change.
     */
    public void resetGrid() {
        setState(new GridState());
        support.firePropertyChange("reset", null, getState());
    }
}
