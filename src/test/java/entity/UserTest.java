package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User userWithId;
    private User userWithoutId;

    @BeforeEach
    public void setUp() {
        userWithId = new User(1, "testuser", "password123");
        userWithoutId = new User("newuser", "newpassword");
    }

    @Test
    public void testConstructorWithId() {
        assertEquals(1, userWithId.getId(), "User ID should be 1");
        assertEquals("testuser", userWithId.getUsername(), "Username should be 'testuser'");
        assertEquals("password123", userWithId.getPassword(), "Password should be 'password123'");
        assertEquals("active", userWithId.getStatus(), "Default status should be 'active'");
    }

    @Test
    public void testConstructorWithoutId() {
        assertEquals(0, userWithoutId.getId(), "User ID should be 0 for new users");
        assertEquals("newuser", userWithoutId.getUsername(), "Username should be 'newuser'");
        assertEquals("newpassword", userWithoutId.getPassword(), "Password should be 'newpassword'");
    }

    @Test
    public void testSetId() {
        userWithoutId.setId(2);
        assertEquals(2, userWithoutId.getId(), "User ID should be set to 2");
    }

    @Test
    public void testSetUsername() {
        userWithId.setUsername("updateduser");
        assertEquals("updateduser", userWithId.getUsername(), "Username should be updated to 'updateduser'");
    }

    @Test
    public void testSetPassword() {
        userWithId.setPassword("newpassword123");
        assertEquals("newpassword123", userWithId.getPassword(), "Password should be updated to 'newpassword123'");
    }

    @Test
    public void testSetStatus() {
        userWithId.setStatus("inactive");
        assertEquals("inactive", userWithId.getStatus(), "Status should be updated to 'inactive'");
    }

    @Test
    public void testToString() {
        String expected = "User{id=1, username='testuser', password='password123'}";
        assertEquals(expected, userWithId.toString(), "toString() output should match");
    }
}
