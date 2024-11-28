package view;

import javax.swing.*;
import java.awt.*;
import use_case.service.UserService;

public class ProfileView {

    private final UserService userService;

    public ProfileView(UserService userService) {
        this.userService = userService;
    }

    public void displayProfileDialog(Component parentComponent) {
        String username = userService.getCurrentUsername();
        JDialog profileDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(parentComponent), "Profile", true);
        profileDialog.setSize(300, 200);
        profileDialog.setLayout(new BorderLayout());

        // User information
        JLabel usernameLabel = new JLabel("Username: " + username, SwingConstants.CENTER);
        JLabel winRateLabel = new JLabel("Wins: " + userService.getUserWins(username), SwingConstants.CENTER);
        JLabel lossRateLabel = new JLabel("Losses: " + userService.getUserLosses(username), SwingConstants.CENTER);

        // Status field
        JPanel statusPanel = new JPanel(new BorderLayout());
        JTextField statusField = new JTextField(userService.getStatus(username) != null ? userService.getStatus(username) : "Set your status here!");
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            userService.setStatus(username, statusField.getText());
            JOptionPane.showMessageDialog(profileDialog, "Status updated!");
        });

        statusPanel.add(new JLabel("Status:"), BorderLayout.WEST);
        statusPanel.add(statusField, BorderLayout.CENTER);
        statusPanel.add(saveButton, BorderLayout.EAST);

        // Add components to the dialog
        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        infoPanel.add(usernameLabel);
        infoPanel.add(winRateLabel);
        infoPanel.add(lossRateLabel);

        profileDialog.add(infoPanel, BorderLayout.CENTER);
        profileDialog.add(statusPanel, BorderLayout.SOUTH);

        profileDialog.setLocationRelativeTo(parentComponent);
        profileDialog.setVisible(true);
    }
}
