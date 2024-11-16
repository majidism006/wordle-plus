package interface_adapter.logout;

import interface_adapter.ViewManagerModel;

import interface_adapter.grid.GridViewModel;

import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

import use_case.logout.LogoutOutputBoundary;
import use_case.logout.LogoutOutputData;

public class LogoutPresenter implements LogoutOutputBoundary {

    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;
    private GridViewModel gridViewModel;

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


        // This code tells the View Manager to switch to the LoginView.

        this.viewManagerModel.setState(loginViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    public void switchTogridView(){
        viewManagerModel.setState(gridViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
