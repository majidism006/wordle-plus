package app;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addLoginView()
                .addGameEndView()
                .addSignupView()
                .addGridView()
                .addWordleInstructionsGUI()
                .addDiscussionPostView()
                .addSignupUseCase()
                .addLoginUseCase()
                .addLogoutUseCase()
                .addInstructionsUseCase()
                .addGridUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
