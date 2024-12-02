package use_case;

import data_access.repository.UserRepositoryImpl;
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
    public static final String TEXT = "Only for tesing";

    UserRepositoryImpl userRepository = new UserRepositoryImpl();
    PasswordHasher passwordHasher = new PasswordHasher();
    UserService userService = new UserService(userRepository, passwordHasher);

    GameEndViewModel gameEndViewModel = new GameEndViewModel();
    ProfileViewModel profileViewModel = new ProfileViewModel();

    @Test
    void updateStatusTest() {
        HistoryInputData historyInputData = new HistoryInputData(USERNAME,TEXT);
        HistoryOutputBoundary historyOutputBoundary = new HistoryPresenter(gameEndViewModel, profileViewModel);
        HistoryInputBoundary historyInteractor = new HistoryInteractor(userService, historyOutputBoundary);
        historyInteractor.updateStatus(historyInputData);
        assertEquals(TEXT, userService.getStatus(USERNAME));
    }
}
