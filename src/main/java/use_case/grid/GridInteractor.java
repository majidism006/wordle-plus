package use_case.grid;

import entity.GameState;
import entity.GuessResult;

public class GridInteractor implements GridInputBoundary {
    private final GridOutputBoundary gridPresenter;
    private final GameRepository gameRepository;

    public GridInteractor(GridOutputBoundary outputBoundary, GameRepository gameRepository) {
        this.gridPresenter = outputBoundary;
        this.gameRepository = gameRepository;
    }

    @Override
    public void switchToLogoutView() {
        gridPresenter.switchToLogoutView();
    }

    @Override
    public void execute(GridInputData gridInputData) {
        GuessResult result = checkGuess(gridInputData.getGuess());
        gridPresenter.presentGuessResult(result);
    }
    private GuessResult checkGuess(String guess) {
        // Retrieve the current game state
        GameState gameState = gameRepository.getGameState();

        // Logic to check the guess
        GuessResult result = gameState.checkGuess(guess);

        // Save the updated game state
        gameRepository.saveGameState(gameState);

        return result;
    }

}