package interface_adapter.profile;

import interface_adapter.ViewModel;
import interface_adapter.logout.GameEndState;
import use_case.History.HistoryOutputData;
import view.ProfileView;

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

    public void loadUserHistory(HistoryOutputData historyOutputData) {
        this.wins = historyOutputData.getWin();
        this.losses = historyOutputData.getLoss();

        firePropertyChange("wins", null, this.wins);
        firePropertyChange("losses", null, this.losses);
    }

    public void updateUserStatus(String text) {
    }
}
