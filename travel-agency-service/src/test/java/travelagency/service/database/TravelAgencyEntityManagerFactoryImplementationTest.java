package travelagency.service.database;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TravelAgencyEntityManagerFactoryImplementationTest {

  @Test
  public void testConstructor() {
    Map<String, String> loginCredentials = new HashMap<>();
    loginCredentials.put("javax.persistence.jdbc.user", "DEMO_USER");
    loginCredentials.put("javax.persistence.jdbc.password", "PASSWORD");
    assertDoesNotThrow(() -> new TravelAgencyEntityManagerFactoryImplementation(loginCredentials));
  }

  @Test
  public void testCreateEntityManager() {
    Map<String, String> loginCredentials = new HashMap<>();
    loginCredentials.put("javax.persistence.jdbc.user", "DEMO_USER");
    loginCredentials.put("javax.persistence.jdbc.password", "PASSWORD");
    assertDoesNotThrow(() -> new TravelAgencyEntityManagerFactoryImplementation(loginCredentials));
    assertDoesNotThrow(() -> new TravelAgencyEntityManagerFactoryImplementation(loginCredentials).createEntityManager());
    assertNotEquals(null, new TravelAgencyEntityManagerFactoryImplementation(loginCredentials).createEntityManager());
  }

}
