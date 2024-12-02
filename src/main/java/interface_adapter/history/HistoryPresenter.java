package interface_adapter.history;

import interface_adapter.gameend.GameEndViewModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import use_case.history.HistoryOutputBoundary;
import use_case.history.HistoryOutputData;

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
        String state = historyOutputData.getStatus();
        double winrates = (win + loss > 0) ? ((double) win / (win + loss)) * 100 : 0;
        final ProfileState currentstate = profileViewModel.getState();
        currentstate.setStatus(state);
        currentstate.setWin(win);
        currentstate.setLoss(loss);

        gameEndViewModel.firePropertyChange("wins", null, win);
        gameEndViewModel.firePropertyChange("losses", null, loss);
        gameEndViewModel.firePropertyChange("winRate", null, winrates);
    }

    @Override
    public void updateStatus(HistoryOutputData historyOutputData) {

        int win = historyOutputData.getWin();
        int loss = historyOutputData.getLoss();
        String state = historyOutputData.getStatus();
        final ProfileState currentstate = profileViewModel.getState();
        currentstate.setStatus(state);
        currentstate.setWin(win);
        currentstate.setLoss(loss);

        profileViewModel.firePropertyChange("status", null, state);

    }

}
