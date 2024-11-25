package data_access.repository;

import app.config.DatabaseConfig;
import entity.DiscussionPost;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiscussionPostRepository {

    public void addPost(DiscussionPost post) throws SQLException {
        String sql = "INSERT INTO discussion_posts (user_id, content) VALUES (?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, post.getUserId());
            stmt.setString(2, post.getContent());
            stmt.executeUpdate();
        }
    }

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