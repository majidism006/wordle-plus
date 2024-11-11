package view;

import javax.swing.*;
import java.awt.*;

public class WordleInstructionsGUI {

    private JFrame frame;

    public WordleInstructionsGUI() {
        // Initialize the frame here
        frame = new JFrame("How to Play");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(new BorderLayout());

        setupComponents();
    }

    private void setupComponents() {
        // Title label
        JLabel titleLabel = new JLabel("How To Play", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Main instructions panel
        JPanel instructionsPanel = new JPanel();
        instructionsPanel.setLayout(new BoxLayout(instructionsPanel, BoxLayout.Y_AXIS));

        // General instructions
        JLabel generalInstructions = new JLabel("<html><center>Guess the Wordle in 6 tries.<br>"
                + "Each guess must be a valid 5-letter word.<br>"
                + "The color of the tiles will change to show<br>"
                + "how close your guess was to the word.</center></html>", SwingConstants.CENTER);
        generalInstructions.setFont(new Font("Serif", Font.PLAIN, 14));
        generalInstructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionsPanel.add(generalInstructions);

        // Examples panel
        JPanel examplesPanel = new JPanel();
        examplesPanel.setLayout(new BoxLayout(examplesPanel, BoxLayout.Y_AXIS));
        examplesPanel.setBorder(BorderFactory.createTitledBorder("Examples"));

        // Example 1: Correct letter and position
        examplesPanel.add(createExample("WORDY", 0));

        // Example 2: Correct letter, wrong position
        examplesPanel.add(createExample("LIGHT", 1));

        // Example 3: Letter not in the word
        examplesPanel.add(createExample("ROGUE", 2));

        instructionsPanel.add(examplesPanel);

        // Add instructions panel to the main frame
        frame.add(instructionsPanel, BorderLayout.CENTER);

        // Placeholder Play button
        JButton playButton = new JButton("Play");
        playButton.setFont(new Font("Serif", Font.BOLD, 16));
        frame.add(playButton, BorderLayout.SOUTH);

        // Here, we might set an action listener to playButton if needed
    }

    public void show() {
        frame.setVisible(true);
    }

    private JPanel createExample(String word, int highlightedIndex) {
        JPanel examplePanel = new JPanel();
        examplePanel.setLayout(new BorderLayout());

        // Add explanatory text above the word tiles
        String explanation = switch (highlightedIndex) {
            case 0 -> "W is in the word and in the correct spot.";
            case 1 -> "I is in the word but in the wrong spot.";
            default -> "U is not in the word in any spot.";
        };
        JLabel explanationLabel = new JLabel(explanation, SwingConstants.CENTER);
        explanationLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0)); // Add space below the text
        examplePanel.add(explanationLabel, BorderLayout.NORTH);

        // Create word tiles
        JPanel wordPanel = new JPanel();
        wordPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5)); // Add horizontal and vertical gaps

        for (int i = 0; i < word.length(); i++) {
            JLabel tileLabel = new JLabel(String.valueOf(word.charAt(i)), SwingConstants.CENTER);
            tileLabel.setPreferredSize(new Dimension(40, 40));
            tileLabel.setOpaque(true);
            tileLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            // Set background color based on the example
            if (i == highlightedIndex) {
                tileLabel.setBackground(highlightedIndex == 0 ? Color.GREEN : highlightedIndex == 1 ? Color.ORANGE : Color.DARK_GRAY);
            } else {
                tileLabel.setBackground(Color.BLACK);
            }
            tileLabel.setForeground(Color.WHITE);
            wordPanel.add(tileLabel);
        }

        examplePanel.add(wordPanel, BorderLayout.CENTER);

        return examplePanel;
    }
}
