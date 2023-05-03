package travel.travelagency.service.data;

import java.util.List;

import travel.travelagency.entities.Booking;
import travel.travelagency.entities.Trip;

public interface TravelAgencyViewDataService {

  /**
   * This method returns all bookings filtered customer ID, and customer's last name.
   * If no corresponding booking is found, this method shall return an empty <code>List</code> object.
   * @param customerID An <code>int</code> of the customer ID to be filtered by.
   * @param customerLastName A <code>String</code> of the customer's last name to be filtered by.
   *                     If this parameter is not equal to <code>null</code>,
   *                     the result shall contain all bookings from corresponding customers.
   *                     If this parameter is equal to <code>null</code>,
   *                     a RuntimeException shall be thrown.
   * @return A <code>List</code> object containing all corresponding bookings from the database.
   *         If no booking with the selected criteria is found,
   *         an empty <code>List</code> object shall be returned.
   */
  List<Booking> getBookings(int customerID, String customerLastName) throws RuntimeException;

  /**
   * This method returns all bookings filtered customer ID.
   * If no corresponding booking is found, this method shall return an empty <code>List</code> object.
   * @param customerID An <code>int</code> of the customer ID to be filtered by.
   * @return A <code>List</code> object containing all corresponding bookings from the database.
   *         If no booking with the selected criteria is found,
   *         an empty <code>List</code> object shall be returned.
   */
  List<Booking> getBookings(int customerID);

  /**
   * This method returns all bookings filtered customer's last name.
   * If no corresponding booking is found, this method shall return an empty <code>List</code> object.
   * @param customerLastName A <code>String</code> of the customer's last name to be filtered by.
   *                     If this parameter is not equal to <code>null</code>,
   *                     the result shall contain all bookings from corresponding customers.
   *                     If this parameter is equal to <code>null</code>,
   *                     a RuntimeException shall be thrown.
   * @return A <code>List</code> object containing all corresponding bookings from the database.
   *         If no booking with the selected criteria is found,
   *         an empty <code>List</code> object shall be returned.
   */
  List<Booking> getBookings(String customerLastName) throws RuntimeException;

  /**
   * This method returns all bookings from the database.
   * If no booking is found, this method shall return an empty <code>List</code> object.
   * @return A <code>List</code> object containing all bookings from the database.
   *         If no booking is found, an empty <code>List</code> object shall be returned.
   */
  List<Booking> getBookings() throws RuntimeException;

  /**
   * This method returns the booking with the specified booking ID, customer ID, and customer's last name.
   * If no corresponding booking is found, this method shall return <code>null</code>.
   * @param bookingID An <code>int</code> of the booking ID to be filtered by.
   * @param customerID An <code>int</code> of the customer ID to be filtered by.
   * @param customerLastName A <code>String</code> of the customer's last name to be filtered by.
   *                     If this parameter is not equal to <code>null</code>,
   *                     the result shall contain all bookings from corresponding customers.
   *                     If this parameter is equal to <code>null</code>,
   *                     a RuntimeException shall be thrown.
   * @return A <code>Booking</code> object of the booking from the database.
   */
  Booking getBooking(int bookingID, int customerID, String customerLastName) throws RuntimeException;

  /**
   * This method returns the booking with the specified booking ID, and customer ID.
   * If no corresponding booking is found, this method shall return <code>null</code>.
   * @param bookingID An <code>int</code> of the booking ID to be filtered by.
   * @param customerID An <code>int</code> of the customer ID to be filtered by.
   * @return A <code>Booking</code> object of the booking from the database.
   */
  Booking getBooking(int bookingID, int customerID) throws RuntimeException;

  /**
   * This method returns the booking with the specified booking ID, and customer's last name.
   * If no corresponding booking is found, this method shall return <code>null</code>.
   * @param bookingID An <code>int</code> of the booking ID to be filtered by.
   * @param customerLastName A <code>String</code> of the customer's last name to be filtered by.
   *                     If this parameter is not equal to <code>null</code>,
   *                     the result shall contain all bookings from corresponding customers.
   *                     If this parameter is equal to <code>null</code>,
   *                     a RuntimeException shall be thrown.
   * @return A <code>Booking</code> object of the booking from the database.
   */
  Booking getBooking(int bookingID, String customerLastName) throws RuntimeException;

  /**
   * This method returns the booking with the specified booking ID.
   * If no corresponding booking is found, this method shall return <code>null</code>.
   * @param bookingID An <code>int</code> of the booking ID to be filtered by.
   * @return A <code>Booking</code> object of the booking from the database.
   */
  Booking getBooking(int bookingID);

  /**
   * This method returns the trip with the specified trip ID.
   * If no corresponding trip is found, this method shall return <code>null</code>.
   * @param tripID An <code>int</code> of the trip ID to be filtered by.
   * @return A <code>Trip</code> object of the trip from the database.
   */
  Trip getTrip(int tripID);

}
