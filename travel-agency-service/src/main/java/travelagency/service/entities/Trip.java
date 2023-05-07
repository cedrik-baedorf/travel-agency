package travelagency.service.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * This class is a jpa entity to the corresponding table 'TRIP'
 * in the database 'travel-agency-service_db'.
 * @author I551381
 * @version 1.0
 */
@Entity
@Table(name = "TRIP")
public class Trip {

  /**
   * Unique identifier for each trip record (primary key in the database).
   * This value will be generated automatically.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TRIP_ID")
  private Integer id;

  /**
   * Set of all hotel bookings associated with this trip
   */
  @OneToMany
  @JoinColumn(name = "TRIP_ID")
  private Set<HotelBooking> hotelBookingSet;

  /**
   * Set of all flight bookings associated with this trip
   */
  @OneToMany
  @JoinColumn(name = "TRIP_ID")
  private Set<FlightBooking> flightBookingSet;

  /**
   * Constructor creates a <code>Trip</code> object with initial attributes
   */
  public Trip() {

  }

  /**
   * Constructor creates a <code>Trip</code> object with specified attributes
   * @param hotelBookingSet Set of all hotel bookings associated with this trip
   * @param flightBookingSet Set of all flight bookings associated with this trip
   */
  public Trip(Set<HotelBooking> hotelBookingSet, Set<FlightBooking> flightBookingSet) {
    this.hotelBookingSet = hotelBookingSet;
    this.flightBookingSet = flightBookingSet;
  }

  /**
   * Getter-method for the <code>id</code> attribute.
   * @return unique identification number
   */
  public Integer getID() {
    return id;
  }

  /**
   * Getter-method for the <code>hotelBookingSet</code> attribute.
   * @return set of all hotel bookings associated with this trip
   */
  public Set<HotelBooking> getHotelBookingSet() {
    return hotelBookingSet;
  }

  /**
   * Setter-method for the <code>hotelBookingSet</code> attribute.
   * @param hotelBookingSet new set of all hotel bookings associated with this trip
   */
  public void setHotelBookingSet(
      Set<HotelBooking> hotelBookingSet) {
    this.hotelBookingSet = hotelBookingSet;
  }

  /**
   * Getter-method for the <code>flightBookingList</code> attribute.
   * @return set of all flight bookings associated with this trip
   */
  public Set<FlightBooking> getFlightBookingSet() {
    return flightBookingSet;
  }

  /**
   * Setter-method for the <code>flightBookingSet</code> attribute.
   * @param flightBookingSet new set of all flight bookings associated with this trip
   */
  public void setFlightBookingSet(
      Set<FlightBooking> flightBookingSet) {
    this.flightBookingSet = flightBookingSet;
  }

  /**
   * This method returns the total price of this single trip by adding the total price of
   * all flight bookings and hotel bookings associated with this trip.
   * @return total price of this trip
   */
  public double getTotalPrice() {
    return getTotalHotelPrice() + getTotalFlightPrice();
  }

  /**
   * This method returns the total price of all hotel bookings associated with this trip
   * @return total price of all hotel bookings associated with this trip
   */
  public double getTotalHotelPrice() {
    return hotelBookingSet.stream().mapToDouble(HotelBooking::getTotalPrice).sum();
  }

  /**
   * This method returns the total price of all flight bookings associated with this trip
   * @return total price of all flight bookings associated with this trip
   */
  public double getTotalFlightPrice() {
    return flightBookingSet.stream().mapToDouble(FlightBooking::getTotalPrice).sum();
  }

  @Override
  public boolean equals(Object obj) {
    if(obj != null && obj.getClass().equals(getClass())) {
      Trip trip = (Trip) obj;
      return
          ((id == null && trip.getID() == null) || id.equals(trip.getID())) &&
          ((hotelBookingSet == null && trip.getHotelBookingSet() == null)
              || hotelBookingSet.equals(trip.getHotelBookingSet())) &&
          ((flightBookingSet == null && trip.getFlightBookingSet() == null)
              || flightBookingSet.equals(trip.getFlightBookingSet()));
    }
    return false;
  }

  @Override
  public String toString() {
    return
        (id != null ? "Trip no. : " + id + '\n' : "")
      + (hotelBookingSet != null  ? "Hotel Bookings:\n" + hotelBookingSet + '\n' : "" )
      + (flightBookingSet != null ? "Flight Bookings:\n" + flightBookingSet : "" );
  }

  @Override
  public int hashCode() {
    return
        (String.valueOf(id != null      ? id.hashCode()               : null)
            + (hotelBookingSet != null  ? hotelBookingSet.hashCode()  : null)
            + (flightBookingSet != null ? flightBookingSet.hashCode() : null)).hashCode();
  }

}
