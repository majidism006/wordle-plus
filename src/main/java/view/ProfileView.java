package view;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import use_case.service.UserService;

public class ProfileView extends JPanel implements PropertyChangeListener {

//    private final UserService userService;
    private final String viewName = "profile";
    private final ProfileViewModel profileViewModel;

    private JDialog profileDialog;
    private JLabel usernameLabel;
    private JLabel winRateLabel;
    private JLabel lossRateLabel;

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

        //Status field
//        JPanel statusPanel = new JPanel(new BorderLayout());
//        JTextField statusField = new JTextField(userService.getStatus(username) != null ? userService.getStatus(username) : "Set your status here!");
//        JButton saveButton = new JButton("Save");
//        saveButton.addActionListener(e -> {
//            ProfileState currentState = profileViewModel.getState();
//            userService.setStatus(currentState.getUsername(), statusField.getText());
//            JOptionPane.showMessageDialog(profileDialog, "Status updated!");
//        });
//
//        statusPanel.add(new JLabel("Status:"), BorderLayout.WEST);
//        statusPanel.add(statusField, BorderLayout.CENTER);
//        statusPanel.add(saveButton, BorderLayout.EAST);

        // Add labels to a panel
        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        infoPanel.add(usernameLabel);
        infoPanel.add(winRateLabel);
        infoPanel.add(lossRateLabel);

        profileDialog.add(infoPanel, BorderLayout.CENTER);
        profileDialog.setLocationRelativeTo(null);
    }

    public void displayProfileDialog() {
        updateProfileData(); // Ensure labels reflect the current state
        profileDialog.setVisible(true);
    }

    private void updateProfileData() {
        ProfileState currentState = profileViewModel.getState();
        usernameLabel.setText("Username: " + currentState.getUsername());
        winRateLabel.setText("Wins: " + currentState.getWin());
        lossRateLabel.setText("Losses: " + currentState.getLoss());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "username" -> usernameLabel.setText("Username: " + evt.getNewValue());
            case "wins" -> winRateLabel.setText("Wins: " + evt.getNewValue());
            case "losses" -> lossRateLabel.setText("Losses: " + evt.getNewValue());
        }
        profileDialog.repaint();
        displayProfileDialog();
    }

    public String getViewName() {
        return this.viewName;
    }
}

////    Component parentComponent
//    public void displayProfileDialog() {
//        final ProfileState currentstate = profileViewModel.getState();
//        String name = currentstate.getUsername();
////        String username = userService.getCurrentUsername();
//        JDialog profileDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Profile", true);
//        profileDialog.setSize(300, 200);
//        profileDialog.setLayout(new BorderLayout());
//
//        // User information
//        usernameLabel = new JLabel("Username: " + name, SwingConstants.CENTER);
//        winRateLabel = new JLabel("Wins: " , SwingConstants.CENTER);
//        lossRateLabel = new JLabel("Losses: ", SwingConstants.CENTER);
//
//        // Status field
////        JPanel statusPanel = new JPanel(new BorderLayout());
////        JTextField statusField = new JTextField(userService.getStatus(username) != null ? userService.getStatus(username) : "Set your status here!");
////        JButton saveButton = new JButton("Save");
////        saveButton.addActionListener(e -> {
////            userService.setStatus(username, statusField.getText());
////            JOptionPane.showMessageDialog(profileDialog, "Status updated!");
////        });
//
////        statusPanel.add(new JLabel("Status:"), BorderLayout.WEST);
////        statusPanel.add(statusField, BorderLayout.CENTER);
////        statusPanel.add(saveButton, BorderLayout.EAST);
//
//        // Add components to the dialog
//        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
//        infoPanel.add(usernameLabel);
//        infoPanel.add(winRateLabel);
//        infoPanel.add(lossRateLabel);
//
//        profileDialog.add(infoPanel, BorderLayout.CENTER);
////        profileDialog.add(statusPanel, BorderLayout.SOUTH);
//
//        profileDialog.setLocationRelativeTo(parentComponent);
//        profileDialog.setVisible(true);
//    }
//
//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//        switch (evt.getPropertyName()) {
//            case "wins" -> winRateLabel.setText("Wins: " + evt.getNewValue());
//            case "losses" -> lossRateLabel.setText("Losses: " + evt.getNewValue());
//        }
//        displayProfileDialog();
//    }
//
//    public String getViewName() {
//        return this.viewName;
//    }
//}
