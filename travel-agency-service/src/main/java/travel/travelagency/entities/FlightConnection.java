package travel.travelagency.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

/**
 * This class is a jpa entity to the corresponding table 'FLIGHT_CONNECTION'
 * in the database 'travel-agency-service_db'.
 * @author I551381
 * @version 1.0
 */
@Entity
@Table(name = "flight_connection")
public class FlightConnection {

  /**
   * Unique identifier for each flight connection record (primary key in the database).
   * This value will be generated automatically.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "flight_connection_id")
  private Integer id;

  /**
   * two character airline code
   */
  @Column(name = "carrier_id")
  private String carrierID;

  /**
   * four character flight code
   */
  @Column(name = "connection_id")
  private String connectionID;

  /**
   * three character airport code of the airport of departure
   */
  @Column(name = "departure_airport_code")
  private String departureAirport;

  /**
   * three character airport code of the airport of arrival
   */
  @Column(name = "arrival_airport_code")
  private String arrivalAirport;

  /**
   * Constructor creates a <code>FlightConnection</code> object with initial attributes
   */
  public FlightConnection() {

  }

  /**
   * Constructor creates a <code>FlightConnection</code> object with specified attributes
   * @param carrierID two character airline code
   * @param connectionID four character flight code
   * @param departureAirport three character airport code of the airport of departure
   * @param arrivalAirport three character airport code of the airport of arrival
   */
  public FlightConnection(String carrierID, String connectionID, String departureAirport, String arrivalAirport) {
    this.carrierID = carrierID;
    this.connectionID = connectionID;
    this.departureAirport = departureAirport;
    this.arrivalAirport = arrivalAirport;
  }

  /**
   * Getter-method for the <code>id</code> attribute.
   * @return unique identification number
   */
  public Integer getId() {
    return id;
  }

  /**
   * Getter-method for the <code>carrierID</code> attribute.
   * @return two character airline code
   */
  public String getCarrierID() {
    return carrierID;
  }

  /**
   * Setter-method for the <code>carrierID</code> attribute.
   * @param carrierID new two character airline code
   */
  public void setCarrierID(String carrierID) {
    this.carrierID = carrierID;
  }

  /**
   * Getter-method for the <code>connectionID</code> attribute.
   * @return four character flight code
   */
  public String getConnectionID() {
    return connectionID;
  }

  /**
   * Setter-method for the <code>connectionID</code> attribute.
   * @param connectionID new four character flight code
   */
  public void setConnectionID(String connectionID) {
    this.connectionID = connectionID;
  }

  /**
   * Getter-method for the <code>departureAirport</code> attribute.
   * @return three character airport code of the airport of departure
   */
  public String getDepartureAirport() {
    return departureAirport;
  }

  /**
   * Setter-method for the <code>departureAirport</code> attribute.
   * @param departureAirport new three character airport code of the airport of departure
   */
  public void setDepartureAirport(String departureAirport) {
    this.departureAirport = departureAirport;
  }

  /**
   * Getter-method for the <code>arrivalAirport</code> attribute.
   * @return three character airport code of the airport of arrival
   */
  public String getArrivalAirport() {
    return arrivalAirport;
  }

  /**
   * Setter-method for the <code>arrivalAirport</code> attribute.
   * @param arrivalAirport new three character airport code of the airport of arrival
   */
  public void setArrivalAirport(String arrivalAirport) {
    this.arrivalAirport = arrivalAirport;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj != null && obj.getClass().equals(this.getClass())) {
      FlightConnection flightConnection = (FlightConnection) obj;
      return
          ((id == null && flightConnection.getId() == null)
              || id.equals(flightConnection.getId())) &&
          ((carrierID == null && flightConnection.getCarrierID() == null)
              || carrierID.equals(flightConnection.getCarrierID())) &&
          ((connectionID == null && flightConnection.getConnectionID() == null)
              || connectionID.equals(flightConnection.getConnectionID())) &&
          ((departureAirport == null && flightConnection.getDepartureAirport() == null)
              || departureAirport.equals(flightConnection.getDepartureAirport())) &&
          ((arrivalAirport == null && flightConnection.getArrivalAirport() == null)
              || arrivalAirport.equals(flightConnection.getArrivalAirport()));
    }
    return false;
  }

  @Override
  public String toString() {
    return
        (carrierID != null        ? carrierID : "_".repeat(2) )
      + (connectionID != null     ? connectionID : "_".repeat(4))
      + (departureAirport != null ? " from " + departureAirport : "")
      + (arrivalAirport != null   ? " to " + arrivalAirport : "");
  }

  @Override
  public int hashCode() {
    return
        (String.valueOf(id != null  ? id.hashCode()       : null)
            + (carrierID != null        ? carrierID.hashCode()        : null)
            + (connectionID != null     ? connectionID.hashCode()     : null)
            + (departureAirport != null ? departureAirport.hashCode() : null)
            + (arrivalAirport != null   ? arrivalAirport.hashCode()   : null)).hashCode();
  }

}
