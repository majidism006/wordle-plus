package data_access.repository;

import entity.DiscussionPost;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiscussionPostRepository {
    private final Connection connection;

    public DiscussionPostRepository(Connection connection) {
        this.connection = connection;
    }

    public void addPost(DiscussionPost post) throws SQLException {
        String sql = "INSERT INTO discussion_posts (user_id, content) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, post.getUserId());
            stmt.setString(2, post.getContent());
            stmt.executeUpdate();
        }
    }

    public List<DiscussionPost> getAllPosts() throws SQLException {
        List<DiscussionPost> posts = new ArrayList<>();
        String sql = "SELECT * FROM discussion_posts ORDER BY created_at DESC";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                DiscussionPost post = new DiscussionPost();
                post.setId(rs.getInt("id"));
                post.setUserId(rs.getInt("user_id"));
                post.setContent(rs.getString("content"));
                post.setCreatedAt(rs.getTimestamp("created_at"));
                posts.add(post);
            }
        }
        return posts;
    }
}
