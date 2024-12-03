package use_case;

import data_access.repository.UserRepositoryImpl;
import interface_adapter.gameend.GameEndState;
import interface_adapter.history.HistoryPresenter;
import interface_adapter.gameend.GameEndViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.security.PasswordHasher;
import org.junit.jupiter.api.Test;
import use_case.history.HistoryInputBoundary;
import use_case.history.HistoryInputData;
import use_case.history.HistoryInteractor;
import use_case.history.HistoryOutputBoundary;
import use_case.service.UserService;

import static org.junit.Assert.assertEquals;

public class HistoryInteractorTest {

    public static final String USERNAME = "username";
    public static final String TEXT = "Only for testing";
    public static final String TEXT2 = "another text for testing";

    UserRepositoryImpl userRepository = new UserRepositoryImpl();
    PasswordHasher passwordHasher = new PasswordHasher();
    UserService userService = new UserService(userRepository, passwordHasher);

    @Test
    void updateStatusTest() {
        GameEndViewModel gameEndViewModel = new GameEndViewModel();
        ProfileViewModel profileViewModel = new ProfileViewModel();
        HistoryInputData historyInputData = new HistoryInputData(USERNAME,TEXT);
        HistoryOutputBoundary historyOutputBoundary = new HistoryPresenter(gameEndViewModel, profileViewModel);
        HistoryInputBoundary historyInteractor = new HistoryInteractor(userService, historyOutputBoundary);

        historyInteractor.updateStatus(historyInputData);

        assertEquals(TEXT, userService.getStatus(USERNAME));
    }

    //TODO: check coverage after connection works
    @Test
    void executeTest() {
        GameEndViewModel gameEndViewModel = new GameEndViewModel();
        ProfileViewModel profileViewModel = new ProfileViewModel();
        HistoryInputData historyInputData = new HistoryInputData(USERNAME,TEXT2);
        HistoryOutputBoundary historyOutputBoundary = new HistoryPresenter(gameEndViewModel, profileViewModel);
        HistoryInputBoundary historyInteractor = new HistoryInteractor(userService, historyOutputBoundary);

        historyInteractor.execute(historyInputData);

        assertEquals(TEXT2, userService.getStatus(USERNAME));
//        int wins = userService.getUserWins(USERNAME);
//        int losses = userService.getUserLosses(USERNAME);
//        GameEndState gameEndState = new GameEndState();
//        assertEquals(gameEndState,gameEndViewModel.getState());
    }
}
