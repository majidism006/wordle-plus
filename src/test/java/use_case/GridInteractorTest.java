package use_case;

import data_access.repository.GameRepositoryImpl;
import data_access.repository.UserRepositoryImpl;
import entity.GameState;
import entity.GuessResult;
import interface_adapter.ViewManagerModel;
import interface_adapter.grid.GridPresenter;
import interface_adapter.grid.GridState;
import interface_adapter.grid.GridViewModel;
import interface_adapter.gameend.GameEndViewModel;
import interface_adapter.security.PasswordHasher;
import org.junit.jupiter.api.Test;
import use_case.grid.GameRepository;
import use_case.grid.GridInteractor;
import use_case.grid.GridOutputBoundary;
import use_case.service.UserService;

class GridInteractorTest {

    public static final String USERNAME = "username";
    public static final String WORD = "WORLD";
    public static final String GUESS = "GUESS";

    ViewManagerModel viewManagerModel = new ViewManagerModel();
    GridViewModel gridViewModel = new GridViewModel();
    GameEndViewModel gameEndViewModel = new GameEndViewModel();
    UserRepositoryImpl userRepository = new UserRepositoryImpl();
    PasswordHasher passwordHasher = new PasswordHasher();

    GridOutputBoundary gridOutputBoundary = new GridPresenter(viewManagerModel, gridViewModel, gameEndViewModel);
    GameRepositoryImpl gameRepository = new GameRepositoryImpl();
    UserService userService = new UserService(userRepository, passwordHasher);


    @Test
    void switchToGameEndViewTest() {
        //No need for testing
    }

    @Test
    void executeTest() {
        //No need for testing
    }

    @Test
    void checkGuessTest() {
        class TempInteractor extends GridInteractor {

            public TempInteractor(GridOutputBoundary outputBoundary, GameRepository gameRepository, UserService userService) {
                super(outputBoundary, gameRepository, userService);
            }

            public GuessResult checkGuess(GameState gameState, String guess) {
                // Reduce number of remaining attempts
                gameState.setRemainingAttempts(gameState.getRemainingAttempts() - 1);
                // Don't save for the test
                return gameState.checkGuess(guess);
            }
        }

        GridState gridState = new GridState();
        gridState.setTargetWord(WORD);
        GameState gameState = new GameState(gridState);
        TempInteractor tempInteractor = new TempInteractor(gridOutputBoundary, gameRepository, userService);

        assert !tempInteractor.checkGuess(gameState, GUESS).isCorrect();
        assert gameState.getRemainingAttempts() == 5;
        assert tempInteractor.checkGuess(gameState, WORD).isCorrect();
    }

    @Test
    void recordGameResultTest() {
        GridInteractor interactor = new GridInteractor(gridOutputBoundary, gameRepository, userService);
        userService.setCurrentUsername(USERNAME);
        int wins = userService.getUserWins(USERNAME);
        int losses = userService.getUserLosses(USERNAME);

        interactor.recordGameResult(true);
        assert userService.getUserWins(USERNAME) == wins + 1;
        assert userService.getUserLosses(USERNAME) == losses;

        interactor.recordGameResult(false);
        assert userService.getUserWins(USERNAME) == wins;
        assert userService.getUserLosses(USERNAME) == losses + 1;
    }

}