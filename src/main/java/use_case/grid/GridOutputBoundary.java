package use_case.grid;


import entity.GuessResult;

public interface GridOutputBoundary {
    void switchToLogoutView();

    void presentGuessResult(GuessResult result);
}
