package interface_adapter.profile;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ProfileViewModel extends ViewModel<ProfileState> {
    private final PropertyChangeSupport support;
    private int wins;
    private int losses;
    public ProfileViewModel() {
        super("profile");
        this.support = new PropertyChangeSupport(this);
        setState(new ProfileState());
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
