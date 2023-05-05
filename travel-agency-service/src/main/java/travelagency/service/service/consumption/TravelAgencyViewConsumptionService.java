package travelagency.service.service.consumption;

import java.util.List;

/**
 * This is an interface for a consumption service providing several methods to retrieve consumable objects from a data service.
 * The consumable objects are simplifications of jpa entities containing all the data necessary to the ui.
 * A service implementation should not be connected to the database directly, but use a data service instead.
 * @author I551381
 * @version 1.0
 */
public interface TravelAgencyViewConsumptionService {

  /**
   * This method returns a <code>List<BookingConsumable></code> object filtered by customer ID, and the customer's last name.
   * If no corresponding booking is found, this method shall return an empty <code>List</code> object.
   * @param customerID An <code>int</code> of the customer ID to be filtered by.
   * @param customerLastName A <code>String</code> of the customer's last name to be filtered by.
   *                     If this parameter is not equal to <code>null</code>,
   *                     the result shall contain all bookings from corresponding customers.
   *                     If this parameter is equal to <code>null</code>,
   *                     a RuntimeException shall be thrown.
   * @return A <code>List</code> object containing all corresponding <code>BookingConsumption</code> objects from the
   *         database. If no booking with the selected criteria is found,an empty <code>List</code> object shall be returned.
   */
  List<BookingConsumable> getBookings(int customerID, String customerLastName);

  /**
   * This method returns a <code>List<BookingConsumable></code> object filtered by customer ID.
   * If no corresponding booking is found, this method shall return an empty <code>List</code> object.
   * @param customerID An <code>int</code> of the customer ID to be filtered by.
   * @return A <code>List</code> object containing all corresponding <code>BookingConsumption</code> objects from the
   *         database. If no booking with the selected criteria is found,an empty <code>List</code> object shall be returned.
   */
  List<BookingConsumable> getBookings(int customerID);

  /**
   * This method returns a <code>List<BookingConsumable></code> object filtered by the customer's last name.
   * If no corresponding booking is found, this method shall return an empty <code>List</code> object.
   * @param customerLastName A <code>String</code> of the customer's last name to be filtered by.
   *                     If this parameter is not equal to <code>null</code>,
   *                     the result shall contain all bookings from corresponding customers.
   *                     If this parameter is equal to <code>null</code>,
   *                     a RuntimeException shall be thrown.
   * @return A <code>List</code> object containing all corresponding <code>BookingConsumption</code> objects from the
   *         database. If no booking with the selected criteria is found,an empty <code>List</code> object shall be returned.
   */
  List<BookingConsumable> getBookings(String customerLastName);

  /**
   * This method returns a <code>List<BookingConsumable></code> object containing all bookings from the database.
   * If no booking is found, this method shall return an empty <code>List</code> object.
   * @return A <code>List</code> object containing all <code>BookingConsumption</code> objects from the
   *         database. If no booking is found,an empty <code>List</code> object shall be returned.
   */
  List<BookingConsumable> getBookings();

  /**
   * This method returns a <code>List<BookingConsumable></code> object filtered by
   * booking ID, customer ID and the customer's last name.
   * Since the booking id is unique the resulting <code>List</code> object will be of length 1.
   * If no corresponding booking is found, this method shall return an empty <code>List</code> object.
   * @param bookingID An <code>int</code> of the booking ID to be filtered by.
   * @param customerID An <code>int</code> of the customer ID to be filtered by.
   * @param customerLastName A <code>String</code> of the customer's last name to be filtered by.
   *                         If this parameter is not equal to <code>null</code>,
   *                         the result shall contain all bookings from corresponding customers.
   *                         If this parameter is equal to <code>null</code>,
   *                         a RuntimeException shall be thrown.
   * @return A <code>List</code> object containing the corresponding <code>BookingConsumption</code> object from the
   *         database. This <code>List</code> object shall not have a length greater than 1
   *         If no booking with the selected criteria is found,an empty <code>List</code> object shall be returned.
   */
  List<BookingConsumable> getBooking(int bookingID, int customerID, String customerLastName);

