package use_case.discussion;

import data_access.repository.DiscussionPostRepository;
import entity.DiscussionPost;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

public class DiscussionPostInteractor implements DiscussionPostInputBoundary {
    private final DiscussionPostRepository repository;
    private final DiscussionPostOutputBoundary outputBoundary;

    public DiscussionPostInteractor(DiscussionPostRepository repository, DiscussionPostOutputBoundary outputBoundary) {
        this.repository = repository;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void addPost(DiscussionPostInputData inputData) {
        try {
            DiscussionPost post = new DiscussionPost();
            post.setUserId(inputData.getUserId());
            post.setContent(inputData.getContent());
            repository.addPost(post);
            outputBoundary.presentPostAdded();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle error
        }
    }

    @Override
    public void getAllPosts() {
        try {
            List<DiscussionPost> posts = repository.getAllPosts();
            outputBoundary.presentAllPosts(posts);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle error
        }
    }

    @Override
    public void fetchRandomQuote(String userId) {
        try {
            JSONArray jsonArray = getObjects();
            JSONObject quoteObject = jsonArray.getJSONObject(0);
            String quote = quoteObject.getString("q");
            String author = quoteObject.getString("a");

            // Create post content
            String postContent = quote + " ~ " + author;

            // Add post
            addPost(new DiscussionPostInputData(userId, postContent));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NotNull
    private static JSONArray getObjects() throws URISyntaxException, IOException {
        URI uri = new URI("https://zenquotes.io/api/random/");
        HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        // Close connections
        in.close();
        connection.disconnect();

        // Parse JSON response
        return new JSONArray(content.toString());
    }

    /**
     * Switches to Instructions
     */
    @Override
    public void switchToInstructionView() {
        outputBoundary.switchToInstructionView();
    }
}
