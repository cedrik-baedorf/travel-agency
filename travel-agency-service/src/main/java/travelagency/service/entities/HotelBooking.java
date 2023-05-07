package travelagency.service.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * This class is a jpa entity to the corresponding table 'HOTEL_BOOKING'
 * in the database 'travel-agency-service_db'.
 * @author I551381
 * @version 1.0
 */
@Entity
@Table(name = "HOTEL_BOOKING")
public class HotelBooking {

  /**
   * Unique identifier for each hotel booking record (primary key in the database).
   * This value will be generated automatically.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "HOTEL_BOOKING_ID")
  private Integer id;

  /**
   * Hotel associated with this hotel booking
   */
  @ManyToOne
  @JoinColumn(name = "HOTEL_ID")
  private Hotel hotel;

  /**
   * Number of guests included in this hotel booking
   */
  @Column(name = "NUMBER_OF_GUESTS")
  private Integer numberOfGuests;

  /**
   * Number of nights each guest will stay at the hotel
   */
  @Column(name = "NUMBER_OF_NIGHTS")
  private Integer numberOfNights;

  /**
   * Constructor creates a <code>HotelBooking</code> object with initial attributes
   */
  public HotelBooking() {

  }

  /**
   * Constructor creates a <code>HotelBooking</code> object with specified attributes
   * @param hotel hotel associated with this hotel booking
   * @param numberOfGuests number of guests included in this hotel booking
   * @param numberOfNights number of nights each guest will stay at the hotel
   */
  public HotelBooking(Hotel hotel, Integer numberOfGuests, Integer numberOfNights) {
    this.hotel = hotel;
    this.numberOfGuests = numberOfGuests;
    this.numberOfNights = numberOfNights;
  }

  /**
   * Getter-method for the <code>id</code> attribute.
   * @return unique identification number
   */
  public Integer getID() {
    return id;
  }

  /**
   * Getter-method for the <code>hotel</code> attribute.
   * @return hotel associated with this hotel booking
   */
  public Hotel getHotel() {
    return hotel;
  }

  /**
   * Setter-method for the <code>hotel</code> attribute.
   * @param hotel new hotel associated with this hotel booking
   */
  public void setHotel(Hotel hotel) {
    this.hotel = hotel;
  }

  /**
   * Getter-method for the <code>numberOfGuests</code> attribute.
   * @return number of guests included in this hotel booking
   */
  public Integer getNumberOfGuests() {
    return numberOfGuests;
  }

  /**
   * Setter-method for the <code>numberOfGuests</code> attribute.
   * @param numberOfGuests new number of guests included in this hotel booking
   */
  public void setNumberOfGuests(Integer numberOfGuests) {
    this.numberOfGuests = numberOfGuests;
  }

  /**
   * Getter-method for the <code>numberOfNights</code> attribute.
   * @return number of nights each guest will stay at the hotel
   */
  public Integer getNumberOfNights() {
    return numberOfNights;
  }

  /**
   * Setter-method for the <code>numberOfNights</code> attribute.
   * @param numberOfNights new number of nights each guest will stay at the hotel
   */
  public void setNumberOfNights(Integer numberOfNights) {
    this.numberOfNights = numberOfNights;
  }

  /**
   * This method returns the total price of this single hotel booking based on the number of guests,
   * the number of nights, and the price per person of the hotel associated with this hotel booking.
   * @return total price of this hotel booking
   */
  public double getTotalPrice() {
    return numberOfGuests * numberOfNights * hotel.getPricePerPerson();
  }

  @Override
  public boolean equals(Object obj) {
    if(obj != null && obj.getClass().equals(getClass())) {
      HotelBooking booking = (HotelBooking) obj;
      return
          ((id == null && booking.getID() == null) || id.equals(booking.getID())) &&
          ((hotel == null && booking.getHotel() == null) || hotel.equals(booking.getHotel())) &&
          ((numberOfGuests == null && booking.getNumberOfGuests() == null)
              || numberOfGuests.equals(booking.getNumberOfGuests())) &&
          ((numberOfNights == null && booking.getNumberOfNights() == null)
              || numberOfNights.equals(booking.getNumberOfNights()));
    }
    return false;
  }

  @Override
  public String toString() {
    return
        (hotel != null          ? hotel.toString() + '\n' : "" )
      + (numberOfGuests != null ? "Guests: " + numberOfGuests + '\n' : "" )
      + (numberOfNights != null ? "Nights: " + numberOfNights : "" );
  }

  @Override
  public int hashCode() {
    return
        (String.valueOf(id != null    ? id.hashCode()             : null)
            + (hotel != null          ? hotel.hashCode()          : null)
            + (numberOfGuests != null ? numberOfGuests.hashCode() : null)
            + (numberOfNights != null ? numberOfNights.hashCode() : null)).hashCode();
  }

}
