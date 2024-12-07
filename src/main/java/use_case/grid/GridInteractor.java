package use_case.grid;

import entity.GameState;
import entity.GuessResult;
import use_case.service.UserService;

/**
 * Interactor for Grid use case.
 */
public class GridInteractor implements GridInputBoundary {
    private final GridOutputBoundary gridPresenter;
    private final GameRepository gameRepository;
    private final UserService userService;

    public GridInteractor(GridOutputBoundary outputBoundary, GameRepository gameRepository, UserService userService) {
        this.gridPresenter = outputBoundary;
        this.gameRepository = gameRepository;
        this.userService = userService;
    }

    @Override
    public void switchToGameEndView() {
        gridPresenter.switchToGameEndView();
    }

    @Override
    public void execute(GridInputData gridInputData) {

    }

    @Override
    public GuessResult checkGuess(String guess) {
        // Retrieve the current game state
        GameState gameState = gameRepository.getGameState();
        // Reduce number of remaining attempts
        gameState.setRemainingAttempts(gameState.getRemainingAttempts() - 1);
        // Logic to check the guess
        GuessResult result = gameState.checkGuess(guess);
        // Save the updated game state
        gameRepository.saveGameState(gameState);

        return result;
    }

    @Override
    public void recordGameResult(boolean userWon) {
        String currentUser = userService.getCurrentUsername();
        if (userWon) {
            int currentWins = userService.getUserWins(currentUser);
            userService.setUserWins(currentUser, currentWins + 1);
        }
        else {
            int currentLosses = userService.getUserLosses(currentUser);
            userService.setUserLosses(currentUser, currentLosses + 1);
        }
    }

    @Override
    public void handleGuess(GridInputData inputData) {
        int row = inputData.getRow();
        String guessedWord = inputData.getGuess();
        // Domain logic for word checking
        GuessResult guessResult = checkGuess(guessedWord);

        GridOutputData outputData = new GridOutputData(
                row,
                guessResult
        );
        gridPresenter.presentOutput(outputData);
    }

}