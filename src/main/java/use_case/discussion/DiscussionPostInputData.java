package use_case.discussion;

/**
 * Class representing the input data for a discussion post.
 */
public class DiscussionPostInputData {
    private final String userId;
    private final String content;

    /**
     * Constructs a new DiscussionPostInputData with the specified user ID and content.
     *
     * @param userId the ID of the user creating the post
     * @param content the content of the discussion post
     */
    public DiscussionPostInputData(String userId, String content) {
        this.userId = userId;
        this.content = content;
    }

    /**
     * Gets the user ID associated with the discussion post.
     *
     * @return the user ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Gets the content of the discussion post.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }
}