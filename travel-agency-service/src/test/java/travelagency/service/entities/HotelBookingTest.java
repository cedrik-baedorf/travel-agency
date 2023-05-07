package travelagency.service.entities;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class HotelBookingTest {

  private static HotelBooking nullBooking, emptyBooking, booking, copyBooking, differentBooking;

  @BeforeAll
  public static void initializeHotelBookings() {
    nullBooking = null;
    emptyBooking = new HotelBooking();
    booking = new HotelBooking(
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
    );
    copyBooking = new HotelBooking(
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
    );
    differentBooking = new HotelBooking(
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
    );
  }

  @Test
  public void testGetTotalPrice() {
    double expectedPrice = 5600.00;

    Hotel hotel = new Hotel();
    hotel.setPricePerPerson(200.0);

    HotelBooking hotelBooking = new HotelBooking();
    hotelBooking.setHotel(hotel);
    hotelBooking.setNumberOfGuests(7);
    hotelBooking.setNumberOfNights(4);

    double actualPrice = hotelBooking.getTotalPrice();

    assertEquals(expectedPrice, actualPrice);
  }

  @Test()
  public void testEqualsMethodWithNull() {
    assertDoesNotThrow(() -> emptyBooking.equals(nullBooking));
    assertFalse(emptyBooking.equals(nullBooking));
  }

  @Test
  public void testEqualsMethodWithEmptyHotelBooking() {
    HotelBooking copyEmptyBooking = new HotelBooking();
    assertDoesNotThrow(() -> emptyBooking.equals(copyEmptyBooking));
    assertTrue(emptyBooking.equals(copyEmptyBooking));
  }

  @Test
  public void testEqualsMethodWithIdenticalHotelBooking() {
    assertDoesNotThrow(() -> booking.equals(copyBooking));
    assertTrue(booking.equals(copyBooking));
  }

  @Test
  public void testEqualsMethodWithDifferentHotelBooking() {
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
    HotelBooking testedHotelBooking = new HotelBooking(
      new Hotel(
      "Excelsior Hotel Ernst",
      500.00,
        new Address("Trankgasse", "1-5", "50667", "Köln", "Deutschland")
      ),
      15,
      3
    );

    String expectedResult = """
        Excelsior Hotel Ernst
        Trankgasse 1-5, 50667 Köln, Deutschland
        Price: 500.0
        Guests: 15
        Nights: 3""";

    assertEquals(expectedResult, testedHotelBooking.toString());
  }

  @Test
  public void testHashCodeMethodWithIdenticalHotelBooking() {
    int expectedHashCode  = booking.hashCode();
    int actualHashCode    = copyBooking.hashCode();

    assertEquals(expectedHashCode, actualHashCode);
  }

  @Test
  public void testHashCodeMethodWithDifferentHotelBooking() {
    int unexpectedHashCode  = booking.hashCode();
    int actualHashCode      = differentBooking.hashCode();

    assertNotEquals(unexpectedHashCode, actualHashCode);
  }

}
