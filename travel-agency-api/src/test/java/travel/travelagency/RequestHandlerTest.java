package travel.travelagency;

import com.sun.net.httpserver.HttpExchange;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.opentest4j.AssertionFailedError;
import spark.Request;
import travelagency.api.Authenticator;
import travelagency.api.RequestHandler;
import travelagency.api.RestService;
import travelagency.api.RestServiceImpl;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import static org.mockito.Mockito.*;

public class RequestHandlerTest {

    @Test
    public void testWithValidCredentials() throws IOException {
        String response = "JSON_Doc";

        final String
                URI_TEXT = "/getBookings?username=demo&password=123",
                USERNAME = "demo",
                PASSWORD = "123";

        OutputStream outputStream = Mockito.mock(OutputStream.class);
        Mockito.doNothing().when(outputStream).write(ArgumentMatchers.eq(response.getBytes()));
        Mockito.doThrow(new RuntimeException("Invalid response due to internal server error")).when(outputStream).
                write("Internal server error".getBytes());
        Mockito.doThrow(new RuntimeException("Invalid response due to invalid credentials provided")).when(outputStream).
                write("Invalid credentials provided".getBytes());
        Mockito.doNothing().when(outputStream).write(response.getBytes());

        URI uri = Mockito.mock(java.net.URI.class);
        Mockito.when(uri.toString()).thenReturn(URI_TEXT);

        HttpExchange httpExchange = Mockito.mock(HttpExchange.class);
        Mockito.when(httpExchange.getRequestURI()).thenReturn(uri);
        Mockito.doNothing().when(httpExchange).sendResponseHeaders(200, response.getBytes().length);
        Mockito.when(httpExchange.getResponseBody()).thenReturn(outputStream);

        Authenticator authenticator = Mockito.mock(Authenticator.class);
        Mockito.when(authenticator.extractUsername(URI_TEXT)).thenReturn(USERNAME);
        Mockito.when(authenticator.extractPassword(URI_TEXT)).thenReturn(PASSWORD);
        Mockito.when(authenticator.checkCredentials(USERNAME, PASSWORD)).thenReturn(true);

        RestService restService = Mockito.mock(RestService.class);
        Mockito.when(restService.getBookings()).thenReturn(response);

        RequestHandler requestHandler = new RequestHandler(authenticator, restService);

        assertDoesNotThrow(() -> {
            try {
                requestHandler.handleBookingsRequest(httpExchange);
            } catch (Exception e) {
                throw new AssertionFailedError("Request was not handled properly due to : " + e.getMessage());
            }
        });
    }

}