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
import use_case.instructions.*;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;
import use_case.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class InstructionsInteractorTest {

    public static final String EASY = "easy";
    public static final String MEDIUM = "medium";
    public static final String HARD = "hard";

    UserRepositoryImpl userRepository = new UserRepositoryImpl();
    PasswordHasher passwordHasher = new PasswordHasher();
    UserService userService = new UserService(userRepository, passwordHasher);

    WordRepository wordRepository = new WordRepository();

    private String getRandomDifficulty() {
        Random rand = new Random();
        List<String> list = new ArrayList<>();
        list.add(EASY);
        list.add(MEDIUM);
        list.add(HARD);
        String difficulty = list.get(rand.nextInt(list.size()));
        return difficulty;
    }

    private List<String> getWordList(String difficulty){
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
        return words;
    }

    @Test
    void getRandomWordTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        InstructionsViewModel instructionsViewModel = new InstructionsViewModel();
        GridViewModel gridViewModel = new GridViewModel();
        DiscussionPostViewModel discussionPostViewModel = new DiscussionPostViewModel();
        ProfileViewModel profileViewModel = new ProfileViewModel();
        GridState gridState = new GridState();
        InstructionsOutputBoundary instructionsOutputBoundary = new InstructionsPresenter(viewManagerModel,
                instructionsViewModel, gridViewModel, discussionPostViewModel, profileViewModel);
        InstructionsInputBoundary instructionsInteractor = new InstructionsInteractor(userService,
                instructionsOutputBoundary, wordRepository, gridState);
        String difficulty = getRandomDifficulty();
        String word = instructionsInteractor.getRandomWord(difficulty);
        List<String> words = getWordList(difficulty);
        assert words.contains(word);
    }

    @Test
    void executeTest(){
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        InstructionsViewModel instructionsViewModel = new InstructionsViewModel();
        GridViewModel gridViewModel = new GridViewModel();
        DiscussionPostViewModel discussionPostViewModel = new DiscussionPostViewModel();
        ProfileViewModel profileViewModel = new ProfileViewModel();
        GridState gridState = new GridState();
        InstructionsOutputBoundary instructionsOutputBoundary = new InstructionsPresenter(viewManagerModel,
                instructionsViewModel, gridViewModel, discussionPostViewModel, profileViewModel);
        InstructionsInputBoundary interactor = new InstructionsInteractor(userService,
                instructionsOutputBoundary, wordRepository, gridState);

        String difficulty = getRandomDifficulty();
        InstructionsInputData inputData = new InstructionsInputData(difficulty);
        interactor.execute(inputData);
        List<String> words = getWordList(difficulty);

        assert words.contains(gridState.getTargetWord());
        assertEquals("grid",viewManagerModel.getState());
    }

    @Test
    void switchToGridViewTest(){
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        InstructionsViewModel instructionsViewModel = new InstructionsViewModel();
        GridViewModel gridViewModel = new GridViewModel();
        DiscussionPostViewModel discussionPostViewModel = new DiscussionPostViewModel();
        ProfileViewModel profileViewModel = new ProfileViewModel();
        GridState gridState = new GridState();
        InstructionsOutputBoundary instructionsOutputBoundary = new InstructionsPresenter(viewManagerModel,
                instructionsViewModel, gridViewModel, discussionPostViewModel, profileViewModel);
        InstructionsInputBoundary interactor = new InstructionsInteractor(userService,
                instructionsOutputBoundary, wordRepository, gridState);

        String difficulty = getRandomDifficulty();
        String word = interactor.getRandomWord(difficulty);

        interactor.switchToGridView(word);

        assertEquals("grid",viewManagerModel.getState());
        assertEquals(word,gridState.getTargetWord());
    }

    @Test
    void switchToDiscussionBoardViewTest(){
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        InstructionsViewModel instructionsViewModel = new InstructionsViewModel();
        GridViewModel gridViewModel = new GridViewModel();
        DiscussionPostViewModel discussionPostViewModel = new DiscussionPostViewModel();
        ProfileViewModel profileViewModel = new ProfileViewModel();
        GridState gridState = new GridState();
        InstructionsOutputBoundary instructionsOutputBoundary = new InstructionsPresenter(viewManagerModel,
                instructionsViewModel, gridViewModel, discussionPostViewModel, profileViewModel);
        InstructionsInputBoundary interactor = new InstructionsInteractor(userService,
                instructionsOutputBoundary, wordRepository, gridState);

        interactor.switchToDiscussionBoardView();

        assertEquals("discussion",viewManagerModel.getState());
    }

    @Test
    void switchToProfileViewTest(){
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        InstructionsViewModel instructionsViewModel = new InstructionsViewModel();
        GridViewModel gridViewModel = new GridViewModel();
        DiscussionPostViewModel discussionPostViewModel = new DiscussionPostViewModel();
        ProfileViewModel profileViewModel = new ProfileViewModel();
        GridState gridState = new GridState();
        InstructionsOutputBoundary instructionsOutputBoundary = new InstructionsPresenter(viewManagerModel,
                instructionsViewModel, gridViewModel, discussionPostViewModel, profileViewModel);
        InstructionsInputBoundary interactor = new InstructionsInteractor(userService,
                instructionsOutputBoundary, wordRepository, gridState);

        interactor.switchToProfileView();

        assertEquals("profile",viewManagerModel.getState());
    }

    @Test
    void testSetDifficulty(){
            // Create an instance of InstructionsInputData with initial difficulty
            InstructionsInputData inputData = new InstructionsInputData("Easy");

            // Assert initial difficulty is correctly set
            assertEquals("Easy", inputData.getDifficulty(),
                    "Initial difficulty should be 'Easy'.");

            // Change the difficulty using setDifficulty
            inputData.setDifficulty("Hard");

            // Assert that the difficulty is updated correctly
            assertEquals("Hard", inputData.getDifficulty(),
                    "Difficulty should be updated to 'Hard'.");
        }
    }



