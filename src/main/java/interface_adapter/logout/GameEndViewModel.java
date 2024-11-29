package interface_adapter.logout;

import interface_adapter.ViewModel;
import use_case.History.HistoryOutputData;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GameEndViewModel extends ViewModel<GameEndState> {
    private final PropertyChangeSupport support;
    private int wins;
    private int losses;
    private double winRate;

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

    public void loadUserHistory(int wins, int losses) {
        this.wins = wins;
        this.losses = losses;
        this.winRate = (wins + losses > 0) ? ((double) wins / (wins + losses)) * 100 : 0;

        firePropertyChange("wins", null, this.wins);
        firePropertyChange("losses", null, this.losses);
        firePropertyChange("winRate", null, this.winRate);
    }

    public void loadUserHistory(HistoryOutputData historyOutputData) {
        this.wins = historyOutputData.getWin();
        this.losses = historyOutputData.getLoss();
        this.winRate = (wins + losses > 0) ? ((double) wins / (wins + losses)) * 100 : 0;

        firePropertyChange("wins", null, this.wins);
        firePropertyChange("losses", null, this.losses);
        firePropertyChange("winRate", null, this.winRate);
    }
}
