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
import use_case.grid.GridInputData;
import use_case.grid.GridInteractor;
import use_case.grid.GridOutputBoundary;
import use_case.service.UserService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

class GridInteractorTest {

    public static final String USERNAME = "username";
    public static final String WORD = "WORLD";
    public static final String GUESS = "GUESS";

    UserRepositoryImpl userRepository = new UserRepositoryImpl();
    PasswordHasher passwordHasher = new PasswordHasher();
    UserService userService = new UserService(userRepository, passwordHasher);
    GameRepositoryImpl gameRepository = new GameRepositoryImpl();

    @Test
    void switchToGameEndViewTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        GridViewModel gridViewModel = new GridViewModel();
        GameEndViewModel gameEndViewModel = new GameEndViewModel();
        GridOutputBoundary gridOutputBoundary = new GridPresenter(viewManagerModel, gridViewModel, gameEndViewModel);
        GridInteractor interactor = new GridInteractor(gridOutputBoundary, gameRepository, userService);

        interactor.switchToGameEndView();

        assertEquals("game end", viewManagerModel.getState());
    }

    //TODO: need to be edited
    @Test
    void executeTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        GridViewModel gridViewModel = new GridViewModel();
        GameEndViewModel gameEndViewModel = new GameEndViewModel();
        GridOutputBoundary gridOutputBoundary = new GridPresenter(viewManagerModel, gridViewModel, gameEndViewModel);

        GridInteractor interactor = new GridInteractor(gridOutputBoundary, gameRepository, userService);

        GridState gridState = new GridState();
        gridState.setTargetWord("ABBBB");
        GameState gameState = new GameState(gridState);
        gameRepository.saveGameState(gameState);
        gridViewModel.setState(gridState);

        GridState previousState = gridViewModel.getState();
        GridInputData inputData = new GridInputData(0,0, "A");
        gridViewModel.setCellContent(0,0, "A");
        interactor.execute(inputData);
        assertEquals(previousState, gridViewModel.getState());

//        for(int i = 0; i < 4; i++) {
//            inputData = new GridInputData(0, 1, "B");
//            interactor.execute(inputData);
//        }
//
//        assertNotEquals(previousState, gridViewModel.getState());
    }

    @Test
    void checkGuessTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        GridViewModel gridViewModel = new GridViewModel();
        GameEndViewModel gameEndViewModel = new GameEndViewModel();
        GridOutputBoundary gridOutputBoundary = new GridPresenter(viewManagerModel, gridViewModel, gameEndViewModel);

        GridInteractor interactor = new GridInteractor(gridOutputBoundary, gameRepository, userService);

        GridState gridState = new GridState();
        gridState.setTargetWord(WORD);
        GameState gameState = new GameState(gridState);
        gameRepository.saveGameState(gameState);

        assert !interactor.checkGuess(GUESS).isCorrect();
        assert gameRepository.getGameState().getRemainingAttempts() == 5;
        assert interactor.checkGuess(WORD).isCorrect();
    }

    @Test
    void recordGameResultTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        GridViewModel gridViewModel = new GridViewModel();
        GameEndViewModel gameEndViewModel = new GameEndViewModel();
        GridOutputBoundary gridOutputBoundary = new GridPresenter(viewManagerModel, gridViewModel, gameEndViewModel);
        GridInteractor interactor = new GridInteractor(gridOutputBoundary, gameRepository, userService);

        userService.setCurrentUsername(USERNAME);
        int wins = userService.getUserWins(USERNAME);
        int losses = userService.getUserLosses(USERNAME);

        interactor.recordGameResult(true);
        assert userService.getUserWins(USERNAME) == wins + 1;
        assert userService.getUserLosses(USERNAME) == losses;

        wins = userService.getUserWins(USERNAME);
        losses = userService.getUserLosses(USERNAME);

        interactor.recordGameResult(false);
        assert userService.getUserWins(USERNAME) == wins;
        assert userService.getUserLosses(USERNAME) == losses + 1;
    }

}