package travel.travelagency.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TripTest {

  private static Trip nullTrip, emptyTrip, trip, copyTrip, differentTrip;

  private static Set<HotelBooking> hotelBookingSet, copyHotelBookingSet, differentHotelBookingSet;

  private static Set<FlightBooking> flightBookingSet, copyFlightBookingSet, differentFlightBookingSet;

  @BeforeAll()
  public static void initializeObjects() {
    initializeHotelBookingSets();
    initializeFlightBookingSets();
    initializeTrips();
  }

  public static void initializeHotelBookingSets() {
    hotelBookingSet = new HashSet<>();
    hotelBookingSet.add(new HotelBooking(
      new Hotel(
        "Luxor Deluxe",
        20000.01,
        "GIB",
        new Address(
          "Meerenge von Gibraltar",
          "1",
          "00001",
          "Zwischen Marokko und Gibraltar",
          "Mittelmeer"
        )
      ),
      204,
      12
    ));
    hotelBookingSet.add(new HotelBooking(
      new Hotel(
        "Excelsior Hotel Ernst",
        500.00,
        "EUR",
        new Address("Trankgasse", "1-5", "50667", "Köln", "Deutschland")
      ),
      15,
      3
    ));
    copyHotelBookingSet = new HashSet<>();
    copyHotelBookingSet.add(new HotelBooking(
        new Hotel(
            "Luxor Deluxe",
            20000.01,
            "GIB",
            new Address(
                "Meerenge von Gibraltar",
                "1",
                "00001",
                "Zwischen Marokko und Gibraltar",
                "Mittelmeer"
            )
        ),
        204,
        12
    ));
    copyHotelBookingSet.add(new HotelBooking(
        new Hotel(
            "Excelsior Hotel Ernst",
            500.00,
            "EUR",
            new Address("Trankgasse", "1-5", "50667", "Köln", "Deutschland")
        ),
        15,
        3
    ));
    differentHotelBookingSet = new HashSet<>();
    differentHotelBookingSet.add(new HotelBooking(
        new Hotel(
            "Billig Hotel",
            1.99,
            "EUR",
            new Address(
                "Reeperbahn",
                "69",
                "12345",
                "Hamburg-St. Pauli",
                "Deutschland"
            )
        ),
        29,
        1
    ));
    differentHotelBookingSet.add(new HotelBooking(
        new Hotel(
            "Excelsior Hotel Ernst",
            500.00,
            "EUR",
            new Address("Trankgasse", "1-5", "50667", "Köln", "Deutschland")
        ),
        15,
        12
    ));
  }

  public static void initializeFlightBookingSets() {
    flightBookingSet = new HashSet<>();
    flightBookingSet.add(new FlightBooking(
      new Flight(
        new FlightConnection("DL", "0015", "FRA", "ATL"),
        LocalDate.of(2023, 5, 14),
        LocalTime.of(11, 30, 20),
        "UTC+02:00",
        LocalDate.of(2023, 5, 14),
        LocalTime.of(16, 15),
        "UTC-05:00",
        299.99,
        "EUR"
      ),
      36
    ));
    flightBookingSet.add(new FlightBooking(
      new Flight(
        new FlightConnection("DL", "0015", "FRA", "ATL"),
        LocalDate.of(2023, 5, 14),
        LocalTime.of(11, 30, 20),
        "UTC+02:00",
        LocalDate.of(2023, 5, 14),
        LocalTime.of(16, 15),
        "UTC-05:00",
        299.99,
        "EUR"
      ),
      19
    ));
    copyFlightBookingSet = new HashSet<>();
    copyFlightBookingSet.add(new FlightBooking(
        new Flight(
            new FlightConnection("DL", "0015", "FRA", "ATL"),
            LocalDate.of(2023, 5, 14),
            LocalTime.of(11, 30, 20),
            "UTC+02:00",
            LocalDate.of(2023, 5, 14),
            LocalTime.of(16, 15),
            "UTC-05:00",
            299.99,
            "EUR"
        ),
        36
    ));
    copyFlightBookingSet.add(new FlightBooking(
      new Flight(
        new FlightConnection("DL", "0015", "FRA", "ATL"),
        LocalDate.of(2023, 5, 14),
        LocalTime.of(11, 30, 20),
        "UTC+02:00",
        LocalDate.of(2023, 5, 14),
        LocalTime.of(16, 15),
        "UTC-05:00",
        299.99,
        "EUR"
      ),
      19
    ));
    differentFlightBookingSet = new HashSet<>();
    differentFlightBookingSet.add(new FlightBooking(
      new Flight(
        new FlightConnection("DL", "0016", "ATL", "FRA"),
        LocalDate.of(2023, 7, 30),
        LocalTime.of(15, 5),
        "UTC-05:00",
        LocalDate.of(2023, 7, 31),
        LocalTime.of(7, 30, 38),
        "UTC+02:00",
        599.99,
        "EUR"
      ),
      4
    ));
    differentFlightBookingSet.add(new FlightBooking(
      new Flight(
        new FlightConnection("DL", "0015", "FRA", "ATL"),
        LocalDate.of(2023, 5, 14),
        LocalTime.of(11, 30, 20),
        "UTC+02:00",
        LocalDate.of(2023, 5, 14),
        LocalTime.of(16, 15),
        "UTC-05:00",
        299.99,
        "EUR"
      ),
      19
    ));
  }

  public static void initializeTrips() {
    nullTrip = null;
    emptyTrip = new Trip();
    trip = new Trip(hotelBookingSet, flightBookingSet);
    copyTrip = new Trip(copyHotelBookingSet, copyFlightBookingSet);
    differentTrip = new Trip(differentHotelBookingSet, differentFlightBookingSet);
  }

  @Test()
  public void testEqualsMethodWithNull() {
    assertDoesNotThrow(() -> emptyTrip.equals(nullTrip));
    assertFalse(emptyTrip.equals(nullTrip));
  }

  @Test
  public void testEqualsMethodWithEmptyTrip() {
    Trip copyEmptyTrip = new Trip();
    assertDoesNotThrow(() -> emptyTrip.equals(copyEmptyTrip));
    assertTrue(emptyTrip.equals(copyEmptyTrip));
  }

  @Test
  public void testEqualsMethodWithIdenticalTrip() {
    assertDoesNotThrow(() -> trip.equals(copyTrip));
    assertTrue(trip.equals(copyTrip));
  }

  @Test
  public void testEqualsMethodWithDifferentTrip() {
    assertDoesNotThrow(() -> trip.equals(differentTrip));
    assertFalse(trip.equals(differentTrip));
  }

  @Test
  public void testEqualsMethodWithDifferentClass() {
    Object obj = new Object();
    assertDoesNotThrow(() -> trip.equals(obj));
    assertFalse(trip.equals(obj));
  }

  @Test
  public void testToStringMethod() {
    Trip testedTrip = new Trip(hotelBookingSet, flightBookingSet);

    String expectedResult =
        "Hotel Bookings:\n" +
            hotelBookingSet.toString() + '\n' +
        "Flight Bookings:\n" +
            flightBookingSet.toString();

    assertEquals(expectedResult, testedTrip.toString());
  }

  @Test
  public void testHashCodeMethodWithIdenticalTrip() {
    int expectedHashCode  = trip.hashCode();
    int actualHashCode    = copyTrip.hashCode();

    assertEquals(expectedHashCode, actualHashCode);
  }

  @Test
  public void testHashCodeMethodWithDifferentTrip() {
    int unexpectedHashCode  = trip.hashCode();
    int actualHashCode      = differentTrip.hashCode();

    assertNotEquals(unexpectedHashCode, actualHashCode);
  }

}