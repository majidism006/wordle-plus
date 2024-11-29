package entity;

import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import static org.junit.jupiter.api.Assertions.*;

public class DiscussionPostTest {

    @Test
    public void testIdSetterAndGetter() {
        DiscussionPost post = new DiscussionPost();
        post.setId(1);
        assertEquals(1, post.getId(), "ID should be set to 1");
    }

    @Test
    public void testUserIdSetterAndGetter() {
        DiscussionPost post = new DiscussionPost();
        post.setUserId("user123");
        assertEquals("user123", post.getUserId(), "User ID should be set to 'user123'");
    }

    @Test
    public void testContentSetterAndGetter() {
        DiscussionPost post = new DiscussionPost();
        post.setContent("This is a test post.");
        assertEquals("This is a test post.", post.getContent(), "Content should match the provided text");
    }

    @Test
    public void testCreatedAtSetterAndGetter() {
        DiscussionPost post = new DiscussionPost();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        post.setCreatedAt(now);
        assertEquals(now, post.getCreatedAt(), "Timestamp should match the one set");
    }

    @Test
    public void testAllFieldsTogether() {
        DiscussionPost post = new DiscussionPost();

        post.setId(42);
        post.setUserId("testUser");
        post.setContent("Testing all fields.");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        post.setCreatedAt(timestamp);

        assertEquals(42, post.getId(), "ID should be set to 42");
        assertEquals("testUser", post.getUserId(), "User ID should be set to 'testUser'");
        assertEquals("Testing all fields.", post.getContent(), "Content should match the provided text");
        assertEquals(timestamp, post.getCreatedAt(), "Timestamp should match the one set");
    }
}
