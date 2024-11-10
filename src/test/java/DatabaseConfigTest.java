import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import app.config.DatabaseConfig;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DatabaseConfigTest {

    private static Connection connection;

    @BeforeAll
    public static void setUp() {
        connection = DatabaseConfig.getConnection();
    }

    @Test
    public void testConnectionNotNull() {
        assertNotNull(connection, "Connection should not be null");
    }

    @Test
    public void testConnectionIsValid() throws SQLException {
        assertTrue(connection.isValid(2), "Connection should be valid");
    }

    @AfterAll
    public static void tearDown() {
        DatabaseConfig.closeConnection();
    }
}
