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

    // This test require some access provided by API. It doesn't really matter considering we can test
    // it directly by using application
//    @Test
//    void fetchRandomQuoteTest() throws SQLException {
//        class TempInteractor extends DiscussionPostInteractor {
//
//            public TempInteractor(DiscussionPostRepository repository, DiscussionPostOutputBoundary outputBoundary) {
//                super(repository, outputBoundary);
//            }
//
//            private static JSONArray getObjectsTemp() throws URISyntaxException, IOException {
//                URI uri = new URI("https://zenquotes.io/api/random/");
//                HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
//                connection.setRequestMethod("GET");
//
//                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                String inputLine;
//                StringBuilder content = new StringBuilder();
//                while ((inputLine = in.readLine()) != null) {
//                    content.append(inputLine);
//                }
//
//                // Close connections
//                in.close();
//                connection.disconnect();
//
//                // Parse JSON response
//                return new JSONArray(content.toString());
//            }
//
//            // return post for this test
//            public DiscussionPost addPostTemp(DiscussionPostInputData inputData) {
//                try {
//                    DiscussionPost post = new DiscussionPost();
//                    post.setUserId(inputData.getUserId());
//                    post.setContent(inputData.getContent());
//                    repository.addPost(post);
//                    return post;
//                    // Don't show test text on discussion board
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                    // Handle error
//                }
//                return null;
//            }
//
//            // return post for this test
//            public DiscussionPost fetchRandomQuoteTemp(String userId) {
//                try {
//                    JSONArray jsonArray = getObjectsTemp();
//                    JSONObject quoteObject = jsonArray.getJSONObject(0);
//                    String quote = quoteObject.getString("q");
//                    String author = quoteObject.getString("a");
//
//                    // Create post content
//                    String postContent = quote + " ~ " + author;
//
//                    // Add post
//                    DiscussionPost post = addPostTemp(new DiscussionPostInputData(userId, postContent));
//
//                    return post;
//                }
//                catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//        }
//
//        TempInteractor FETCHInteractor = new TempInteractor(repository, OutputBoundary);
//
//        UserRepositoryImpl userRepository = new UserRepositoryImpl();
//
//        DiscussionPost post = FETCHInteractor.fetchRandomQuoteTemp(String.valueOf(
//                userRepository.findUserByUsername(USERNAME).getId()));
//        List<DiscussionPost> posts = repository.getAllPosts();
//        assertEquals(post.getUserId(), posts.get(posts.size() - 1).getUserId());
//        assertEquals(post.getContent(), posts.get(posts.size() - 1).getContent());
//    }

}