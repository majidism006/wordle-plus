package app;

import javax.swing.*;

/**
 * Main class for the Wordle program.
 */
public class Main {

    /**
     * Main class for the Wordle program.
     * @param args standard args for psvm.
     */
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addLoginView()
                .addSignupView()
                .addGridView()
                .addGameEndView()
                .addProfileView()
                .addInstructionsView()
                .addDiscussionPostView()
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
