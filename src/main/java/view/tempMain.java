package view;

import javax.swing.*;

public class tempMain {
    public static void main(String[] args) {
        final tempAppBuilder appBuilder = new tempAppBuilder();
        final JFrame application = appBuilder
                .addLoginView()
                .addLoggedInView()
                .addLoginUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
