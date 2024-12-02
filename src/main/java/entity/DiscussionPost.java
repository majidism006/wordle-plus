package entity;

import java.sql.Timestamp;

/**
 * Class representing a Discussion Post.
 */
public class DiscussionPost {
    private int id;
    private String userId;
    private String content;
    private Timestamp createdAt;

    /**
     * Gets the ID of the discussion post.
     *
     * @return the ID of the discussion post
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the discussion post.
     *
     * @param id the new ID of the discussion post
     */
    public void setId(int id) {
        this.id = id;
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

    /**
     * Gets the timestamp when the discussion post was created.
     *
     * @return the timestamp when the discussion post was created
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp when the discussion post was created.
     *
     * @param createdAt the new timestamp when the discussion post was created
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
