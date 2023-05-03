package travelagency.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The RestServiceImpl class implements the RestService interface.
 * It retrieves hotel and flight connection data from the database and converts the data into JSON format.
 *
 * @author I551381
 * @version 1.0
 */
public class RestServiceImpl implements RestService {

    /**
     * Retrieves hotel data from the database and returns it as a JSON-formatted string.
     *
     * @param connection The database connection to use for retrieving hotel data.
     * @return A JSON-formatted string containing hotel data.
     */
    public String getHotels(Connection connection) {
        List<Map<String, Object>> hotels = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("""
                SELECT *
                FROM hotel
            """)) {
            ResultSet resultSet = statement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(i);
                    row.put(columnName, columnValue);
                }
                hotels.add(row);
            }
            System.out.println("Hotels sent as JSON");
            return GsonConverter.listToJSON(hotels);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves flight connection data from the database and returns it as a JSON-formatted string.
     *
     * @param connection The database connection to use for retrieving flight connection data.
     * @return A JSON-formatted string containing flight connection data.
     */
    @Override
    public String getFlightConnections(Connection connection) {
        List<Map<String, Object>> flightConnections = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("""
                SELECT *
                FROM flight_connection
            """)) {
            ResultSet resultSet = statement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(i);
                    row.put(columnName, columnValue);
                }
                flightConnections.add(row);
            }
            System.out.println("Flight connections sent as JSON");
            return GsonConverter.listToJSON(flightConnections);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
