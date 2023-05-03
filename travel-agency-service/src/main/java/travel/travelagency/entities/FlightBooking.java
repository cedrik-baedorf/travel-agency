package travel.travelagency.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * This class is a jpa entity to the corresponding table 'FLIGHT_BOOKING'
 * in the database 'travel-agency-service_db'.
 * @author I551381
 * @version 1.0
 */
@Entity
@Table(name = "flight_booking")
public class FlightBooking {

  /**
   * Unique identifier for each flight booking record (primary key in the database).
   * This value will be generated automatically.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "flight_booking_id")
  private Integer id;

  /**
   * Flight associated with this flight booking
   */
  @ManyToOne
  @JoinColumn(name = "flight_id")
  private Flight flight;

  /**
   * Number of passengers included in this flight booking
   */
  @Column(name = "number_of_passengers")
  private Integer numberOfPassengers;

  /**
   * Constructor creates a <code>FlightBooking</code> object with initial attributes
   */
  public FlightBooking() {

  }

  /**
   * Constructor creates a <code>FlightBooking</code> object with specified attributes
   * @param flight flight associated with this flight booking
   * @param numberOfPassengers number of passengers included in this flight booking
   */
  public FlightBooking(Flight flight, Integer numberOfPassengers) {
    this.flight = flight;
    this.numberOfPassengers = numberOfPassengers;
  }

  /**
   * Getter-method for the <code>id</code> attribute.
   * @return unique identification number
   */
  public Integer getID() {
    return id;
  }

  /**
   * Getter-method for the <code>flight</code> attribute.
   * @return flight associated with this flight booking
   */
  public Flight getFlight() {
    return flight;
  }

  /**
   * Setter-method for the <code>flight</code> attribute.
   * @param flight new flight associated with this flight booking
   */
  public void setFlight(Flight flight) {
    this.flight = flight;
  }

  /**
   * Getter-method for the <code>numberOfPassengers</code> attribute.
   * @return number of passengers included in this flight booking
   */
  public Integer getNumberOfPassengers() {
    return numberOfPassengers;
  }

  /**
   * Setter-method for the <code>numberOfPassengers</code> attribute.
   * @param numberOfPassengers new number of passengers included in this flight booking
   */
  public void setNumberOfPassengers(Integer numberOfPassengers) {
    this.numberOfPassengers = numberOfPassengers;
  }

  /**
   * This method returns the total price of this single flight booking based on the number of passengers
   * and the price per person of the flight associated with this flight booking.
   * @return total price of this flight booking
   */
  public double getTotalPrice() {
    return numberOfPassengers * flight.getPricePerPerson();
  }

  @Override
  public boolean equals(Object obj) {
    if(obj != null && obj.getClass().equals(this.getClass())) {
      FlightBooking booking = (FlightBooking) obj;
      return
          ((id == null && booking.getID() == null) || id.equals(booking.getID())) &&
          ((flight == null && booking.getFlight() == null) || flight.equals(booking.getFlight())) &&
          ((numberOfPassengers == null && booking.getNumberOfPassengers() == null)
              || numberOfPassengers.equals(booking.getNumberOfPassengers()));
    }
    return false;
  }

  @Override
  public String toString() {
    return
        (flight != null             ? flight.toString() + '\n'                       : "" )
      + (numberOfPassengers != null ? "Passengers: " + numberOfPassengers : "" );
  }

  @Override
  public int hashCode() {
    return
        (String.valueOf(id != null        ? id.hashCode()                 : null)
            + (flight != null             ? flight.hashCode()             : null)
            + (numberOfPassengers != null ? numberOfPassengers.hashCode() : null)).hashCode();
  }

}
