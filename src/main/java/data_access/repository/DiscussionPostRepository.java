
package data_access.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import app.config.DatabaseConfig;
import entity.DiscussionPost;

/**
 * Defines CRUD operations for the Discussion Post use case.
 */
public class DiscussionPostRepository {

    /**
     * Adds new post to database.
     * @param post contains post text and username.
     * @throws SQLException if error fetching table.
     */
    public void addPost(DiscussionPost post) throws SQLException {
        String sql = "INSERT INTO discussion_posts (user_id, content) VALUES (?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, post.getUserId());
            stmt.setString(2, post.getContent());
            stmt.executeUpdate();
        }
    }

    /**
     * Returns all posts to render Discussion Board.
     * @return List of DiscussionPost objects.
     * @throws SQLException if unable to get table.
     */
    public List<DiscussionPost> getAllPosts() throws SQLException {
        List<DiscussionPost> posts = new ArrayList<>();
        String sql = "SELECT * FROM discussion_posts ORDER BY created_at ASC";
        try (Connection connection = DatabaseConfig.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                DiscussionPost post = new DiscussionPost();
                post.setId(rs.getInt("id"));
                post.setUserId(rs.getString("user_id"));
                post.setContent(rs.getString("content"));
                post.setCreatedAt(rs.getTimestamp("created_at"));
                posts.add(post);
            }
        }
        return posts;
    }
}