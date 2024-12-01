package interface_adapter.logout;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GameEndViewModel extends ViewModel<GameEndState> {
    private final PropertyChangeSupport support;

    public GameEndViewModel() {
        super("game end");
        this.support = new PropertyChangeSupport(this);
        setState(new GameEndState());
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        support.firePropertyChange(propertyName, oldValue, newValue);
    }
}
