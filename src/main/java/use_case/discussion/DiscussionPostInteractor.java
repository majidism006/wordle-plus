package use_case.discussion;

import data_access.repository.DiscussionPostRepository;
import entity.DiscussionPost;

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

    /**
     * Switches to Instructions
     */
    @Override
    public void switchToInstructionView() {
        outputBoundary.switchToInstructionView();
    }
}
