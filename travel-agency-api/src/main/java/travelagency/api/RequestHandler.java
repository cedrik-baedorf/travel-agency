package travelagency.api;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;

/**
 * This class handles incoming requests for hotels and flights and generates responses accordingly.
 * It checks the requester's credentials and sends responses with hotel and flight connection data.
 *
 * @author I551393
 * @version 1.0
 */
public class RequestHandler {

    private final Authenticator authenticator;
    private final RestService restService;

    /**
     * Initializes the RequestHandler with the given services.
     *
     * @param authenticator     The authenticator service to validate request credentials.
     * @param restService       The REST service for handling request processing.
     */
    public RequestHandler(Authenticator authenticator, RestService restService) {
        this.authenticator = authenticator;
        this.restService = restService;
    }

    /**
     * Handles incoming requests for hotels and flights based on the request type.
     *
     * @param exchange    The HttpExchange object for handling the request.
     * @param requestType The type of request (HOTELS or FLIGHTS).
     * @throws IOException If an I/O error occurs while handling the request.
     */
    void handleRequest(HttpExchange exchange, RequestType requestType) throws IOException {
        String username = authenticator.extractUsername(exchange.getRequestURI().toString());
        String password = authenticator.extractPassword(exchange.getRequestURI().toString());
        if (authenticator.checkCredentials(username, password)) {
            String response = "";
            if(requestType == RequestType.BOOKINGS)
                response = restService.getBookings();

            if (response != null) {
                exchange.sendResponseHeaders(200, response.getBytes().length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            } else {
                exchange.sendResponseHeaders(500, "Internal server error".getBytes().length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write("Internal server error".getBytes());
                }
            }
        } else {
            exchange.sendResponseHeaders(401, "Invalid credentials provided".getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write("Invalid credentials provided".getBytes());
            }
        }
    }

    /**
     * The method is the request handler for the hotel GET request.
     *
     * @param exchange The HttpExchange object for handling the request.
     * @throws IOException If an I/O error occurs while handling the request.
     */
    public void handleBookingsRequest(HttpExchange exchange) throws IOException {
        handleRequest(exchange, RequestType.BOOKINGS);
    }
}
