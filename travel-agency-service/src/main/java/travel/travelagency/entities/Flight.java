package travel.travelagency.entities;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is a jpa entity to the corresponding table 'FLIGHT'
 * in the database 'travel-agency-service_db'.
 * @author I551381
 * @version 1.0
 */
@Entity
@Table(name = "flight")
public class Flight {

  static final Logger logger = LogManager.getLogger(Flight.class);

  private static final String
    MSG_NULL_TIMESTAMP = "date, time, and time zone have not all been initialized yet";

  /**
   * Unique identifier for each flight record (primary key in the database).
   * This value will be generated automatically.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "flight_id")
  private Integer id;

  /**
   * flight connection this flight services
   */
  @ManyToOne
  @JoinColumn(name = "flight_connection_id")
  private FlightConnection flightConnection;

  /**
   * local date of departure
   */
  @Column(name = "departure_date")
  private LocalDate dateOfDeparture;

  /**
   * local time of departure
   */
  @Column(name = "departure_time")
  private LocalTime timeOfDeparture;

  /**
   * time zone of the airport of departure
   */
  @Column(name = "departure_time_zone")
  private String timeZoneOfDeparture;

  /**
   * local date of arrival
   */
  @Column(name = "arrival_date")
  private LocalDate dateOfArrival;

  /**
   * local time of arrival
   */
  @Column(name = "arrival_time")
  private LocalTime timeOfArrival;

  /**
   * time zone of the airport of arrival
   */
  @Column(name = "arrival_time_zone")
  private String timeZoneOfArrival;

  /**
   * price per person
   */
  @Column(name = "price_per_person")
  private Double pricePerPerson;

  /**
   * three character currency key to the <code>pricePerPerson</code>
   */
  @Column(name = "currency_key")
  private String currencyKey;

  /**
   * Constructor creates a <code>Flight</code> object with initial attributes
   */
  public Flight() {

  }

  /**
   * Constructor creates a <code>Flight</code> object with specified attributes
   * @param flightConnection flight connection this flight services
   * @param dateOfDeparture local data of departure
   * @param timeOfDeparture local time of departure
   * @param timeZoneOfDeparture time zone of the airport of departure
   * @param dateOfArrival local date of arrival
   * @param timeOfArrival local time of arrival
   * @param timeZoneOfArrival time zone of the airport of arrival
   * @param pricePerPerson price per person
   * @param currencyKey three character currency key to the <code>pricePerPerson</code>
   */
  public Flight(FlightConnection flightConnection, LocalDate dateOfDeparture,
      LocalTime timeOfDeparture, String timeZoneOfDeparture, LocalDate dateOfArrival,
      LocalTime timeOfArrival, String timeZoneOfArrival, Double pricePerPerson,
      String currencyKey) {
    this.flightConnection = flightConnection;
    this.dateOfDeparture = dateOfDeparture;
    this.timeOfDeparture = timeOfDeparture;
    this.timeZoneOfDeparture = timeZoneOfDeparture;
    this.dateOfArrival = dateOfArrival;
    this.timeOfArrival = timeOfArrival;
    this.timeZoneOfArrival = timeZoneOfArrival;
    this.pricePerPerson = pricePerPerson;
    this.currencyKey = currencyKey;
  }

  /**
   * Getter-method for the <code>id</code> attribute.
   * @return unique identification number
   */
  public Integer getId() {
    return id;
  }

  /**
   * Getter-method for the <code>flightConnection</code> attribute.
   * @return flight connection this flight services
   */
  public FlightConnection getFlightConnection() {
    return flightConnection;
  }

  /**
   * Setter-method for the <code>flightConnection</code> attribute.
   * @param flightConnection new flight connection this flight services
   */
  public void setFlightConnection(FlightConnection flightConnection) {
    this.flightConnection = flightConnection;
  }

  /**
   * Getter-method for the <code>pricePerPerson</code> attribute.
   * @return price per person
   */
  public Double getPricePerPerson() {
    return pricePerPerson;
  }

  /**
   * Setter-method for the <code>pricePerPerson</code> attribute.
   * @param pricePerPerson new price per person
   */
  public void setPricePerPerson(Double pricePerPerson) {
    this.pricePerPerson = pricePerPerson;
  }

  /**
   * Getter-method for the <code>currencyKey</code> attribute.
   * @return three character currency key to the <code>pricePerPerson</code>
   */
  public String getCurrencyKey() {
    return currencyKey;
  }

  /**
   * Setter-method for the <code>currencyKey</code> attribute.
   * @param currencyKey new three character currency key to the <code>pricePerPerson</code>
   */
  public void setCurrencyKey(String currencyKey) {
    this.currencyKey = currencyKey;
  }

  /**
   * This method returns a <code>ZonedDateTime</code> object representing the timestamp of this
   * flight's departure. The timestamp is created using the <code>dateOfDeparture</code>,
   * <code>timeOfDeparture</code>, and <code>timeZoneOfDeparture</code> attributes.
   * @return timestamp of departure
   */
  public ZonedDateTime getDepartureTimestamp() {
    try {
      return ZonedDateTime.of(dateOfDeparture, timeOfDeparture, ZoneId.of(timeZoneOfDeparture));
    } catch (NullPointerException e) {
      logger.error(MSG_NULL_TIMESTAMP + " for method getDepartureTimestamp()");
      return null;
    }
  }

