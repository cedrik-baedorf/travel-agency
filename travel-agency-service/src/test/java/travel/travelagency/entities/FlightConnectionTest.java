package travel.travelagency.entities;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FlightConnectionTest {

  private static FlightConnection nullConnection, emptyConnection, connection, copyConnection, differentConnection;

  @BeforeAll
  public static void initializeConnections() {
    nullConnection = null;
    emptyConnection = new FlightConnection();
    connection = new FlightConnection("LH", "1234", "CGN", "DUB");
    copyConnection = new FlightConnection("LH", "1234", "CGN", "DUB");
    differentConnection = new FlightConnection("DL", "0005", "QKL", "LAX");
  }

  @Test()
  public void testEqualsMethodWithNull() {
    assertDoesNotThrow(() -> emptyConnection.equals(nullConnection));
    assertFalse(emptyConnection.equals(nullConnection));
  }

  @Test
  public void testEqualsMethodWithEmptyFlightConnection() {
    FlightConnection copyEmptyConnection = new FlightConnection();
    assertDoesNotThrow(() -> emptyConnection.equals(copyEmptyConnection));
    assertTrue(emptyConnection.equals(copyEmptyConnection));
  }

  @Test
  public void testEqualsMethodWithIdenticalFlightConnection() {
    assertDoesNotThrow(() -> connection.equals(copyConnection));
    assertTrue(connection.equals(copyConnection));
  }

  @Test
  public void testEqualsMethodWithDifferentFlightConnection() {
    assertDoesNotThrow(() -> connection.equals(differentConnection));
    assertFalse(connection.equals(differentConnection));
  }

  @Test
  public void testEqualsMethodWithDifferentClass() {
    Object obj = new Object();
    assertDoesNotThrow(() -> connection.equals(obj));
    assertFalse(connection.equals(obj));
  }

  @Test
  public void testToStringMethod() {
    FlightConnection fullConnection =
        new FlightConnection("DL", "0015", "FRA", "ATL");
    FlightConnection noConnConnection =
        new FlightConnection("DL", null, "FRA", "ATL");
    FlightConnection noCarrConnection =
        new FlightConnection(null, "0015", "FRA", "ATL");
    FlightConnection noDepConnection =
        new FlightConnection("DL", "0015", null, "ATL");
    FlightConnection noArrConnection =
        new FlightConnection("DL", "0015", "FRA", null);
    FlightConnection noDepAndArrConnection =
        new FlightConnection("DL", "0015", null, null);

    assertEquals("DL0015 from FRA to ATL", fullConnection.toString());
    assertEquals("DL____ from FRA to ATL", noConnConnection.toString());
    assertEquals("__0015 from FRA to ATL", noCarrConnection.toString());
    assertEquals("DL0015 to ATL", noDepConnection.toString());
    assertEquals("DL0015 from FRA", noArrConnection.toString());
    assertEquals("DL0015", noDepAndArrConnection.toString());
  }

  @Test
  public void testHashCodeMethodWithIdenticalFlightConnection() {
    int expectedHashCode  = connection.hashCode();
    int actualHashCode    = copyConnection.hashCode();

    assertEquals(expectedHashCode, actualHashCode);
  }

  @Test
  public void testHashCodeMethodWithDifferentFlightConnection() {
    int unexpectedHashCode  = connection.hashCode();
    int actualHashCode      = differentConnection.hashCode();

    assertNotEquals(unexpectedHashCode, actualHashCode);
  }

}
