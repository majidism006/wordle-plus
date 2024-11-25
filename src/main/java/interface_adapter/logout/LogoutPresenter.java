package interface_adapter.logout;

import interface_adapter.ViewManagerModel;

import interface_adapter.grid.GridViewModel;
import interface_adapter.instructions.InstructionsViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

import use_case.logout.LogoutOutputBoundary;
import use_case.logout.LogoutOutputData;

public class LogoutPresenter implements LogoutOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private final InstructionsViewModel instructionsViewModel;
    private final LogoutViewModel logoutViewModel;
    private final GridViewModel gridViewModel;

    public LogoutPresenter(ViewManagerModel viewManagerModel,
                           LoginViewModel loginViewModel,
                           InstructionsViewModel instructionsViewModel,
                           LogoutViewModel logoutViewModel,
                           GridViewModel gridViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.instructionsViewModel = instructionsViewModel;
        this.logoutViewModel = logoutViewModel;
        this.gridViewModel = gridViewModel;

    }

    @Override
    public void prepareSuccessView(LogoutOutputData response) {

        final LoginState loginState = loginViewModel.getState();
        loginState.setUsername("");
        loginState.setPassword("");
        this.loginViewModel.setState(loginState);
        this.loginViewModel.firePropertyChanged();
    }

    @Override
    public void switchToInstructionView() {
        gridViewModel.resetGrid();
        viewManagerModel.setState(instructionsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void loadUserHistory(int win, int loss) {
//        final LogoutState logoutState = logoutViewModel.getState();
////        final LoginState loginState = loginViewModel.getState();
//        String name = logoutState.getUsername();
////        logoutState.setUsername(name);
        this.logoutViewModel.getUserHistory(win, loss);
    }
}
