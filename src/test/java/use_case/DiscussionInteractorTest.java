package use_case;

import data_access.repository.DiscussionPostRepository;
import data_access.repository.UserRepositoryImpl;
import entity.DiscussionPost;
import interface_adapter.ViewManagerModel;
import interface_adapter.discussion.DiscussionPostPresenter;
import interface_adapter.discussion.DiscussionPostViewModel;

import interface_adapter.security.PasswordHasher;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import use_case.discussion.DiscussionPostInputData;
import use_case.discussion.DiscussionPostInteractor;
import use_case.discussion.DiscussionPostOutputBoundary;
import use_case.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class DiscussionInteractorTest {

    public static final String USERNAME = "username";
    public static final String TEXT = "OnlyForTesting";

    DiscussionPostRepository repository = new DiscussionPostRepository();

    UserRepositoryImpl userRepository = new UserRepositoryImpl();
    PasswordHasher passwordHasher = new PasswordHasher();
    UserService userService = new UserService(userRepository, passwordHasher);

    @Test
    void addPostTest() throws SQLException {
        DiscussionPostViewModel viewModel = new DiscussionPostViewModel();
        ViewManagerModel managerModel = new ViewManagerModel();
        DiscussionPostOutputBoundary OutputBoundary = new DiscussionPostPresenter(viewModel, managerModel);

        DiscussionPostInteractor ADDInteractor = new DiscussionPostInteractor(repository, OutputBoundary);
        DiscussionPostInputData inputData = new DiscussionPostInputData(USERNAME, TEXT);

        List<DiscussionPost> previousPosts = repository.getAllPosts();

        ADDInteractor.addPost(inputData);

        List<DiscussionPost> posts = repository.getAllPosts();

        assertNotEquals(previousPosts.size(), posts.size());
        assertEquals(USERNAME, String.valueOf(posts.get(posts.size() - 1).getUserId()));
        assertEquals(TEXT, posts.get(posts.size() - 1).getContent());
    }


    @Test
    void fetchRandomQuoteTest() throws SQLException {
        DiscussionPostViewModel viewModel = new DiscussionPostViewModel();
        ViewManagerModel managerModel = new ViewManagerModel();
        DiscussionPostOutputBoundary OutputBoundary = new DiscussionPostPresenter(viewModel, managerModel);

        DiscussionPostInteractor FETCHInteractor = new DiscussionPostInteractor(repository, OutputBoundary);

        List<DiscussionPost> previousPosts = repository.getAllPosts();

        FETCHInteractor.fetchRandomQuote(USERNAME);

        List<DiscussionPost> posts = repository.getAllPosts();

        assertNotEquals(previousPosts.size(), posts.size());

    }

    @Test
    void getAllPostsTest() throws SQLException {
        DiscussionPostViewModel viewModel = new DiscussionPostViewModel();
        ViewManagerModel managerModel = new ViewManagerModel();
        DiscussionPostOutputBoundary OutputBoundary = new DiscussionPostPresenter(viewModel, managerModel);

        DiscussionPostInteractor interactor = new DiscussionPostInteractor(repository, OutputBoundary);
        interactor.getAllPosts();
        List<DiscussionPost> posts1 = repository.getAllPosts();
        List<DiscussionPost> posts2 = viewModel.getPosts();

        assertEquals(repository.getAllPosts().size(), viewModel.getPosts().size());
    }

    @Test
    void switchToInstuctionViewTest() {
        DiscussionPostViewModel viewModel = new DiscussionPostViewModel();
        ViewManagerModel managerModel = new ViewManagerModel();
        DiscussionPostOutputBoundary OutputBoundary = new DiscussionPostPresenter(viewModel, managerModel);

        DiscussionPostInteractor interactor = new DiscussionPostInteractor(repository, OutputBoundary);
        interactor.switchToInstructionView();

        assertEquals("instructions", managerModel.getState());
    }

    @Test
    void getObjectTest() throws IOException, URISyntaxException {

    }

}