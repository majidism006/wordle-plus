package use_case;

import data_access.repository.DiscussionPostRepository;
import entity.DiscussionPost;
import interface_adapter.ViewManagerModel;
import interface_adapter.discussion.DiscussionPostPresenter;
import interface_adapter.discussion.DiscussionPostViewModel;
import org.junit.jupiter.api.Test;
import use_case.discussion.DiscussionPostInputData;
import use_case.discussion.DiscussionPostInteractor;
import use_case.discussion.DiscussionPostOutputBoundary;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscussionInteractorTest {

    public static final String USERNAME = "username";
    public static final String TEXT = "OnlyForTesting";

    DiscussionPostRepository repository = new DiscussionPostRepository();
    DiscussionPostViewModel viewModel = new DiscussionPostViewModel();
    ViewManagerModel managerModel = new ViewManagerModel();
    DiscussionPostOutputBoundary OutputBoundary = new DiscussionPostPresenter(viewModel, managerModel);

    @Test
    void addPostTest() throws SQLException {
        class TempInteractor extends DiscussionPostInteractor {

            public TempInteractor(DiscussionPostRepository repository, DiscussionPostOutputBoundary outputBoundary) {
                super(repository, outputBoundary);
            }

            // return post for this test
            public DiscussionPost addPostTemp(DiscussionPostInputData inputData) {
                try {
                    DiscussionPost post = new DiscussionPost();
                    post.setUserId(inputData.getUserId());
                    post.setContent(inputData.getContent());
                    repository.addPost(post);
                    return post;
                    // Don't show test text on discussion board
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Handle error
                }
                return null;
            }
        }

        TempInteractor ADDInteractor = new TempInteractor(repository, OutputBoundary);

        DiscussionPostInputData inputData = new DiscussionPostInputData(USERNAME, TEXT);
        DiscussionPost post = ADDInteractor.addPostTemp(inputData);
        List<DiscussionPost> posts = repository.getAllPosts();
        assertEquals(post.getUserId(), posts.get(posts.size() - 1).getUserId());
        assertEquals(post.getContent(), posts.get(posts.size() - 1).getContent());
    }

    @Test
    void getAllPostsTest() {
        //No need for testing a visual output method
    }

    @Test
    void fetchRandomQuoteTest() {
        //No need for testing
    }

    @Test
    void getObjectTest() {
        //No need for testing
    }

    @Test
    void switchToInstructionViewTest(){
        //No need for testing
    }

}