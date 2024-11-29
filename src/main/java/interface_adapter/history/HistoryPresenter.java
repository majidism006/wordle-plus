package interface_adapter.history;

import interface_adapter.logout.GameEndViewModel;
import use_case.History.HistoryOutputBoundary;
import use_case.History.HistoryOutputData;

public class HistoryPresenter implements HistoryOutputBoundary {

    private final GameEndViewModel gameEndViewModel;


    public HistoryPresenter(GameEndViewModel gameEndViewModel) {

        this.gameEndViewModel = gameEndViewModel;
    }


    @Override
    public void prepareSuccessView(HistoryOutputData historyOutputData) {
        gameEndViewModel.loadUserHistory(historyOutputData);
    }
}
