package interface_adapter.gameend;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import interface_adapter.ViewModel;

/**
 * ViewModel class for managing the state and behavior at the end of a game.
 */
public class GameEndViewModel extends ViewModel<GameEndState> {
    private final PropertyChangeSupport support;

    /**
     * Constructs a new GameEndViewModel.
     * Initializes the PropertyChangeSupport and sets the initial state.
     */
    public GameEndViewModel() {
        super("game end");
        this.support = new PropertyChangeSupport(this);
        setState(new GameEndState());
    }

    /**
     * Adds a PropertyChangeListener to the listener list.
     *
     * @param listener the PropertyChangeListener to be added
     */
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
     * Fires a property change event to notify listeners of a change.
     *
     * @param propertyName the name of the property that changed
     * @param oldValue the old value of the property
     * @param newValue the new value of the property
     */
    public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        support.firePropertyChange(propertyName, oldValue, newValue);
    }
}
