package travelagency.service.entities;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FlightBookingTest {

  private static FlightBooking nullBooking, emptyBooking, booking, copyBooking, differentBooking;

  @BeforeAll
  public static void initializeFlightBookings() {
    nullBooking = null;
    emptyBooking = new FlightBooking();
    booking = new FlightBooking(
        new Flight(
          new FlightConnection("DL", "0015", "FRA", "ATL"),
          LocalDate.of(2023, 5, 14),
          LocalTime.of(11, 30, 20),
          "UTC+02:00",
          LocalDate.of(2023, 5, 14),
          LocalTime.of(16, 15),
          "UTC-05:00",
          299.99
        ),
        36
    );
    copyBooking = new FlightBooking(
        new Flight(
            new FlightConnection("DL", "0015", "FRA", "ATL"),
            LocalDate.of(2023, 5, 14),
            LocalTime.of(11, 30, 20),
            "UTC+02:00",
            LocalDate.of(2023, 5, 14),
            LocalTime.of(16, 15),
            "UTC-05:00",
            299.99
        ),
        36
    );
    differentBooking = new FlightBooking(
        new Flight(
            new FlightConnection("DL", "0016", "ATL", "FRA"),
            LocalDate.of(2023, 7, 30),
            LocalTime.of(15, 5),
            "UTC-05:00",
            LocalDate.of(2023, 7, 31),
            LocalTime.of(7, 30, 38),
            "UTC+02:00",
            599.99
        ),
        4
    );
  }

  @Test
  public void testGetTotalPrice() {
    double expectedPrice = 200.0;

    Flight flight = new Flight();
    flight.setPricePerPerson(50.0);

    FlightBooking flightBooking = new FlightBooking();
    flightBooking.setFlight(flight);
    flightBooking.setNumberOfPassengers(4);

    double actualPrice = flightBooking.getTotalPrice();

    assertEquals(expectedPrice, actualPrice);
  }

  @Test()
  public void testEqualsMethodWithNull() {
    assertDoesNotThrow(() -> emptyBooking.equals(nullBooking));
    assertFalse(emptyBooking.equals(nullBooking));
  }

  @Test
  public void testEqualsMethodWithEmptyFlightBooking() {
    FlightBooking copyEmptyBooking = new FlightBooking();
    assertDoesNotThrow(() -> emptyBooking.equals(copyEmptyBooking));
    assertTrue(emptyBooking.equals(copyEmptyBooking));
  }

  @Test
  public void testEqualsMethodWithIdenticalFlightBooking() {
    assertDoesNotThrow(() -> booking.equals(copyBooking));
    assertTrue(booking.equals(copyBooking));
  }

  @Test
  public void testEqualsMethodWithDifferentFlightBooking() {
    assertDoesNotThrow(() -> booking.equals(differentBooking));
    assertFalse(booking.equals(differentBooking));
  }

  @Test
  public void testEqualsMethodWithDifferentClass() {
    Object obj = new Object();
    assertDoesNotThrow(() -> booking.equals(obj));
    assertFalse(booking.equals(obj));
  }

  @Test
  public void testToStringMethod() {
    FlightBooking testedFlightBooking = new FlightBooking(
      new Flight(
        new FlightConnection("DL", "0015", "FRA", "ATL"),
        LocalDate.of(2023, 5, 14),
        LocalTime.of(11, 30, 20),
        "UTC+02:00",
        LocalDate.of(2023, 5, 14),
        LocalTime.of(16, 15),
        "UTC-05:00",
        299.99
      ),
      19
    );

    String expectedResult = """
        DL0015 from FRA to ATL
        Departure:\s""" + testedFlightBooking.getFlight().getDepartureTimestamp().toString() + '\n' + """
        Arrival  :\s""" + testedFlightBooking.getFlight().getArrivalTimestamp().toString() + '\n' + """
        Price    : 299.99
        Passengers: 19""";

    assertEquals(expectedResult, testedFlightBooking.toString());
  }

  @Test
  public void testHashCodeMethodWithIdenticalFlightBooking() {
    int expectedHashCode  = booking.hashCode();
    int actualHashCode    = copyBooking.hashCode();

    assertEquals(expectedHashCode, actualHashCode);
  }

  @Test
  public void testHashCodeMethodWithDifferentFlightBooking() {
    int unexpectedHashCode  = booking.hashCode();
    int actualHashCode      = differentBooking.hashCode();

    assertNotEquals(unexpectedHashCode, actualHashCode);
  }

}