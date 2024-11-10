package app.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {

    private static final String DB_URL;
    private static final String USER;
    private static final String PASS;

    static {
        try (InputStream input = DatabaseConfig.class.getClassLoader().getResourceAsStream("application.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
            }
            // Load the properties file
            prop.load(input);

            // Get the property values
            DB_URL = prop.getProperty("db.url");
            USER = prop.getProperty("db.username");
            PASS = prop.getProperty("db.password");


        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to load database configuration", ex);
        }
    }


    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Connected to the database!");
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to connect to the database", e);
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

