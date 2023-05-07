package travel.travelagency;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import travelagency.api.LocalServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {

    private static final String BASE_URL = "http://localhost:8500";

    @BeforeAll
    public static void setUp() {
        // Start the local server
        new Thread(() -> {
            try {
                LocalServer.main(new String[]{});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @AfterAll
    public static void tearDown() {
        // Stop the local server after tests (if applicable)
    }

    @Test
    public void testValidHotelsRequest() throws Exception {
        URL url = new URL(BASE_URL + "/getHotels?username=demo&password=123");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        connection.disconnect();

        assertEquals(200, responseCode);
    }

    @Test
    public void testInvalidHotelsRequest() throws Exception {
        URL url = new URL(BASE_URL + "/getHotels?username=demo&password=wrongpassword");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        assertEquals(401, responseCode);
    }
}
