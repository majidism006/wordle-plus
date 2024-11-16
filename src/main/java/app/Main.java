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
                .addSignupUseCase()
                .addLoginUseCase()
                .addLogoutUseCase()
                .addGridUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
