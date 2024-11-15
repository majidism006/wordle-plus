package interface_adapter.logout;

import interface_adapter.ViewManagerModel;

import interface_adapter.grid.GridViewModel;

import interface_adapter.loggedin.LoggedInState;
import interface_adapter.loggedin.LoggedInViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutOutputBoundary;
import use_case.logout.LogoutOutputData;

public class LogoutPresenter implements LogoutOutputBoundary {

    private LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;
    private GridViewModel gridViewModel;

    public LogoutPresenter(ViewManagerModel viewManagerModel,
                           LoggedInViewModel loggedInViewModel,
                           LoginViewModel loginViewModel,
                           GridViewModel gridViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.gridViewModel = gridViewModel;

    }

    @Override
    public void prepareSuccessView(LogoutOutputData response) {

        final LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUsername("");
        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();

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
