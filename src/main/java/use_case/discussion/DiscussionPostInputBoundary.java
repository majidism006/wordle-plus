package use_case.discussion;

/**
 * Interface defining the input boundary for discussion post use cases.
 */
public interface DiscussionPostInputBoundary {

    /**
     * Adds a new discussion post.
     *
     * @param inputData the input data for the discussion post
     */
    void addPost(DiscussionPostInputData inputData);

    /**
     * Retrieves all discussion posts.
     */
    void getAllPosts();

    /**
     * Fetches a random quote for the specified user.
     *
     * @param userId the ID of the user for whom to fetch a random quote
     */
    void fetchRandomQuote(String userId);

    /**
     * Switches the view to the instruction view.
     */
    void switchToInstructionView();
}
