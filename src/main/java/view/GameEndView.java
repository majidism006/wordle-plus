package view;


import interface_adapter.logout.GameEndController;
import interface_adapter.logout.GameEndState;
import interface_adapter.logout.GameEndViewModel;
import use_case.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GameEndView extends JPanel implements PropertyChangeListener {

    private static final String viewName = "game end";
    private final GameEndViewModel gameEndViewModel;
    private final UserService userService;
    private GameEndController gameEndController;

    private JLabel winsLabel;
    private JLabel lossesLabel;
    private JLabel winRateLabel;

    public GameEndView(GameEndViewModel gameEndViewModel, UserService userService) {
        this.gameEndViewModel = gameEndViewModel;
        this.userService = userService;
        this.gameEndViewModel.addPropertyChangeListener(this);
        setupComponents();
    }

    private void setupComponents() {
        final JPanel stats = new JPanel();
        stats.setLayout(new GridLayout(4, 1));
        JLabel titleLabel = new JLabel("User's History", SwingConstants.CENTER);
        stats.add(titleLabel);

        winsLabel = new JLabel("Wins: ", SwingConstants.CENTER);
        lossesLabel = new JLabel("Losses: ", SwingConstants.CENTER);
        winRateLabel = new JLabel("Winning Rate: ", SwingConstants.CENTER);

        stats.add(winsLabel);
        stats.add(lossesLabel);
        stats.add(winRateLabel);

        final JPanel buttons = new JPanel();
        JButton playAgain = new JButton("Play Again?");
        buttons.add(playAgain);
        JButton logout = new JButton("Log Out");
        buttons.add(logout);

        playAgain.addActionListener(evt -> gameEndController.switchToInstructionView());

        logout.addActionListener(evt -> {
            final GameEndState currentState = gameEndViewModel.getState();
            gameEndController.execute(currentState.getUsername());
            System.exit(0);
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(stats);
        this.add(buttons);
    }

    private void updateUserStats() {
        String username = userService.getCurrentUsername();
        System.out.println("Fetching stats for user: " + username);

        int wins = userService.getUserWins(username);
        int losses = userService.getUserLosses(username);

        System.out.println("Wins: " + wins);
        System.out.println("Losses: " + losses);

        double winRate = (wins + losses > 0) ? ((double) wins / (wins + losses)) * 100 : 0;

        winsLabel.setText("Wins: " + wins);
        lossesLabel.setText("Losses: " + losses);
        winRateLabel.setText("Winning Rate: " + String.format("%.2f", winRate) + "%");

        System.out.println("Updated labels with stats.");
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("viewDisplayed".equals(evt.getPropertyName())) {
            updateUserStats();
        }
    }

    public void setLogoutController(GameEndController gameEndController) {
        this.gameEndController = gameEndController;
    }
}