package app;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addLoginView()
                .addLogoutView()
                .addSignupView()
                .addGridView()
                .addWordleInstructionsGUI()
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
