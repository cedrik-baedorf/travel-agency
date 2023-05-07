package travelagency.service.entities;

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

public class BookingTest {

  private static Booking nullBooking, emptyBooking, booking, copyBooking, differentBooking;
  private static Customer customer, copyCustomer, differentCustomer;
  private static Set<Trip> tripSet, copyTripSet, differentTripSet;
  private static Set<HotelBooking> hotelBookingSet, copyHotelBookingSet, differentHotelBookingSet;
  private static Set<FlightBooking> flightBookingSet, copyFlightBookingSet, differentFlightBookingSet;

  @BeforeAll()
  public static void initializeObjects() {
    initializeCustomers();
    initializeHotelBookingSets();
    initializeFlightBookingSets();
    initializeTripSets();
    initializeBookings();
  }

  public static void initializeCustomers() {
    customer = new Customer(
        "DE19582919875254589658745236512589",
        new PersonalData(
            "Merkel",
            "Angela",
            "Dorothea",
            LocalDate.of(1954, 7, 17),
            new Address("Street", "18", "93726", "Town", "Country")
        ),
        new Address("Street","101a","19824","My Town","Disneyland" )
    );
    copyCustomer = new Customer(
        "DE19582919875254589658745236512589",
        new PersonalData(
            "Merkel",
            "Angela",
            "Dorothea",
            LocalDate.of(1954, 7, 17),
            new Address("Street", "18", "93726", "Town", "Country")
        ),
        new Address("Street","101a","19824","My Town","Disneyland" )
    );
    differentCustomer = new Customer(
        "DE12457886135615487659132658132548",
        new PersonalData(
            "Scholz",
            "Olaf",
            "",
            LocalDate.of(1987, 11, 17),
            new Address("Way", "9", "65958", "Stadt", "Osmanien")
        ),
        new Address("Dwy", "18b", "HKL21", "Town", "Ivy")
    );
  }

  public static void initializeHotelBookingSets() {
    hotelBookingSet = new HashSet<>();
    hotelBookingSet.add(new HotelBooking(
        new Hotel(
            "Luxor Deluxe",
            20000.01,
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

  public static void initializeTripSets() {
    tripSet = new HashSet<>();
    tripSet.add(new Trip(hotelBookingSet, flightBookingSet));
    copyTripSet = new HashSet<>();
    copyTripSet.add(new Trip(copyHotelBookingSet, copyFlightBookingSet));
    differentTripSet = new HashSet<>();
    differentTripSet.add(new Trip(differentHotelBookingSet, differentFlightBookingSet));
  }

  public static void initializeBookings() {
    nullBooking = null;
    emptyBooking = new Booking();
    booking = new Booking(customer, LocalDate.of(2023, 4, 20), tripSet);
    copyBooking = new Booking(copyCustomer, LocalDate.of(2023, 4, 20), copyTripSet);
    differentBooking = new Booking(differentCustomer, LocalDate.of(2023, 8, 15), differentTripSet);
  }

  @Test()
  public void testEqualsMethodWithNull() {
    assertDoesNotThrow(() -> emptyBooking.equals(nullBooking));
    assertFalse(emptyBooking.equals(nullBooking));
  }

  @Test
  public void testEqualsMethodWithEmptyBooking() {
    Booking copyEmptyBooking = new Booking();
    assertDoesNotThrow(() -> emptyBooking.equals(copyEmptyBooking));
    assertTrue(emptyBooking.equals(copyEmptyBooking));
  }

  @Test
  public void testEqualsMethodWithIdenticalBooking() {
    assertDoesNotThrow(() -> booking.equals(copyBooking));
    assertTrue(booking.equals(copyBooking));
  }

  @Test
  public void testEqualsMethodWithDifferentBooking() {
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
    LocalDate bookingDate = LocalDate.of(2023, 4, 20);
    Booking testedBooking = new Booking(customer, bookingDate, tripSet);

    String expectedResult =
      "Booking ID: null\n" +
      "Date: " + bookingDate + '\n' +
      "Customer:\n" +
          customer.toString() + '\n' +
      "Trips:\n" +
          tripSet.toString();

    assertEquals(expectedResult, testedBooking.toString());
  }

  @Test
  public void testHashCodeMethodWithIdenticalBooking() {
    int expectedHashCode  = booking.hashCode();
    int actualHashCode    = copyBooking.hashCode();

    assertEquals(expectedHashCode, actualHashCode);
  }

  @Test
  public void testHashCodeMethodWithDifferentBooking() {
    int unexpectedHashCode  = booking.hashCode();
    int actualHashCode      = differentBooking.hashCode();

    assertNotEquals(unexpectedHashCode, actualHashCode);
  }

}