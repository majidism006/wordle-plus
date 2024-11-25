package interface_adapter.grid;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The View Model for the Grid View.
 */
public class GridViewModel extends ViewModel<GridState> {

    private final PropertyChangeSupport support;
    private String targetWord;



    public GridViewModel() {
        super("grid");
        setState(new GridState());
        this.support = new PropertyChangeSupport(this);
    }

    public void setTargetWord(String targetWord) {
        this.targetWord = targetWord;
    }

    public String getTargetWord() {
        return targetWord;
    }

    public String getViewName() {
        return "grid";
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public void setCellContent(int row, int col, String content) {
        getState().setCellContent(row, col, content);
        support.firePropertyChange("cellContent", null, getState());
    }

    public void setCellCorrectPosition(int row, int col, boolean isCorrect) {
        // Assuming GridState has a method to set correct position
        getState().setCellCorrectPosition(row, col, isCorrect);
        support.firePropertyChange("cellCorrectPosition", null, getState());
    }

    public void setCellCorrectLetter(int row, int col, boolean isCorrect) {
        // Assuming GridState has a method to set correct letter
        getState().setCellCorrectLetter(row, col, isCorrect);
        support.firePropertyChange("cellCorrectLetter", null, getState());
    }

    public boolean isCellCorrectPosition(int row, int col) {
        return getState().isCellCorrectPosition(row, col);
    }

    public boolean isCellCorrectLetter(int row, int col) {
        return getState().isCellCorrectLetter(row, col);
    }


    public void setGameOver(boolean isGameOver) {
        // TODO: to be implemented
    }
}