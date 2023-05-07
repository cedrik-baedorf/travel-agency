package travelagency.service.entities;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FlightTest {

  private static Flight nullFlight, emptyFlight, flight, copyFlight, differentFlight;

  @BeforeAll
  public static void initializeConnections() {
    nullFlight = null;
    emptyFlight = new Flight();
    flight = new Flight(
        new FlightConnection("DL", "0015", "FRA", "ATL"),
        LocalDate.of(2023, 5, 14),
        LocalTime.of(11, 30, 20),
        "UTC+02:00",
        LocalDate.of(2023, 5, 14),
        LocalTime.of(16, 15),
        "UTC-05:00",
        299.99
    );
    copyFlight = new Flight(
        new FlightConnection("DL", "0015", "FRA", "ATL"),
        LocalDate.of(2023, 5, 14),
        LocalTime.of(11, 30, 20),
        "UTC+02:00",
        LocalDate.of(2023, 5, 14),
        LocalTime.of(16, 15),
        "UTC-05:00",
        299.99
    );
    differentFlight = new Flight(
        new FlightConnection("DL", "0016", "ATL", "FRA"),
        LocalDate.of(2023, 7, 30),
        LocalTime.of(15, 5),
        "UTC-05:00",
        LocalDate.of(2023, 7, 31),
        LocalTime.of(7, 30, 38),
        "UTC+02:00",
        599.99
    );
  }

  /**
   * This test is designed to test the getDepartureTimestamp() method on an empty Flight object.
   * The method should return <code>null</code>.
   */
  @Test
  public void testGetDepartureTimestampWithEmpty() {
    assertDoesNotThrow(() -> emptyFlight.getDepartureTimestamp());
    assertNull(emptyFlight.getDepartureTimestamp());
  }

  /**
   * This test is designed to test the getArrivalTimestamp() method on an empty Flight object.
   * The method should return <code>null</code>.
   */
  @Test
  public void testGetArrivalTimestampWithEmpty() {
    assertDoesNotThrow(() -> emptyFlight.getArrivalTimestamp());
    assertNull(emptyFlight.getArrivalTimestamp());
  }

  /**
   * This test is designed to test the getDepartureTimestamp() method on a valid Flight object.
   * The method should return the correct timestamp.
   */
  @Test
  public void testGetDepartureTimestampWithValidFlight() {
    ZonedDateTime expectedTimestamp = ZonedDateTime.of(
        LocalDate.of(2023, 5, 14),
        LocalTime.of(11, 30, 20),
        ZoneId.of("UTC+02:00")
    );

    assertDoesNotThrow(() -> flight.getDepartureTimestamp());
    assertEquals(expectedTimestamp, flight.getDepartureTimestamp());
  }

  /**
   * This test is designed to test the getArrivalTimestamp() method on a valid Flight object.
   * The method should return the correct timestamp.
   */
  @Test
  public void testGetArrivalTimestampWithValidFlight() {
    ZonedDateTime expectedTimestamp = ZonedDateTime.of(
        LocalDate.of(2023, 5, 14),
        LocalTime.of(16, 15),
        ZoneId.of("UTC-05:00")
    );

    assertDoesNotThrow(() -> flight.getArrivalTimestamp());
    assertEquals(expectedTimestamp, flight.getArrivalTimestamp());
  }


  /**
   * This test is designed to test the getFlightDuration() method on an empty Flight object.
   * The method should return <code>null</code>.
   */
  @Test
  public void testGetFlightDurationWithEmpty() {
    assertDoesNotThrow(() -> emptyFlight.getFlightDuration());
    assertNull(emptyFlight.getFlightDuration());
  }

  /**
   * This test is designed to test the getFlightDuration() method on an empty Flight object.
   * The method should return <code>null</code>.
   */
  @Test
  public void testGetFlightDurationWithValidFlight() {
    Duration expectedDuration = Duration.ofMinutes(704);
    assertDoesNotThrow(() -> flight.getFlightDuration());
    assertEquals(expectedDuration, flight.getFlightDuration());
  }

  @Test()
  public void testEqualsMethodWithNull() {
    assertDoesNotThrow(() -> emptyFlight.equals(nullFlight));
    assertFalse(emptyFlight.equals(nullFlight));
  }

  @Test
  public void testEqualsMethodWithEmptyFlightConnection() {
    Flight copyEmptyFlight = new Flight();
    assertDoesNotThrow(() -> emptyFlight.equals(copyEmptyFlight));
    assertTrue(emptyFlight.equals(copyEmptyFlight));
  }

  @Test
  public void testEqualsMethodWithIdenticalFlightConnection() {
    assertDoesNotThrow(() -> flight.equals(copyFlight));
    assertTrue(flight.equals(copyFlight));
  }

  @Test
  public void testEqualsMethodWithDifferentFlightConnection() {
    assertDoesNotThrow(() -> flight.equals(differentFlight));
    assertFalse(flight.equals(differentFlight));
  }

  @Test
  public void testEqualsMethodWithDifferentClass() {
    Object obj = new Object();
    assertDoesNotThrow(() -> flight.equals(obj));
    assertFalse(flight.equals(obj));
  }

  @Test
  public void testToStringMethod() {
    Flight testedFlight = new Flight(
        new FlightConnection("DL", "0015", "FRA", "ATL"),
        LocalDate.of(2023, 5, 14),
        LocalTime.of(11, 30, 20),
        "UTC+02:00",
        LocalDate.of(2023, 5, 14),
        LocalTime.of(16, 15),
        "UTC-05:00",
        299.99
    );

    String expectedResult = """
        DL0015 from FRA to ATL
        Departure:\s""" + testedFlight.getDepartureTimestamp().toString() + '\n' + """
        Arrival  :\s""" + testedFlight.getArrivalTimestamp().toString() + '\n' + """
        Price    : 299.99""";

    assertEquals(expectedResult, testedFlight.toString());
  }

  @Test
  public void testHashCodeMethodWithIdenticalFlightConnection() {
    int expectedHashCode  = flight.hashCode();
    int actualHashCode    = copyFlight.hashCode();

    assertEquals(expectedHashCode, actualHashCode);
  }

  @Test
  public void testHashCodeMethodWithDifferentFlightConnection() {
    int unexpectedHashCode  = flight.hashCode();
    int actualHashCode      = differentFlight.hashCode();

    assertNotEquals(unexpectedHashCode, actualHashCode);
  }

}
