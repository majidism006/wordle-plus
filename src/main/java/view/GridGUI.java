package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GridGUI extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Wordle Grid");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);

        // Main panel with BorderLayout to hold title and grid
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("WORDLE!!!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Grid panel with 6x5 grid layout
        JPanel gridPanel = new JPanel(new GridLayout(6, 5, 10, 10));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Array to keep track of each cell
        JTextField[][] textFields = new JTextField[6][5];

        // Loop will create text fields
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 5; col++) {
                JTextField textField = new JTextField(1);
                textField.setHorizontalAlignment(JTextField.CENTER);
                textField.setFont(new Font("Arial", Font.BOLD, 18));
                textFields[row][col] = textField;

                // KeyListener that ensures one letter per box and auto move forward and back
                int finalRow1 = row;
                int finalCol1 = col;
                textField.addKeyListener(new KeyAdapter() {
                    public void keyTyped(KeyEvent e) {
                        if (textField.getText().length() >= 1) {
                            e.consume();
                        }
                    }

                    public void keyReleased(KeyEvent e) {
                        char keyChar = e.getKeyChar();

                        // Ensure uppercase letters
                        if (Character.isLetter(keyChar)) {
                            textField.setText(String.valueOf(keyChar).toUpperCase());
                        }

                        // Auto move forward
                        if (textField.getText().length() == 1 && keyChar != KeyEvent.VK_BACK_SPACE) {
                            moveToNextField(textFields, finalRow1, finalCol1);
                        }

                        // Auto move back
                        if (keyChar == KeyEvent.VK_BACK_SPACE && textField.getText().isEmpty()) {
                            moveToPreviousField(textFields, finalRow1, finalCol1);
                        }
                    }

                });

                gridPanel.add(textField);
            }
        }

        // Add grid panel to center of main panel
        mainPanel.add(gridPanel, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    // Method to auto move
    private static void moveToNextField(JTextField[][] fields, int row, int col) {
        int nextCol = (col + 1) % 5;
        int nextRow = row + ((col + 1) / 5);
        if (nextRow < fields.length) {
            fields[nextRow][nextCol].requestFocus();
        }
    }

    // Method to auto move back
    private static void moveToPreviousField(JTextField[][] fields, int row, int col) {
        int prevCol = (col - 1 + 6) % 6;
        int prevRow = row - ((col == 0) ? 1 : 0);
        if (prevRow >= 0) {
            fields[prevRow][prevCol].requestFocus();
            fields[prevRow][prevCol].setText("");  // Clear the field when moving back
        }
    }
}
