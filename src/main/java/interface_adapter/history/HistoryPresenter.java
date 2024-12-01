package interface_adapter.history;

import interface_adapter.logout.GameEndViewModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import use_case.History.HistoryOutputBoundary;
import use_case.History.HistoryOutputData;

public class HistoryPresenter implements HistoryOutputBoundary {

    private final GameEndViewModel gameEndViewModel;
    private final ProfileViewModel profileViewModel;


    public HistoryPresenter(GameEndViewModel gameEndViewModel, ProfileViewModel profileViewModel) {

        this.gameEndViewModel = gameEndViewModel;
        this.profileViewModel = profileViewModel;
    }

    @Override
    public void prepareSuccessView(HistoryOutputData historyOutputData) {
        int win = historyOutputData.getWin();
        int loss = historyOutputData.getLoss();
        String state = historyOutputData.getState();
        double winrates = (win + loss > 0) ? ((double) win / (win + loss)) * 100 : 0;
        final ProfileState currentstate = profileViewModel.getState();
        currentstate.setState(state);
        currentstate.setWin(win);
        currentstate.setLoss(loss);
        profileViewModel.firePropertyChange("wins", null, win);
        profileViewModel.firePropertyChange("losses", null, loss);
        profileViewModel.firePropertyChange("status", null, state);

        gameEndViewModel.firePropertyChange("wins", null, win);
        gameEndViewModel.firePropertyChange("losses", null, loss);
        gameEndViewModel.firePropertyChange("winRate", null, winrates);
    }
}
