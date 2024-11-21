package app;


import data_access.repository.UserRepositoryImpl;
import interface_adapter.ViewManagerModel;
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
import interface_adapter.reset.ResetController;
import interface_adapter.reset.ResetPresenter;
import interface_adapter.security.PasswordHasher;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.grid.GridViewModel;
import use_case.WordleInstructions.InstructionsInputBoundary;
import use_case.WordleInstructions.InstructionsOutputBoundary;
import use_case.WordleInstructions.InstructionsUseCaseInteractor;
import use_case.grid.GridInputBoundary;
import use_case.grid.GridInteractor;
import use_case.grid.GridOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.reset.ResetInputBoundary;
import use_case.reset.ResetInputData;
import use_case.reset.ResetInteractor;
import use_case.reset.ResetOutBoundary;
import use_case.service.UserService;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.*;

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
    final PasswordHasher passwordHasher = new PasswordHasher();
    final UserService userService = new UserService(userRepository, passwordHasher);

    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private GridViewModel gridViewModel;
    private LogoutViewModel logoutViewModel;
    private LoginView loginView;
    private SignupView signupView;
    private LogoutView logoutView;
    private GridView gridView;
    private InstructionsViewModel instructionsViewModel;
    private WordleInstructionsGUI wordleInstructionsGUI;



    public AppBuilder() {

        cardPanel.setLayout(cardLayout);
        // sets size to be a little bigger than whats the default
        cardPanel.setPreferredSize(new Dimension(600, 500));

    }

    /**
     * Adds the Signup View to the application.
     *
     * @return this builder
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Adds the Login View to the application.
     *
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the instructions page to the application.
     * @return this builder.
     */
    public AppBuilder addWordleInstructionsGUI() {
        instructionsViewModel = new InstructionsViewModel();
        wordleInstructionsGUI = new WordleInstructionsGUI(instructionsViewModel);
        cardPanel.add(wordleInstructionsGUI, WordleInstructionsGUI.getViewName());
        return this;
    }

    public AppBuilder addGridView() {
        gridViewModel = new GridViewModel();
        gridView = new GridView(gridViewModel);
        cardPanel.add(gridView, gridView.getViewName());
        return this;
    }




    public AppBuilder addLogoutView() {
        logoutViewModel = new LogoutViewModel();
        logoutView = new LogoutView(logoutViewModel, userService);
        cardPanel.add(logoutView, logoutView.getViewName());
        return this;
    }

    public AppBuilder addGameEndView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     *
     * @return this builder
     */
    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loginViewModel, signupViewModel, gridViewModel, instructionsViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userService, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userService, signupOutputBoundary);

        final SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    public AppBuilder addInstructionsUseCase() {
        final InstructionsOutputBoundary instructionsOutputBoundary = new InstructionsPresenter(viewManagerModel,
                instructionsViewModel, gridViewModel);
        final InstructionsInputBoundary instructionsInteractor = new InstructionsUseCaseInteractor(
                userService,instructionsOutputBoundary);

        final InstructionsController controller = new InstructionsController(instructionsInteractor);
        wordleInstructionsGUI.setInstructionsController(controller);
        return this;
    }

    /**
     * Adds the Logout Use Case to the application.
     *
     * @return this builder
     */
    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(
                viewManagerModel,
                loginViewModel,
                gridViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(userService, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        logoutView.setLogoutController(logoutController);
        return this;
    }

    public AppBuilder addResetUseCase() {
        final ResetOutBoundary resetOutBoundary = new ResetPresenter(viewManagerModel, gridViewModel);
        final ResetInputBoundary resetInteractor = new ResetInteractor(resetOutBoundary);
        final ResetController resetController = new ResetController(resetInteractor);
        logoutView.setResetController(resetController);
        return this;
    }

    public AppBuilder addGridUseCase() {
        final GridOutputBoundary gridOutputBoundary = new GridPresenter(viewManagerModel, gridViewModel, logoutViewModel);
        final GridInputBoundary gridInteractor = new GridInteractor(gridOutputBoundary);
        final GridController gridController = new GridController(gridInteractor);
        gridView.setGridController(gridController);
        return this;
    }

    /**
     * Allows the title of the Jframe to change depending on what Frame is Visible
     *
     * @return Frame title
     */
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


    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     *
     * @return the application
     */
    public JFrame build() {
        // To ensure resizing of the visible frame
        cardPanel.revalidate();
        cardPanel.repaint();

        final JFrame application = new JFrame("Login");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        // Add a listener to update the frame title
        viewManagerModel.addPropertyChangeListener(evt -> {
            String newState = (String) evt.getNewValue(); // Assume state is the view name
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
