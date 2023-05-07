package travelagency.api;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import travelagency.service.database.TravelAgencyDatabaseAuthenticator;
import travelagency.service.database.TravelAgencyServiceFactory;
import travelagency.service.service.data.TravelAgencyViewDataService;

import java.sql.SQLException;

/**
 * This class creates and starts the local REST-API server and coordinates the requests and responses.
 * The REST-API checks the credentials of the requester for every sent request and is able to
 * respond with hotel and flight connection data. If the requester sends a wrong request, a 400 HTTP status code
 * is sent.
 *
 * @author I551393
 * @version 1.1
 */
public class LocalServer {

    private final RestServiceImpl restServiceImpl;
    private final Authenticator authenticator;
    private final RequestHandler requestHandler;

    /**
     * Constructs a LocalServer instance, initializing required services and handlers.
     */
    public LocalServer() {
        TravelAgencyServiceFactory factory = new TravelAgencyDatabaseAuthenticator().loginToDataBase("DEMO_USER", "PASSWORD");
        TravelAgencyViewDataService dataService = factory.createViewDataService();
        restServiceImpl = new RestServiceImpl(dataService);
        authenticator = new Authenticator();
        requestHandler = new RequestHandler(authenticator, restServiceImpl);
    }

    public static void main(String[] args) throws IOException, SQLException {
        LocalServer localServer = new LocalServer();
        localServer.startServer();
    }

    /**
     * Starts the HTTP server, creates contexts for hotel and flight connections, and starts the database service.
     *
     * @throws IOException   if an I/O error occurs when starting the server
     * @throws SQLException  if an SQL error occurs when connecting to the database
     */
    public void startServer() throws IOException, SQLException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8500), 0);

        HttpContext bookingsContext = server.createContext("/getBookings");
        bookingsContext.setHandler(requestHandler::handleBookingsRequest);

        server.start();
    }
}
