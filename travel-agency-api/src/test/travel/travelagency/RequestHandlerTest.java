package travel.travelagency;

import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import travelagency.api.Authenticator;
import travelagency.api.DbService;
import travelagency.api.RequestHandler;
import travelagency.api.RestServiceImpl;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.sql.Connection;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RequestHandlerTest {

    private HttpExchange httpExchange;
    private Authenticator authenticator;
    private RestServiceImpl restServiceImpl;
    private DbService dbService;
    private RequestHandler requestHandler;
    private OutputStream outputStream;

    @BeforeEach
    public void setUp() {
        httpExchange = mock(HttpExchange.class);
        authenticator = mock(Authenticator.class);
        restServiceImpl = mock(RestServiceImpl.class);
        dbService = mock(DbService.class);
        requestHandler = new RequestHandler(authenticator, restServiceImpl, dbService);
        outputStream = mock(OutputStream.class);

        // Add this line to mock the getConnection() method in the DbService class
        when(dbService.getConnection()).thenReturn(mock(Connection.class));
    }


    @Test
    public void testHandleHotelsRequest() throws IOException {
        when(httpExchange.getRequestURI()).thenReturn(URI.create("http://localhost:8000/hotels?username=demo&password=123"));
        when(authenticator.getCredentialsMap()).thenReturn(Map.of("demo", "123"));
        when(restServiceImpl.getHotels(any(Connection.class))).thenReturn("Hotels JSON");
        when(httpExchange.getResponseBody()).thenReturn(outputStream);

        requestHandler.handleHotelsRequest(httpExchange);

        verify(authenticator).getCredentialsMap();
        verify(restServiceImpl).getHotels(any(Connection.class));
        verify(httpExchange).sendResponseHeaders(200, "Hotels JSON".getBytes().length);
        verify(outputStream).write("Hotels JSON".getBytes());
    }

    @Test
    public void testHandleFlightsRequest() throws IOException {
        when(httpExchange.getRequestURI()).thenReturn(URI.create("http://localhost:8000/flights?username=demo&password=123"));
        when(authenticator.getCredentialsMap()).thenReturn(Map.of("demo", "123"));
        when(restServiceImpl.getFlightConnections(any(Connection.class))).thenReturn("Flight connections JSON");
        when(httpExchange.getResponseBody()).thenReturn(outputStream);

        requestHandler.handleFlightsRequest(httpExchange);

        verify(authenticator).getCredentialsMap();
        verify(restServiceImpl).getFlightConnections(any(Connection.class));
        verify(httpExchange).sendResponseHeaders(200, "Flight connections JSON".getBytes().length);
        verify(outputStream).write("Flight connections JSON".getBytes());
    }

    @Test
    public void testHandleRequestInvalidCredentials() throws IOException {
        when(httpExchange.getRequestURI()).thenReturn(URI.create("http://localhost:8000/hotels?username=demo&password=wrong"));
        when(authenticator.getCredentialsMap()).thenReturn(Map.of("demo", "123"));
        when(httpExchange.getResponseBody()).thenReturn(outputStream);

        requestHandler.handleHotelsRequest(httpExchange);

        verify(authenticator).getCredentialsMap();
        verify(restServiceImpl, never()).getHotels(any(Connection.class));
        verify(httpExchange).sendResponseHeaders(401, "Invalid credentials provided".getBytes().length);
        verify(outputStream).write("Invalid credentials provided".getBytes());
    }
}