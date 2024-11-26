package interface_adapter.logout;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import use_case.logout.GameEndInputBoundary;
import use_case.logout.GameEndInputData;

public class GameEndController {
    private static final Logger log = LoggerFactory.getLogger(GameEndController.class);
    private final GameEndInputBoundary gameEndInteractor;


    public GameEndController(GameEndInputBoundary gameEndInteractor) {
        this.gameEndInteractor = gameEndInteractor;
    }


    /**
     * Executes the Logout Use Case.
     * @param username the username of the user logging in
     */
    public void execute(String username) {
        final GameEndInputData gameEndInputData = new GameEndInputData(username);
        gameEndInteractor.execute(gameEndInputData);
    }


    public void switchToInstructionView() {
        gameEndInteractor.switchToInstructionView();
    }

    public void getUserHistory() {
        gameEndInteractor.loadUserHistory();
    }
}
