package use_case.logout;

import entity.User;
import use_case.service.UserService;

public class LogoutInteractor implements LogoutInputBoundary {
    private UserService userService;
    private LogoutOutputBoundary logoutPresenter;

    public LogoutInteractor(UserService userService,
                            LogoutOutputBoundary logoutOutputBoundary) {
        this.userService = userService;
        this.logoutPresenter = logoutOutputBoundary;
    }

    @Override
    public void execute(LogoutInputData logoutInputData) {
//        final String name = logoutInputData.getUsername();
//        User user = userService.setCurrentUsername();// * set the username to null in the repository
//        final LogoutOutputData logoutOutputData = new LogoutOutputData(name, false);
//        if (user == null) {
//            logoutPresenter.prepareSuccessView(logoutOutputData);
//        }
        final String name = logoutInputData.getUsername();
        userService.setCurrentUsername(null);
        final LogoutOutputData logoutOutputData = new LogoutOutputData(name, false);
        logoutPresenter.prepareSuccessView(logoutOutputData);
    }

    @Override
    public void switchTogridView() { logoutPresenter.switchTogridView();}
}
