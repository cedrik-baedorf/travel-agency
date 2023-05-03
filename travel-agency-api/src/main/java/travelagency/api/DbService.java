package travelagency.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class manages the database connection for the application.
 * It establishes and maintains the connection to the database.
 *
 * @author I551381
 * @version 1.0
 */
public class DbService {

    private Connection connection;

    /**
     * Establishes a connection to the database using the provided credentials.
     *
     * @throws SQLException If a database access error occurs or the URL is null.
     */
    public void connectToDB() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/travel-agency-service_db",
                    "root", "password"
            );
        }
    }

    /**
     * Returns the current database connection.
     *
     * @return The database connection object.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Closes the current database connection if it exists and is open.
     *
     * @throws SQLException If a database access error occurs.
     */
    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
