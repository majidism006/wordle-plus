package interface_adapter.logout;

import interface_adapter.ViewManagerModel;

import interface_adapter.gameend.GameEndViewModel;
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
    private final GameEndViewModel gameEndViewModel;
    private final GridViewModel gridViewModel;

    public LogoutPresenter(ViewManagerModel viewManagerModel,
                           LoginViewModel loginViewModel,
                           InstructionsViewModel instructionsViewModel,
                           GameEndViewModel gameEndViewModel,
                           GridViewModel gridViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.instructionsViewModel = instructionsViewModel;
        this.gameEndViewModel = gameEndViewModel;
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
}
