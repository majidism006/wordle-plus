package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import interface_adapter.discussion.DiscussionPostState;
import interface_adapter.discussion.DiscussionPostViewModel;
import interface_adapter.grid.GridState;
import interface_adapter.instructions.InstructionsViewModel;
import interface_adapter.logout.GameEndState;
import interface_adapter.logout.GameEndViewModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

import interface_adapter.signup.SignupViewModel;
import interface_adapter.grid.GridViewModel;
import view.GameEndView;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private final SignupViewModel signupViewModel;
    private final InstructionsViewModel instructionsViewModel;
    private final GameEndViewModel gameEndViewModel;
    private final DiscussionPostViewModel discussionPostViewModel;
    private final ProfileViewModel profileViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoginViewModel loginViewModel, SignupViewModel signupViewModel, InstructionsViewModel instructionsViewModel,
                          GameEndViewModel gameEndViewModel,
                          DiscussionPostViewModel discussionPostViewModel, ProfileViewModel profileViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;
        this.instructionsViewModel = instructionsViewModel;
        this.gameEndViewModel = gameEndViewModel;
        this.discussionPostViewModel = discussionPostViewModel;
        this.profileViewModel = profileViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the Instructions view.
        final GameEndState gameEndState = gameEndViewModel.getState();
        final DiscussionPostState discussionPostState = discussionPostViewModel.getState();
        final ProfileState profileState = profileViewModel.getState();
        profileState.setUsername(response.getUsername());
        gameEndState.setUsername(response.getUsername());
        discussionPostState.setUsername(response.getUsername());

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
