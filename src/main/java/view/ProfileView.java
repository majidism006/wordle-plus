package view;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import interface_adapter.history.HistoryController;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;

/**
 * Profile view.
 */
public class ProfileView extends JPanel implements PropertyChangeListener {

    private final String viewName = "profile";
    private final ProfileViewModel profileViewModel;

    private JDialog profileDialog;
    private JLabel usernameLabel;
    private JLabel winRateLabel;
    private JLabel lossRateLabel;
    private JTextField statusField;
    private HistoryController historyController;

    public ProfileView(ProfileViewModel profileViewModel) {

        this.profileViewModel = profileViewModel;
        this.profileViewModel.addPropertyChangeListener(this);

        usernameLabel = new JLabel("", SwingConstants.CENTER);
        winRateLabel = new JLabel("Wins: 0", SwingConstants.CENTER);
        lossRateLabel = new JLabel("Losses: 0", SwingConstants.CENTER);

        // Create dialog
        profileDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Profile", true);
        profileDialog.setSize(300, 200);
        profileDialog.setLayout(new BorderLayout());

        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        statusField = new JTextField(10);
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {

            final ProfileState currentstate = profileViewModel.getState();

            historyController.execute(currentstate.getUsername(), statusField.getText());
            JOptionPane.showMessageDialog(profileDialog, "Status updated!");
        });

        statusPanel.add(new JLabel("Status:"));
        statusPanel.add(statusField);
        statusPanel.add(saveButton);

        JPanel mainPanel = new JPanel();
        // Stack vertically
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        winRateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        lossRateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statusPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(usernameLabel);
        // Add spacing
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(winRateLabel);
        // Add spacing
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(lossRateLabel);
        // Add larger spacing
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(statusPanel);

        profileDialog.add(mainPanel, BorderLayout.CENTER);
        profileDialog.setLocationRelativeTo(null);
    }

    /**
     * Displays the profile dialog.
     */
    public void displayProfileDialog() {
        updateProfileData();
        profileDialog.setVisible(true);
    }

    /**
     * Updates the profile data.
     */
    private void updateProfileData() {
        ProfileState currentState = profileViewModel.getState();
        usernameLabel.setText("Username: " + currentState.getUsername());
        winRateLabel.setText("Wins: " + currentState.getWin());
        lossRateLabel.setText("Losses: " + currentState.getLoss());
        statusField.setText(currentState.getStatus() != null ? currentState.getStatus() : "Set your status here!");
    }

    /**
     * Handles property change events.
     *
     * @param evt the property change event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "username" -> usernameLabel.setText("Username: " + evt.getNewValue());
            case "wins" -> winRateLabel.setText("Wins: " + evt.getNewValue());
            case "losses" -> lossRateLabel.setText("Losses: " + evt.getNewValue());
            case "status" -> statusField.setText((String) evt.getNewValue());
        }
        displayProfileDialog();
    }

    /**
     * Returns the name of the view.
     *
     * @return the view name
     */
    public String getViewName() {
        return this.viewName;
    }

    /**
     * Sets the history controller.
     *
     * @param historyController the history controller to set
     */
    public void setHistoryController(HistoryController historyController) {
        this.historyController = historyController;
    }
}