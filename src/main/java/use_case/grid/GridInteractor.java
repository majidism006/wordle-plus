package use_case.grid;

import entity.GameState;
import entity.GuessResult;
import use_case.service.UserService;

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
    public void switchToLogoutView() {
        gridPresenter.switchToLogoutView();
    }

    @Override
    public void execute(GridInputData gridInputData) {
        GuessResult result = checkGuess(gridInputData.getGuess());
        gridPresenter.presentGuessResult(result);
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
    public void recordGameResult(boolean userWon){
        String currentUser = userService.getCurrentUsername();
        if(userWon){
            int currentWins = userService.getUserWins(currentUser);
            userService.setUserWins(currentUser, currentWins+1);
        }
        else{
            int currentLosses = userService.getUserLosses(currentUser);
            userService.setUserLosses(currentUser, currentLosses+1);
        }
    }



}