package travelagency.service.entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * This class is a jpa entity to the corresponding table 'BOOKING'
 * It contains several prepared statements that allow for queries of filtered bookings
 * in the database 'travel-agency-service_db'.
 * @author I551381
 * @version 1.0
 */
@Entity
@Table(name = "BOOKING")
@NamedQueries({
    @NamedQuery(
        name = Booking.FIND_ALL,
        query = "SELECT booking FROM Booking booking"
    ),
    @NamedQuery(
        name = Booking.FIND_WITH_FILTERS,
        query = """
            SELECT booking FROM Booking booking
            WHERE booking.customer.id                     = coalesce(:customerID, booking.customer.id)
            AND   booking.customer.personalData.lastName  = coalesce(:customerName, booking.customer.personalData.lastName)"""
    )
})
public class Booking {

  /**
   * This constant represents the name of a named query returning all bookings.
   */
  public static final String FIND_ALL = "Booking.findAll";

  /**
   * This constant represents the name of a named query returning all bookings filtered by
   * bookingID, customerID, and lastName of the customer.
   */
  public static final String FIND_WITH_FILTERS = "Booking.findWithFilters";

  /**
   * This constant represents the name of the parameter to filter by customer ID in the named query
   * 'Booking.findWithFilters'.
   */
  public static final String CUSTOMER_ID = "customerID";

  /**
   * This constant represents the name of the parameter to filter by customer's last name in the named query
   * 'Booking.findWithFilters'.
   */
  public static final String CUSTOMER_LASTNAME = "customerName";

  /**
   * Unique identifier for each booking record (primary key in the database).
   * This value will be generated automatically.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "BOOKING_ID")
  private Integer id;

  /**
   * Customer paying for this booking
   */
  @ManyToOne
  @JoinColumn(name = "CUSTOMER_ID")
  private Customer customer;

  /**
   * Date of this booking
   */
  @Column(name = "DATE")
  private LocalDate date;

  /**
   * Set of all trips associated with this booking
   */
  @OneToMany
  @JoinColumn(name = "BOOKING_ID")
  private Set<Trip> tripSet;

  /**
   * Constructor creates a <code>Booking</code> object with initial attributes
   */
  public Booking() {

  }

  /**
   * Constructor creates a <code>Booking</code> object with specified attributes
   * @param customer customer paying for this booking
   * @param date date of this booking
   * @param tripSet set of all trips associated with this booking
   */
  public Booking(Customer customer, LocalDate date, Set<Trip> tripSet) {
    this.date = date;
    this.customer = customer;
    this.tripSet = tripSet;
  }

  /**
   * Getter-method for the <code>id</code> attribute.
   * @return unique identification number
   */
  public Integer getID() {
    return id;
  }

  /**
   * Getter-method for the <code>customer</code> attribute.
   * @return customer paying for this booking
   */
  public Customer getCustomer() {
    return customer;
  }

  /**
   * Setter-method for the <code>customer</code> attribute.
   * @param customer new customer paying for this booking
   */
  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  /**
   * Getter-method for the <code>date</code> attribute.
   * @return date of this booking
   */
  public LocalDate getDate() {
    return date;
  }

  /**
   * Setter-method for the <code>date</code> attribute.
   * @param date new date of this booking
   */
  public void setDate(LocalDate date) {
    this.date = date;
  }

  /**
   * Getter-method for the <code>tripSet</code> attribute.
   * @return set of all trips associated with this booking
   */
  public Set<Trip> getTripSet() {
    return tripSet;
  }

  /**
   * Setter-method for the <code>tripSet</code> attribute.
   * @param tripSet new set of all trips associated with this booking
   */
  public void setTripSet(Set<Trip> tripSet) {
    this.tripSet = tripSet;
  }

  /**
   * This method returns the total price of this single booking by
   * adding the total price of all trips associated with this booking.
   * @return total price of this booking
   */
  public double getTotalPrice() {
    return tripSet.stream().mapToDouble(Trip::getTotalPrice).sum();
  }

  @Override
  public boolean equals(Object obj) {
    if(obj != null && obj.getClass().equals(this.getClass())) {
      Booking booking = (Booking) obj;
      return
          ((id == null && booking.getID() == null) || id.equals(booking.getID())) &&
          ((customer == null && booking.getCustomer() == null)
              || customer.equals(booking.getCustomer())) &&
          ((tripSet == null && booking.getTripSet() == null)
              || tripSet.equals(booking.getTripSet()));
    }
    return false;
  }

  @Override
  public String toString() {
    return
        "Booking ID: " + id + '\n'
      + (date != null ? "Date: " + date + '\n' : "")
      + (customer != null ? "Customer:\n" + customer + '\n' : "" )
      + (tripSet != null  ? "Trips:\n" + tripSet : "" );
  }

  @Override
  public int hashCode() {
    return
        (String.valueOf(id != null  ? id.hashCode()       : null)
      + (customer != null           ? customer.hashCode() : null)
      + (tripSet != null            ? tripSet.hashCode()  : null)).hashCode();
  }

}
