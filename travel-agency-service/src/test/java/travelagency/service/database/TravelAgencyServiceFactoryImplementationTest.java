package travelagency.service.database;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TravelAgencyServiceFactoryImplementationTest {

  @Test
  public void testConstructor() {
    Map<String, String> loginCredentials = new HashMap<>();
    loginCredentials.put("javax.persistence.jdbc.user", "DEMO_USER");
    loginCredentials.put("javax.persistence.jdbc.password", "PASSWORD");
    assertDoesNotThrow(() -> new TravelAgencyServiceFactoryImplementation(loginCredentials));
  }

  @Test
  public void testCreateEntityManager() {
    Map<String, String> loginCredentials = new HashMap<>();
    loginCredentials.put("javax.persistence.jdbc.user", "DEMO_USER");
    loginCredentials.put("javax.persistence.jdbc.password", "PASSWORD");
    assertDoesNotThrow(() -> new TravelAgencyServiceFactoryImplementation(loginCredentials));
    assertDoesNotThrow(() -> new TravelAgencyServiceFactoryImplementation(loginCredentials).createEntityManager());
    assertNotEquals(null, new TravelAgencyServiceFactoryImplementation(loginCredentials).createEntityManager());
  }

}
