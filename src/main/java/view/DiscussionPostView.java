package view;

import interface_adapter.discussion.DiscussionPostController;
import interface_adapter.discussion.DiscussionPostState;
import interface_adapter.discussion.DiscussionPostViewModel;
import entity.DiscussionPost;
import use_case.service.UserService;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class DiscussionPostView extends JPanel implements ActionListener, PropertyChangeListener {

    private final JTextArea discussionArea;
    private final JTextField textEntryField;
    private final JButton postButton;
    private final JButton exitButton;
    private final DiscussionPostViewModel viewModel;
    private final UserService userService;
    private final DiscussionPostController controller;
    private final Timer timer;

    public DiscussionPostView(DiscussionPostViewModel viewModel, UserService userService,
                              DiscussionPostController controller) {
        this.viewModel = viewModel;
        this.userService = userService;
        this.controller = controller;
        this.viewModel.addPropertyChangeListener(this);

        // Set layout for the main panel
        this.setLayout(new BorderLayout());

        // Create the discussion area (scrollable)
        discussionArea = new JTextArea();
        discussionArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(discussionArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Create the text entry field and post button
        textEntryField = new JTextField(30);
        postButton = new JButton("Post");
        exitButton = new JButton("Exit");

        // Panel for text entry and post button
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(textEntryField, BorderLayout.CENTER);
        inputPanel.add(postButton, BorderLayout.EAST);

        // Panel for exit button
        JPanel exitPanel = new JPanel();
        exitPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        exitPanel.add(exitButton);

        // Add components to the main panel
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(inputPanel, BorderLayout.SOUTH);
        this.add(exitPanel, BorderLayout.NORTH);

        // Add action listeners
        postButton.addActionListener(this);
        exitButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(exitButton)) {
                        controller.switchToInstructionView();
                    }
                }
        );

        // Add document listener to text entry field
        textEntryField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updatePostButtonState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updatePostButtonState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updatePostButtonState();
            }
        });

        // Initial state of the post button
        updatePostButtonState();

        // Load existing posts
        controller.getAllPosts();

        // Set up a timer to fetch new posts every 5 seconds
        timer = new Timer(5000, e -> controller.getAllPosts());
        timer.start();
    }

    private void updatePostButtonState() {
        postButton.setEnabled(true); // Always enable the post button
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == postButton) {
            String text = textEntryField.getText().trim();
            String currentUserId = userService.getCurrentUsername();
            if (text.isEmpty()) {
                controller.fetchRandomQuote(currentUserId);
            } else {
                controller.addPost(currentUserId, text);
            }
            textEntryField.setText("");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("posts".equals(evt.getPropertyName())) {
            List<DiscussionPost> posts = (List<DiscussionPost>) evt.getNewValue();
            displayPosts(posts);
        }
    }

    private void displayPosts(List<DiscussionPost> posts) {
        discussionArea.setText("");
        for (DiscussionPost post : posts) {
            discussionArea.append(post.getUserId() + ": " + post.getContent() + "\n");
        }
    }

    public String getViewName() {
        return "discussion";
    }
}