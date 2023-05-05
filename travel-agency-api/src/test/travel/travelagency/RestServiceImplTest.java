package travel.travelagency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import travelagency.api.RestServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class RestServiceImplTest {

    private RestServiceImpl restService;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @Mock
    private ResultSetMetaData resultSetMetaData;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        restService = new RestServiceImpl();
    }

    @Test
    public void testGetHotels() throws Exception {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.getMetaData()).thenReturn(resultSetMetaData);
        when(resultSetMetaData.getColumnCount()).thenReturn(3);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSetMetaData.getColumnName(anyInt())).thenReturn("id").thenReturn("name").thenReturn("city");
        when(resultSet.getObject(anyInt())).thenReturn(1).thenReturn("Test Hotel").thenReturn("Test City");

        String hotelsJson = restService.getHotels(connection);

        assertNotNull(hotelsJson);
        verify(connection).prepareStatement(anyString());
        verify(preparedStatement).executeQuery();
        verify(resultSet).getMetaData();
    }

    @Test
    public void testGetFlightConnections() throws Exception {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.getMetaData()).thenReturn(resultSetMetaData);
        when(resultSetMetaData.getColumnCount()).thenReturn(3);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSetMetaData.getColumnName(anyInt())).thenReturn("id").thenReturn("origin").thenReturn("destination");
        when(resultSet.getObject(anyInt())).thenReturn(1).thenReturn("Test Origin").thenReturn("Test Destination");

        String flightConnectionsJson = restService.getFlightConnections(connection);

        assertNotNull(flightConnectionsJson);
        verify(connection).prepareStatement(anyString());
        verify(preparedStatement).executeQuery();
        verify(resultSet).getMetaData();
    }
}
