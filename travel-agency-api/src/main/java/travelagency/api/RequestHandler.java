package travelagency.api;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import travelagency.service.database.TravelAgencyDatabaseAuthenticator;
import travelagency.service.database.TravelAgencyServiceFactory;
import travelagency.service.service.data.TravelAgencyViewDataService;

import static travelagency.api.Authenticator.*;

/**
 * This class handles incoming requests for hotels and flights and generates responses accordingly.
 * It checks the requester's credentials and sends responses with hotel and flight connection data.
 *
 * @author I551393
 * @version 1.0
 */
public class RequestHandler {

    private final Authenticator authenticator;
    private final RestServiceImpl restServiceImpl;

    /**
     * Initializes the RequestHandler with the given services.
     *
     * @param authenticator   The authenticator service to validate request credentials.
     * @param restServiceImpl The REST service implementation for handling request processing.
     */
    public RequestHandler(Authenticator authenticator, RestServiceImpl restServiceImpl) {
        this.authenticator = authenticator;
        this.restServiceImpl = restServiceImpl;
    }

    /**
     * Handles incoming requests for hotels and flights based on the request type.
     *
     * @param exchange    The HttpExchange object for handling the request.
     * @param requestType The type of request (HOTELS or FLIGHTS).
     * @throws IOException If an I/O error occurs while handling the request.
     */
    void handleRequest(HttpExchange exchange, RequestType requestType) throws IOException {
        String username = extractUsername(exchange.getRequestURI().toString());
        String password = extractPassword(exchange.getRequestURI().toString());
        if (checkCredentials(authenticator.getCredentialsMap(), username, password)) {
            String response = "";
            response = restServiceImpl.getBookings();
            /*if (requestType == RequestType.HOTELS) {
                response = restServiceImpl.getHotels(dbService.getConnection());
            } else if (requestType == RequestType.FLIGHTS) {
                response = restServiceImpl.getFlightConnections(dbService.getConnection());
            }*/

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


    /**
     * The method is the request handler for the hotel GET request.
     *
     * @param exchange The HttpExchange object for handling the request.
     * @throws IOException If an I/O error occurs while handling the request.
     */
    public void handleHotelsRequest(HttpExchange exchange) throws IOException {
        handleRequest(exchange, RequestType.HOTELS);
    }

    /**
     * The method is the request handler for the flight connection GET request.
     *
     * @param exchange The HttpExchange object for handling the request.
     * @throws IOException If an I/O error occurs while handling the request.
     */
    public void handleFlightsRequest(HttpExchange exchange) throws IOException {
        handleRequest(exchange, RequestType.FLIGHTS);
    }
}
