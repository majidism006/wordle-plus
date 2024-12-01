package view;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.history.HistoryController;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import use_case.service.UserService;

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
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Stack vertically

        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        winRateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        lossRateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statusPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(usernameLabel);
        mainPanel.add(Box.createVerticalStrut(10)); // Add spacing
        mainPanel.add(winRateLabel);
        mainPanel.add(Box.createVerticalStrut(10)); // Add spacing
        mainPanel.add(lossRateLabel);
        mainPanel.add(Box.createVerticalStrut(20)); // Add larger spacing
        mainPanel.add(statusPanel);

        profileDialog.add(mainPanel, BorderLayout.CENTER);
        profileDialog.setLocationRelativeTo(null);
    }

    public void displayProfileDialog() {
        updateProfileData();
        profileDialog.setVisible(true);
    }

    private void updateProfileData() {
        ProfileState currentState = profileViewModel.getState();
        usernameLabel.setText("Username: " + currentState.getUsername());
        winRateLabel.setText("Wins: " + currentState.getWin());
        lossRateLabel.setText("Losses: " + currentState.getLoss());
        statusField.setText(currentState.getStatus() != null ? currentState.getStatus() : "Set your status here!");
    }

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


    public String getViewName() {
        return this.viewName;
    }

    public void setHistoryController(HistoryController historyController) {
        this.historyController = historyController;
    }
}