package view;


import interface_adapter.history.HistoryController;
import interface_adapter.logout.GameEndController;
import interface_adapter.logout.GameEndState;
import interface_adapter.logout.GameEndViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GameEndView extends JPanel implements PropertyChangeListener {

    private static final String viewName = "game end";
    private final GameEndViewModel gameEndViewModel;
    private GameEndController gameEndController;
    private HistoryController historyController;

    private JLabel titleLabel;
    private JLabel winsLabel;
    private JLabel lossesLabel;
    private JLabel winRateLabel;

    public GameEndView(GameEndViewModel gameEndViewModel) {
        this.gameEndViewModel = gameEndViewModel;
        this.gameEndViewModel.addPropertyChangeListener(this);
        setupComponents();
        setupListeners();
    }

    private void setupComponents() {
        final JPanel stats = new JPanel();
        stats.setLayout(new GridLayout(4, 1));
        titleLabel = new JLabel("", SwingConstants.CENTER);
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

    private void setupListeners() {
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                // Trigger user history retrieval when the view is shown
                final GameEndState currentState = gameEndViewModel.getState();
                historyController.execute(currentState.getUsername());
                titleLabel.setText(currentState.getUsername() + "'s History");
            }
        });
    }


    public String getViewName() {
        return viewName;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "wins" -> winsLabel.setText("Wins: " + evt.getNewValue());
            case "losses" -> lossesLabel.setText("Losses: " + evt.getNewValue());
            case "winRate" -> winRateLabel.setText("Winning Rate: " + String.format("%.2f", evt.getNewValue()) + "%");
        }
    }

    public void setLogoutController(GameEndController gameEndController) {
        this.gameEndController = gameEndController;
    }

    public void setHistoryController(HistoryController historyController) {
        this.historyController = historyController;
    }
}