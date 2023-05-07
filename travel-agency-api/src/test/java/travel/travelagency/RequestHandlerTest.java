package travel.travelagency;

import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import travelagency.api.Authenticator;
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
    private RequestHandler requestHandler;
    private OutputStream outputStream;

    @BeforeEach
    public void initialize() {
        httpExchange = mock(HttpExchange.class);
        authenticator = mock(Authenticator.class);
        restServiceImpl = mock(RestServiceImpl.class);
        requestHandler = new RequestHandler(authenticator, restServiceImpl);
        outputStream = mock(OutputStream.class);
    }
}