package use_case.logout;

import use_case.service.UserService;

/**
 * The Logout Interactor.
 */
public class GameEndInteractor implements GameEndInputBoundary {
    private final UserService userService;
    private final GameEndOutputBoundary logoutPresenter;

    public GameEndInteractor(UserService userService,
                             GameEndOutputBoundary gameEndOutputBoundary) {
        this.userService = userService;
        this.logoutPresenter = gameEndOutputBoundary;
    }

    @Override
    public void execute(GameEndInputData gameEndInputData) {

        final String name = gameEndInputData.getUsername();
        userService.setCurrentUsername(null);
        final GameEndOutputData gameEndOutputData = new GameEndOutputData(name, false);
        logoutPresenter.prepareSuccessView(gameEndOutputData);
    }

    @Override
    public void switchToInstructionView() {
        logoutPresenter.switchToInstructionView();

    }
}

