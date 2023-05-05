package travel.travelagency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import travelagency.api.Authenticator;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthenticatorTest {

    private Authenticator authenticator;

    @BeforeEach
    public void setUp() {
        authenticator = new Authenticator();
    }

    @Test
    public void testGetCredentialsMap() {
        Map<String, String> credentials = authenticator.getCredentialsMap();
        assertEquals("123", credentials.get("demo"));
        assertEquals("password", credentials.get("flo"));
    }

    @Test
    public void testExtractUsername() {
        String uri = "http://localhost:8500/getHotels?username=demo&password=123";
        assertEquals("demo", Authenticator.extractUsername(uri));
    }

    @Test
    public void testExtractPassword() {
        String uri = "http://localhost:8500/getHotels?username=demo&password=123";
        assertEquals("123", Authenticator.extractPassword(uri));
    }

    @Test
    public void testCheckCredentials() {
        Map<String, String> credentials = authenticator.getCredentialsMap();
        assertTrue(Authenticator.checkCredentials(credentials, "demo", "123"));
        assertTrue(Authenticator.checkCredentials(credentials, "flo", "password"));
        assertFalse(Authenticator.checkCredentials(credentials, "unknown", "123"));
        assertFalse(Authenticator.checkCredentials(credentials, "demo", "wrongpassword"));
    }
}
