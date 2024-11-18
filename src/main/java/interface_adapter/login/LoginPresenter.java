package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import interface_adapter.grid.GridState;
import interface_adapter.instructions.InstructionsViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

import interface_adapter.signup.SignupViewModel;
import interface_adapter.grid.GridViewModel;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private final SignupViewModel signupViewModel;
    private final GridViewModel gridViewModel;
    private final InstructionsViewModel instructionsViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoginViewModel loginViewModel, SignupViewModel signupViewModel, GridViewModel gridViewModel, InstructionsViewModel instructionsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;
        this.gridViewModel = gridViewModel;
        this.instructionsViewModel = instructionsViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the Instructions view.


        this.viewManagerModel.setState(instructionsViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }
    @Override
    public void switchToSignupView() {
        viewManagerModel.setState(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToInstructionsView() {
        viewManagerModel.setState(instructionsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