  /**
   * This method returns a <code>List<BookingConsumable></code> object filtered by booking ID, and customer ID.
   * Since the booking id is unique the resulting <code>List</code> object will be of length 1.
   * If no corresponding booking is found, this method shall return an empty <code>List</code> object.
   * @param bookingID An <code>int</code> of the booking ID to be filtered by.
   * @param customerID An <code>int</code> of the customer ID to be filtered by.
   * @return A <code>List</code> object containing the corresponding <code>BookingConsumption</code> object from the
   *         database. This <code>List</code> object shall not have a length greater than 1
   *         If no booking with the selected criteria is found,an empty <code>List</code> object shall be returned.
   */
  List<BookingConsumable> getBooking(int bookingID, int customerID);

  /**
   * This method returns a <code>List<BookingConsumable></code> object filtered by
   * booking ID, and the customer's last name.
   * Since the booking id is unique the resulting <code>List</code> object will be of length 1.
   * If no corresponding booking is found, this method shall return an empty <code>List</code> object.
   * @param bookingID An <code>int</code> of the booking ID to be filtered by.
   * @param customerLastName A <code>String</code> of the customer's last name to be filtered by.
   *                         If this parameter is not equal to <code>null</code>,
   *                         the result shall contain all bookings from corresponding customers.
   *                         If this parameter is equal to <code>null</code>,
   *                         a RuntimeException shall be thrown.
   * @return A <code>List</code> object containing the corresponding <code>BookingConsumption</code> object from the
   *         database. This <code>List</code> object shall not have a length greater than 1
   *         If no booking with the selected criteria is found,an empty <code>List</code> object shall be returned.
   */
  List<BookingConsumable> getBooking(int bookingID, String customerLastName);

  /**
   * This method returns a <code>List<BookingConsumable></code> object filtered by booking ID.
   * Since the booking id is unique the resulting <code>List</code> object will be of length 1.
   * If no corresponding booking is found, this method shall return an empty <code>List</code> object.
   * @param bookingID An <code>int</code> of the booking ID to be filtered by.
   * @return A <code>List</code> object containing the corresponding <code>BookingConsumption</code> object from the
   *         database. This <code>List</code> object shall not have a length greater than 1
   *         If no booking with the selected criteria is found,an empty <code>List</code> object shall be returned.
   */
  List<BookingConsumable> getBooking(int bookingID);

  /**
   * This method returns a <code>List<TripConsumable></code> object containing all trips
   * associated with the booking whose booking id is provided as a parameter.
   * If no corresponding booking is found, this method shall throw a <code>RuntimeException</code>.
   * If the corresponding booking does not contain any trips,
   * this method shall return an empty <code>List</code> object.
   * @param bookingID An <code>int</code> identifying the booking whose trips shall be returned.
   * @return A <code>List</code> object containing all corresponding <code>TripConsumption</code> objects
   *         associated with the booking in question.
   *         If the booking does not contain any trips, an empty <code>List</code> object shall be returned.
   */
  List<TripConsumable> getTrips(int bookingID);

  /**
   * This method returns a <code>List<HotelBookingConsumption></code> object containing all hotel bookings
   * associated with the trip whose trip id is provided as a parameter.
   * If no corresponding trip is found, this method shall throw a <code>RuntimeException</code>.
   * If the corresponding trip does not contain any hotel bookings,
   * this method shall return an empty <code>List</code> object.
   * @param tripID An <code>int</code> identifying the trip whose hotel bookings shall be returned.
   * @return A <code>List</code> object containing all corresponding <code>HotelBookingConsumption</code>
   *         objects associated with the trip in question.
   *         If the trip does not contain any hotel bookings,
   *         an empty <code>List</code> object shall be returned.
   */
  List<HotelBookingConsumable> getHotelBookings(int tripID);

  /**
   * This method returns a <code>List<HotelBookingConsumption></code> object containing all flight bookings
   * associated with the trip whose trip id is provided as a parameter.
   * If no corresponding trip is found, this method shall throw a <code>RuntimeException</code>.
   * If the corresponding trip does not contain any flight bookings,
   * this method shall return an empty <code>List</code> object.
   * @param tripID An <code>int</code> identifying the trip whose flight bookings shall be returned.
   * @return A <code>List</code> object containing all corresponding <code>FlightBookingConsumption</code>
   *         objects associated with the trip in question.
   *         If the trip does not contain any flight bookings,
   *         an empty <code>List</code> object shall be returned.
   */
  List<FlightBookingConsumable> getFlightBookings(int tripID);

}
