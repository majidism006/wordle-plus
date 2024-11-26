package app;


import data_access.repository.DiscussionPostRepository;
import data_access.repository.GameRepositoryImpl;
import data_access.repository.UserRepositoryImpl;
import entity.User;
import interface_adapter.ViewManagerModel;
import entity.DifficultyState;
import interface_adapter.discussion.DiscussionPostController;
import interface_adapter.discussion.DiscussionPostPresenter;
import interface_adapter.discussion.DiscussionPostViewModel;
import interface_adapter.grid.GridController;
import interface_adapter.grid.GridPresenter;
import interface_adapter.instructions.InstructionsController;
import interface_adapter.instructions.InstructionsPresenter;
import interface_adapter.instructions.InstructionsViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.logout.LogoutViewModel;

import interface_adapter.security.PasswordHasher;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.grid.GridViewModel;
import use_case.WordleInstructions.InstructionsInputBoundary;
import use_case.WordleInstructions.InstructionsOutputBoundary;
import use_case.WordleInstructions.InstructionsUseCaseInteractor;
import use_case.discussion.DiscussionPostInputBoundary;
import use_case.discussion.DiscussionPostInteractor;
import use_case.discussion.DiscussionPostOutputBoundary;
import use_case.grid.GridInputBoundary;
import use_case.grid.GridInteractor;
import use_case.grid.GridOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.service.UserService;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.*;
import data_access.repository.WordRepository;
import interface_adapter.grid.GridState;

import javax.swing.*;
import java.awt.*;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);
    private final UserRepositoryImpl userRepository = new UserRepositoryImpl();
    private final PasswordHasher passwordHasher = new PasswordHasher();
    private final UserService userService = new UserService(userRepository, passwordHasher);
    private final WordRepository wordRepository = new WordRepository();
    private final GridState gridState = new GridState();

    // View Models
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private GridViewModel gridViewModel;
    private LogoutViewModel logoutViewModel;
    private DiscussionPostViewModel discussionPostViewModel;
    private InstructionsViewModel instructionsViewModel;

    // Views
    private LoginView loginView;
    private SignupView signupView;
    private GameEndView logoutView;
    private GridView gridView;
    private DiscussionPostView discussionPostView;
    private WordleInstructionsGUI wordleInstructionsGUI;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
        cardPanel.setPreferredSize(new Dimension(600, 500));
    }

    private AppBuilder addView(JPanel view, String viewName) {
        cardPanel.add(view, viewName);
        return this;
    }

    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        return addView(signupView, signupView.getViewName());
    }

    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        return addView(loginView, loginView.getViewName());
    }

    public AppBuilder addWordleInstructionsGUI() {
        instructionsViewModel = new InstructionsViewModel();
        final DifficultyState difficultyState = new DifficultyState();
        // Example values for User, win rate, and loss rate
        User user = new User("player1", "password123"); // Replace with actual User object from your app logic
        double winRate = 75.0;  // Replace with actual win rate
        double lossRate = 25.0; // Replace with actual loss rate

        wordleInstructionsGUI = new WordleInstructionsGUI(instructionsViewModel, difficultyState, gridState, user, winRate, lossRate);
        cardPanel.add(wordleInstructionsGUI, WordleInstructionsGUI.getViewName());
        return this;
    }

    public AppBuilder addGridView() {
        gridViewModel = new GridViewModel();
        gridView = new GridView(gridViewModel);
        return addView(gridView, gridView.getViewName());
    }

    public AppBuilder addGameEndView() {
        logoutViewModel = new LogoutViewModel();
        logoutView = new GameEndView(logoutViewModel, userService);
        return addView(logoutView, logoutView.getViewName());
    }

    public AppBuilder addDiscussionPostView() {
        discussionPostViewModel = new DiscussionPostViewModel();

        DiscussionPostOutputBoundary discussionPostOutputBoundary = new DiscussionPostPresenter(
                discussionPostViewModel, viewManagerModel);
        DiscussionPostRepository discussionPostRepository = new DiscussionPostRepository();
        DiscussionPostInputBoundary discussionPostInputBoundary = new DiscussionPostInteractor(
                discussionPostRepository,
                discussionPostOutputBoundary);
        DiscussionPostController discussionPostController = new DiscussionPostController(discussionPostInputBoundary);

        discussionPostView = new DiscussionPostView(discussionPostViewModel, userService, discussionPostController);
        return addView(discussionPostView, discussionPostView.getViewName());
    }

    public AppBuilder addLoginUseCase() {
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loginViewModel, signupViewModel,
                gridViewModel, instructionsViewModel);
        LoginInputBoundary loginInteractor = new LoginInteractor(userService, loginOutputBoundary);
        LoginController loginController = new LoginController(loginInteractor);

        loginView.setLoginController(loginController);
        return this;
    }

    public AppBuilder addSignupUseCase() {
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel,
                loginViewModel);
        SignupInputBoundary userSignupInteractor = new SignupInteractor(userService, signupOutputBoundary);
        SignupController controller = new SignupController(userSignupInteractor);

        signupView.setSignupController(controller);
        return this;
    }

    public AppBuilder addInstructionsUseCase() {
        InstructionsOutputBoundary instructionsOutputBoundary = new InstructionsPresenter(viewManagerModel,
                instructionsViewModel, gridViewModel, discussionPostViewModel);
        InstructionsInputBoundary instructionsInteractor = new InstructionsUseCaseInteractor(userService,
                instructionsOutputBoundary, wordRepository, gridState);
        InstructionsController controller = new InstructionsController(instructionsInteractor);

        wordleInstructionsGUI.setInstructionsController(controller);
        return this;
    }

    public AppBuilder addLogoutUseCase() {
        LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel, loginViewModel,
                instructionsViewModel, logoutViewModel, gridViewModel);
        LogoutInputBoundary logoutInteractor = new LogoutInteractor(userService, logoutOutputBoundary);
        LogoutController logoutController = new LogoutController(logoutInteractor);

        logoutView.setLogoutController(logoutController);
        return this;
    }

    public AppBuilder addGridUseCase() {
        GridOutputBoundary gridOutputBoundary = new GridPresenter(viewManagerModel, gridViewModel, logoutViewModel);
        GameRepositoryImpl gameRepository = new GameRepositoryImpl();
        GridInputBoundary gridInteractor = new GridInteractor(gridOutputBoundary, gameRepository);
        GridController gridController = new GridController(gridInteractor, gridState);

        gridView.setGridController(gridController);
        return this;
    }

    private String convertStateToTitle(String state) {
        switch (state) {
            case "game end":
                return "Game End";
            case "log in":
                return "Login";
            case "sign up":
                return "Signup";
            case "grid":
                return "Wordle Grid";
            default:
                return "Application";
        }
    }

    public JFrame build() {
        cardPanel.revalidate();
        cardPanel.repaint();

        JFrame application = new JFrame();
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.add(cardPanel);

        // Add a listener to update the frame title
        viewManagerModel.addPropertyChangeListener(evt -> {
            String newState = (String) evt.getNewValue();
            application.setTitle(convertStateToTitle(newState));
        });

        // Set the initial state and fire property changes
        viewManagerModel.setState(loginView.getViewName());
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
        return application;
    }
}