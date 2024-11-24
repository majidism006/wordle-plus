package interface_adapter.difficulty;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DifficultySelector {
    public DifficultySelector() {
        JFrame frame = new JFrame("difficulty");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        String[] difficulties = {"Easy", "Medium", "Hard"};
        JComboBox<String> difficultyBox = new JComboBox<>(difficulties);

        difficultyBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDifficulty = (String) difficultyBox.getSelectedItem();
            }
        });

        JPanel panel = new JPanel();
        panel.add(difficultyBox);
        frame.add(panel);

    }
}

