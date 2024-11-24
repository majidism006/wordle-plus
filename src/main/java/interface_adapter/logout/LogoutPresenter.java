package interface_adapter.logout;

import interface_adapter.ViewManagerModel;

import interface_adapter.grid.GridViewModel;

import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

import use_case.logout.LogoutOutputBoundary;
import use_case.logout.LogoutOutputData;

public class LogoutPresenter implements LogoutOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private final GridViewModel gridViewModel;

    public LogoutPresenter(ViewManagerModel viewManagerModel,
                           LoginViewModel loginViewModel,
                           GridViewModel gridViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
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

    public void switchTogridView(){
        viewManagerModel.setState(gridViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
