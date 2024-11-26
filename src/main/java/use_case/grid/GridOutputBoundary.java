package use_case.grid;


import entity.GuessResult;

public interface GridOutputBoundary {
    void switchToGameEndView();

    void presentGuessResult(GuessResult result);
}
