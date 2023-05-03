package travelagency.api;

import java.sql.Connection;

/**
 * This interface fetches the requested data from the database via prepared statements.
 * @author I551381
 * @version 1.0
 */
public interface RestService {

    /**
     * Gets the whole table of the hotels from the database using prepared statements.
     * @param connection
     * @return Returns all the hotel data from the database as a String in JSON format.
     */
    String getHotels(Connection connection);

    /**
     * Gets the whole table of the flight connections from the database using prepared statements.
     * @param connection
     * @return Returns all the flight connection data as a String in JSON format.
     */
    String getFlightConnections(Connection connection);
}
