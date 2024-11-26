package app;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addLoginView()
                .addSignupView()
                .addGridView()
                .addGameEndView()
                .addWordleInstructionsGUI()
                .addDiscussionPostView()
                .addSignupUseCase()
                .addLoginUseCase()
                .addInstructionsUseCase()
                .addGridUseCase()
                .addGameEndUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
