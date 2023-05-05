package travelagency.service.database;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TravelAgencyEntityManagerFactoryTest {

  @Test
  public void testConstructor() {
    Map<String, String> loginCredentials = new HashMap<>();
    loginCredentials.put("javax.persistence.jdbc.user", "DEMO_USER");
    loginCredentials.put("javax.persistence.jdbc.password", "PASSWORD");
    assertDoesNotThrow(() -> new TravelAgencyEntityManagerFactory(loginCredentials));
  }

  @Test
  public void testCreateEntityManager() {
    Map<String, String> loginCredentials = new HashMap<>();
    loginCredentials.put("javax.persistence.jdbc.user", "DEMO_USER");
    loginCredentials.put("javax.persistence.jdbc.password", "PASSWORD");
    assertDoesNotThrow(() -> new TravelAgencyEntityManagerFactory(loginCredentials));
    assertDoesNotThrow(() -> new TravelAgencyEntityManagerFactory(loginCredentials).createEntityManager());
    assertNotEquals(null, new TravelAgencyEntityManagerFactory(loginCredentials).createEntityManager());
  }

}
