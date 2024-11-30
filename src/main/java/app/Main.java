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
                .addProfileView()
                .addSignupUseCase()
                .addLoginUseCase()
                .addInstructionsUseCase()
                .addGridUseCase()
                .addGameEndUseCase()
                .addHistoryUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