  /**
   * This method sets the values for the <code>dateOfDeparture</code>,
   * <code>timeOfDeparture</code>, and <code>timeZoneOfDeparture</code> attributes
   * from the <code>ZonedDateTime</code> object provided
   * @param departureTimestamp timestamp of departure
   */
  public void setDepartureTimestamp(ZonedDateTime departureTimestamp) {
    this.dateOfDeparture = departureTimestamp.toLocalDate();
    this.timeOfDeparture = departureTimestamp.toLocalTime();
    this.timeZoneOfDeparture = departureTimestamp.getZone().toString();
  }

  /**
   * This method returns a <code>ZonedDateTime</code> object representing the timestamp of this
   * flight's arrival. The timestamp is created using the <code>dateOfArrival</code>,
   * <code>timeOfArrival</code>, and <code>timeZoneOfArrival</code> attributes.
   * @return timestamp of arrival
   */
  public ZonedDateTime getArrivalTimestamp() {
    try {
      return ZonedDateTime.of(dateOfArrival, timeOfArrival, ZoneId.of(timeZoneOfArrival));
    } catch (NullPointerException e) {
      logger.error(MSG_NULL_TIMESTAMP + " for method getArrivalTimestamp()");
      return null;
    }
  }

  /**
   * This method sets the values for the <code>dateOfArrival</code>,
   * <code>timeOfArrival</code>, and <code>timeZoneOfArrival</code> attributes
   * from the <code>ZonedDateTime</code> object provided.
   * @param arrivalTimestamp timestamp of arrival
   */
  public void setArrivalTimestamp(ZonedDateTime arrivalTimestamp) {
    this.dateOfArrival = arrivalTimestamp.toLocalDate();
    this.timeOfArrival = arrivalTimestamp.toLocalTime();
    this.timeZoneOfArrival = arrivalTimestamp.getZone().toString();
  }

  /**
   * This method returns a <code>Duration</code> object representing the total length of this flight.
   * @return length of this flight
   */
  public Duration getFlightDuration() {
    ZonedDateTime
        departureTimestamp  = this.getDepartureTimestamp(),
        arrivalTimestamp    = this.getArrivalTimestamp();
    if(departureTimestamp != null && arrivalTimestamp != null)
      return Duration.ofMinutes(ChronoUnit.MINUTES.between(
          this.getArrivalTimestamp(),
          this.getDepartureTimestamp()
      )).abs();
    logger.error(MSG_NULL_TIMESTAMP + " for method getFlightDurationTimestamp()");
    return null;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj != null && obj.getClass().equals(this.getClass())) {
      Flight flight = (Flight) obj;
      ZonedDateTime
          departureTimestamp  = this.getDepartureTimestamp(),
          arrivalTimestamp    = this.getArrivalTimestamp();
      return
          ((id == null && flight.getId() == null)
              || id.equals(flight.getId())) &&
          ((flightConnection == null && flight.getFlightConnection() == null)
              || flightConnection.equals(flight.getFlightConnection())) &&
          ((departureTimestamp == null && flight.getDepartureTimestamp() == null)
              || departureTimestamp.equals(flight.getDepartureTimestamp())) &&
          ((arrivalTimestamp == null && flight.getArrivalTimestamp() == null)
              || arrivalTimestamp.equals(flight.getArrivalTimestamp())) &&
          ((pricePerPerson == null && flight.getPricePerPerson() == null)
              || pricePerPerson.equals(flight.getPricePerPerson()))&&
          ((currencyKey == null && flight.getCurrencyKey() == null)
              || currencyKey.equals(flight.getCurrencyKey()));
    }
    return false;
  }

  @Override
  public String toString() {
    ZonedDateTime
        departureTimestamp  = this.getDepartureTimestamp(),
        arrivalTimestamp    = this.getArrivalTimestamp();
    return
        (flightConnection != null ? flightConnection.toString() + '\n' : "")
            + (departureTimestamp != null ? "Departure: " + departureTimestamp + '\n' : "")
            + (arrivalTimestamp != null   ? "Arrival  : " + arrivalTimestamp + '\n' : "")
            + (pricePerPerson != null && currencyKey != null ?
                  "Price    : " + pricePerPerson + " " + currencyKey : "");
  }

  @Override
  public int hashCode() {
    ZonedDateTime
        departureTimestamp  = this.getDepartureTimestamp(),
        arrivalTimestamp    = this.getArrivalTimestamp();
    return
        (String.valueOf(id != null        ? id.hashCode()                 : null)
            + (flightConnection != null   ? flightConnection.hashCode()   : null)
            + (departureTimestamp != null ? departureTimestamp.hashCode() : null)
            + (arrivalTimestamp != null   ? arrivalTimestamp.hashCode()   : null)
            + (pricePerPerson != null     ? pricePerPerson.hashCode()     : null)
            + (currencyKey != null        ? currencyKey.hashCode()        : null)).hashCode();
  }

}
