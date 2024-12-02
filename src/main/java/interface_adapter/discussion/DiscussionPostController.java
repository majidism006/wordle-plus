package interface_adapter.discussion;

import use_case.discussion.DiscussionPostInputBoundary;
import use_case.discussion.DiscussionPostInputData;

/**
 * Controller class for handling discussion post-related actions.
 */
public class DiscussionPostController {
    private final DiscussionPostInputBoundary interactor;

    /**
     * Constructs a new DiscussionPostController with the specified interactor.
     *
     * @param interactor the interactor to handle discussion post use cases
     */
    public DiscussionPostController(DiscussionPostInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Adds a new discussion post.
     *
     * @param userId the ID of the user adding the post
     * @param content the content of the post
     */
    public void addPost(String userId, String content) {
        DiscussionPostInputData inputData = new DiscussionPostInputData(userId, content);
        interactor.addPost(inputData);
    }

    /**
     * Retrieves all discussion posts.
     */
    public void getAllPosts() {
        interactor.getAllPosts();
    }

    /**
     * Fetches a random quote for the specified user.
     *
     * @param userId the ID of the user for whom to fetch a random quote
     */
    public void fetchRandomQuote(String userId) {
        interactor.fetchRandomQuote(userId);
    }

    /**
     * Switches the view to the instruction view.
     */
    public void switchToInstructionView() {
        interactor.switchToInstructionView();
    }
}
