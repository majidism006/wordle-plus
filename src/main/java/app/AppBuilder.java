package app;


import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import data_access.repository.DiscussionPostRepository;
import data_access.repository.GameRepositoryImpl;
import data_access.repository.UserRepositoryImpl;
import data_access.repository.WordRepository;
import entity.DifficultyState;
import entity.GameState;
import interface_adapter.ViewManagerModel;
import interface_adapter.discussion.DiscussionPostController;
import interface_adapter.discussion.DiscussionPostPresenter;
import interface_adapter.discussion.DiscussionPostViewModel;
import interface_adapter.gameend.GameEndViewModel;
import interface_adapter.grid.GridController;
import interface_adapter.grid.GridPresenter;
import interface_adapter.grid.GridState;
import interface_adapter.grid.GridViewModel;
import interface_adapter.history.HistoryController;
import interface_adapter.history.HistoryPresenter;
import interface_adapter.instructions.InstructionsController;
import interface_adapter.instructions.InstructionsPresenter;
import interface_adapter.instructions.InstructionsViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.security.PasswordHasher;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.discussion.DiscussionPostInputBoundary;
import use_case.discussion.DiscussionPostInteractor;
import use_case.discussion.DiscussionPostOutputBoundary;
import use_case.grid.GridInputBoundary;
import use_case.grid.GridInteractor;
import use_case.grid.GridOutputBoundary;
import use_case.history.*;
import use_case.instructions.InstructionsInputBoundary;
import use_case.instructions.InstructionsInteractor;
import use_case.instructions.InstructionsOutputBoundary;
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
    private UserService userService = new UserService(userRepository, passwordHasher);
    private final WordRepository wordRepository = new WordRepository();
    private final GridState gridState = new GridState();
    private GameState gameState = new GameState(gridState);

    // View Models
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private GridViewModel gridViewModel;
    private GameEndViewModel gameEndViewModel;
    private DiscussionPostViewModel discussionPostViewModel;
    private InstructionsViewModel instructionsViewModel;
    private ProfileViewModel profileViewModel;

    // Views
    private LoginView loginView;
    private SignupView signupView;
    private GameEndView gameEndView;
    private GridView gridView;
    private DiscussionPostView discussionPostView;
    private InstructionsView instructionsView;
    private ProfileView profileView;

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

    public AppBuilder addProfileView() {
        profileViewModel = new ProfileViewModel();
        profileView = new ProfileView(profileViewModel);
        return addView(profileView, profileView.getViewName());
    }

    public AppBuilder addInstructionsView() {
        instructionsViewModel = new InstructionsViewModel();
        final DifficultyState difficultyState = new DifficultyState();
        instructionsView = new InstructionsView(instructionsViewModel, profileViewModel, difficultyState, gridState,
                userService);
        cardPanel.add(instructionsView, InstructionsView.getViewName());
        return this;
    }

    public AppBuilder addGridView() {
        gridViewModel = new GridViewModel();
        gridView = new GridView(gridViewModel);
        return addView(gridView, gridView.getViewName());
    }

    public AppBuilder addGameEndView() {
        gameEndViewModel = new GameEndViewModel();
        gameEndView = new GameEndView(gameEndViewModel);
        return addView(gameEndView, gameEndView.getViewName());
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

        discussionPostView = new DiscussionPostView(discussionPostViewModel, discussionPostController);
        return addView(discussionPostView, discussionPostView.getViewName());
    }

    public AppBuilder addLoginUseCase() {
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loginViewModel, signupViewModel,
                instructionsViewModel, gameEndViewModel, discussionPostViewModel,profileViewModel);
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
                instructionsViewModel, gridViewModel, discussionPostViewModel, profileViewModel);
        InstructionsInputBoundary instructionsInteractor = new InstructionsInteractor(userService,
                instructionsOutputBoundary, wordRepository, gridState);
        InstructionsController controller = new InstructionsController(instructionsInteractor);

        instructionsView.setInstructionsController(controller);
        return this;
    }

    public AppBuilder addGameEndUseCase() {
        LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel, loginViewModel,
                instructionsViewModel, gameEndViewModel, gridViewModel);
        LogoutInputBoundary logoutInteractor = new LogoutInteractor(userService, logoutOutputBoundary);
        LogoutController logoutController = new LogoutController(logoutInteractor);

        gameEndView.setLogoutController(logoutController);
        return this;
    }

    public AppBuilder addHistoryUseCase() {
        HistoryOutputBoundary historyOutputBoundary = new HistoryPresenter(gameEndViewModel, profileViewModel);
        HistoryInputBoundary historyInteractor = new HistoryInteractor(userService, historyOutputBoundary);
        HistoryController historyController = new HistoryController(historyInteractor);

        gameEndView.setHistoryController(historyController);
        instructionsView.setHistoryController(historyController);
        profileView.setHistoryController(historyController);
        return this;
    }

    public AppBuilder addGridUseCase() {
        GridOutputBoundary gridOutputBoundary = new GridPresenter(viewManagerModel, gridViewModel, gameEndViewModel);
        GameRepositoryImpl gameRepository = new GameRepositoryImpl();
        gameRepository.saveGameState(gameState);
        GridInputBoundary gridInteractor = new GridInteractor(gridOutputBoundary, gameRepository, userService);
        GridController gridController = new GridController(gridInteractor, gridState, gridViewModel);

        gridView.setGridController(gridController);
        return this;
    }

    private String convertStateToTitle(String state) {
        Map<String, String> stateTitleMap = new HashMap<>();
        stateTitleMap.put("game end", "Game End");
        stateTitleMap.put("log in", "Login");
        stateTitleMap.put("sign up", "Signup");
        stateTitleMap.put("grid", "Wordle Grid");

        return stateTitleMap.getOrDefault(state, "Application");
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