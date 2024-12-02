package interface_adapter.discussion;

/**
 * Class representing the state of a discussion post.
 */
public class DiscussionPostState {
    private String userId = "";
    private String content = "";
    private String username = "";

    /**
     * Gets the username associated with the discussion post.
     *
     * @return the username associated with the discussion post
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets the username associated with the discussion post.
     *
     * @param username the new username associated with the discussion post
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the user ID associated with the discussion post.
     *
     * @return the user ID associated with the discussion post
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the user ID associated with the discussion post.
     *
     * @param userId the new user ID associated with the discussion post
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets the content of the discussion post.
     *
     * @return the content of the discussion post
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the discussion post.
     *
     * @param content the new content of the discussion post
     */
    public void setContent(String content) {
        this.content = content;
    }
}
