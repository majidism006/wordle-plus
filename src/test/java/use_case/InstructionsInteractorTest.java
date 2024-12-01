package use_case;

import data_access.repository.UserRepositoryImpl;
import data_access.repository.WordRepository;
import interface_adapter.ViewManagerModel;
import interface_adapter.discussion.DiscussionPostViewModel;
import interface_adapter.grid.GridState;
import interface_adapter.grid.GridViewModel;
import interface_adapter.instructions.InstructionsPresenter;
import interface_adapter.instructions.InstructionsViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.security.PasswordHasher;
import org.junit.jupiter.api.Test;
import use_case.instructions.InstructionsInputBoundary;
import use_case.instructions.InstructionsOutputBoundary;
import use_case.instructions.InstructionsInteractor;
import use_case.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class InstructionsInteractorTest {

    public static final String EASY = "easy";
    public static final String MEDIUM = "medium";
    public static final String HARD = "hard";

    UserRepositoryImpl userRepository = new UserRepositoryImpl();
    PasswordHasher passwordHasher = new PasswordHasher();
    UserService userService = new UserService(userRepository, passwordHasher);

    ViewManagerModel viewManagerModel = new ViewManagerModel();
    InstructionsViewModel instructionsViewModel = new InstructionsViewModel();
    GridViewModel gridViewModel = new GridViewModel();
    DiscussionPostViewModel discussionPostViewModel = new DiscussionPostViewModel();
    ProfileViewModel profileViewModel = new ProfileViewModel();

    WordRepository wordRepository = new WordRepository();
    GridState gridState = new GridState();

    @Test
    void getRandomWordTest() {
        InstructionsOutputBoundary instructionsOutputBoundary = new InstructionsPresenter(viewManagerModel,
                instructionsViewModel, gridViewModel, discussionPostViewModel, profileViewModel);
        InstructionsInputBoundary instructionsInteractor = new InstructionsInteractor(userService,
                instructionsOutputBoundary, wordRepository, gridState);
        Random rand = new Random();
        List<String> list = new ArrayList<>();
        list.add(EASY);
        list.add(MEDIUM);
        list.add(HARD);
        String difficulty = list.get(rand.nextInt(list.size()));
        List<String> words = new ArrayList<>();
        switch (difficulty){
            case EASY:
                try (BufferedReader reader = Files.newBufferedReader(Paths.get("src/main/resources/easy_words"))) {
                    words = reader.lines().collect(Collectors.toList());
                    break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            case MEDIUM:
                try (BufferedReader reader = Files.newBufferedReader(
                        Paths.get("src/main/resources/medium_words"))) {
                    words = reader.lines().collect(Collectors.toList());
                    break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            case HARD:
                try (BufferedReader reader = Files.newBufferedReader(Paths.get("src/main/resources/hard_words"))) {
                    words = reader.lines().collect(Collectors.toList());
                    break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            default:
                break;
        }

        String word = instructionsInteractor.getRandomWord(difficulty);
        assert words.contains(word);
    }
}
