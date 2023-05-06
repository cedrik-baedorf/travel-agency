package travelagency.service.service.data;

import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import travelagency.service.entities.Booking;
import travelagency.service.entities.Trip;

public class TravelAgencyViewDataServiceImplementation implements TravelAgencyViewDataService {

  /**
   * Logger for errors and additional information
   */
  static final Logger logger = LogManager.getLogger(TravelAgencyViewDataServiceImplementation.class);

  /**
   * <code>EntityManager</code> object used to retrieve entities from the database.
   */
  private final EntityManager EM;

  /**
   * Message if the customerLastName parameter is equal to <code>null</code>
   */
  private static final String MSG_CUSTOMER_LASTNAME_NULL =
      "Cannot filter by customerLastName = 'null'";

  /**
   * Message if the corresponding entity is/entities are not found
   */
  private static final String MSG_ENTITY_NOT_FOUND =
      "Could not retrieve %s with %s = %s so null was returned.";

  /**
   * Message if entityManager is unable to retrieve the entities specified
   */
  private static final String MSG_INVALID_ENTITY_MANAGER =
      "Unable to locate persister for class %s. Make sure to run the database setup properly.";

  public TravelAgencyViewDataServiceImplementation(EntityManager entityManager) {
    this.EM = entityManager;
  }

  /**
   * Private method returning a <code>List</code> object containing all bookings filtered by the selected criteria.
   * Each parameter with the <code>null</code> value will be ignored in the filter.
   * If no corresponding <code>Booking</code> object is found, this method returns an empty <code>List</code> object.
   * @param customerID customer ID to be filtered by.
   * @param customerLastName customer's last name to be filtered by.
   * @return A <code>List</code> object containing all bookings matching the filters above.
   */
  private List<Booking> getBookingList(Integer customerID, String customerLastName) {
    try {
      TypedQuery<Booking> typedQuery = EM.createNamedQuery(Booking.FIND_WITH_FILTERS, Booking.class)
              .setParameter(Booking.CUSTOMER_ID, customerID)
              .setParameter(Booking.CUSTOMER_LASTNAME, customerLastName);
      return typedQuery.getResultList();
    } catch (IllegalArgumentException e) {
      final String MSG = String.format(MSG_INVALID_ENTITY_MANAGER, Booking.class);
      logger.error(MSG);
      throw new RuntimeException(MSG);
    }
  }

  /**
   * Private method returning a <code>Booking</code> object with the provided booking ID.
   * If no corresponding <code>Booking</code> object is found,
   * this method returns <code>null</code>.
   * @param bookingID booking ID to be filtered by.
   * @return The <code>Booking</code> object to the corresponding booking ID.
   */
  private Booking getSingleBooking(int bookingID) {
    try {
      Booking booking = EM.find(Booking.class, bookingID);
      if (booking == null) {
        final String MSG = String.format(MSG_ENTITY_NOT_FOUND, "Booking", "ID", bookingID);
        throw new RuntimeException(MSG);
      }
      return booking;
    } catch (IllegalArgumentException e) {
      final String MSG = String.format(MSG_INVALID_ENTITY_MANAGER, Booking.class);
      logger.error(MSG);
      throw new RuntimeException(MSG);
    }
  }

  /**
   * Private method returning a <code>Trip</code> object with the provided trip ID.
   * If no corresponding <code>Trip</code> object is found,
   * this method returns <code>null</code>.
   * @param tripID trip ID to be filtered by.
   * @return The <code>Trip</code> object to the corresponding trip ID.
   */
  private Trip getSingleTrip(int tripID) {
    try {
      Trip trip = EM.find(Trip.class, tripID);
      if(trip == null) {
        final String MSG = String.format(MSG_ENTITY_NOT_FOUND, "Trip", "ID", trip);
        throw new RuntimeException(MSG);
      }
      return trip;
    } catch (IllegalArgumentException e) {
      final String MSG = String.format(MSG_INVALID_ENTITY_MANAGER, Trip.class);
      logger.error(MSG);
      throw new RuntimeException(MSG);
    }
  }

  @Override
  public List<Booking> getBookings(int customerID, String customerLastName) throws RuntimeException {
    if(customerLastName == null) {
      logger.warn(MSG_CUSTOMER_LASTNAME_NULL);
      throw new RuntimeException(MSG_CUSTOMER_LASTNAME_NULL);
    }
    return getBookingList(customerID, customerLastName);
  }

  @Override
  public List<Booking> getBookings(int customerID) {
    return getBookingList(customerID, null);
  }

  @Override
  public List<Booking> getBookings(String customerLastName) throws RuntimeException {
    if(customerLastName == null) {
      logger.warn(MSG_CUSTOMER_LASTNAME_NULL);
      throw new RuntimeException(MSG_CUSTOMER_LASTNAME_NULL);
    }
    return getBookingList(null, customerLastName);
  }

  @Override
  public List<Booking> getBookings() {
    return getBookingList(null, null);
  }

  @Override
  public Booking getBooking(int bookingID, int customerID, String customerLastName) throws RuntimeException {
    if(customerLastName == null) {
      logger.warn(MSG_CUSTOMER_LASTNAME_NULL);
      throw new RuntimeException(MSG_CUSTOMER_LASTNAME_NULL);
    }
    try {
      Booking booking = getBooking(bookingID, customerID);
      if(! customerLastName.equals(booking.getCustomer().getPersonalData().getLastName())) {
        final String MSG = String.format(MSG_ENTITY_NOT_FOUND, "Booking", "customerLastName", customerLastName);
        logger.info(MSG);
        return null;
      }
      return booking;
    } catch (RuntimeException e) {
      logger.info(e.getMessage());
      return null;
    }
  }

  @Override
  public Booking getBooking(int bookingID, int customerID) throws RuntimeException {
    try {
      Booking booking = getSingleBooking(bookingID);
      if (customerID != booking.getCustomer().getID()) {
        final String MSG = String.format(MSG_ENTITY_NOT_FOUND, "Booking", "customerID", customerID);
        logger.info(MSG);
        return null;
      }
      return booking;
    } catch (RuntimeException e) {
      logger.info(e.getMessage());
      return null;
    }
  }

  @Override
  public Booking getBooking(int bookingID, String customerLastName) throws RuntimeException {
    if(customerLastName == null) {
      logger.warn(MSG_CUSTOMER_LASTNAME_NULL);
      throw new RuntimeException(MSG_CUSTOMER_LASTNAME_NULL);
    }
    try {
      Booking booking = getSingleBooking(bookingID);
      if (!customerLastName.equals(booking.getCustomer().getPersonalData().getLastName())) {
        final String MSG = String.format(MSG_ENTITY_NOT_FOUND, "Booking", "customerLastName", customerLastName);
        logger.info(MSG);
        return null;
      }
      return booking;
    } catch (RuntimeException e) {
      logger.info(e.getMessage());
      return null;
    }
  }

  @Override
  public Booking getBooking(int bookingID) {
    try {
      return getSingleBooking(bookingID);
    } catch (RuntimeException e) {
      logger.info(e.getMessage());
      return null;
    }
  }

  @Override
  public Trip getTrip(int tripID) {
    try {
      return getSingleTrip(tripID);
    } catch (RuntimeException e) {
      logger.info(e.getMessage());
      return null;
    }
  }

}
