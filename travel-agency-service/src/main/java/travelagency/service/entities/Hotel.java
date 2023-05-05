package travelagency.service.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

/**
 * This class is a jpa entity to the corresponding table 'HOTEL'
 * in the database 'travel-agency-service_db'.
 * @author I551381
 * @version 1.0
 */
@Entity
@Table(name = "HOTEL")
public class Hotel {

  /**
   * Unique identifier for each hotel record (primary key in the database).
   * This value will be generated automatically.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "HOTEL_ID")
  private Integer id;

  /**
   * hotel name
   */
  @Column(name = "NAME")
  private String name;

  /**
   * price per night per person
   */
  @Column(name = "PRICE_PER_PERSON")
  private Double pricePerPerson;

  /**
   * three character currency key to the <code>pricePerPerson</code>
   */
  @Column(name = "CURRENCY_KEY")
  private String currencyKey;

  /**
   * hotel address
   */
  @ManyToOne
  @JoinColumn(name = "ADDRESS_ID")
  private Address address;

  /**
   * Constructor creates a <code>Hotel</code> object with initial attributes
   */
  public Hotel() {

  }

  /**
   * Constructor creates a <code>Hotel</code> object with specified attributes
   * @param name hotel name
   * @param pricePerPerson price per night per person
   * @param currencyKey three character currency key to the <code>pricePerPerson</code>
   * @param address hotel address
   */
  public Hotel(String name, double pricePerPerson, String currencyKey, Address address) {
    this.name = name;
    this.pricePerPerson = pricePerPerson;
    this.currencyKey = currencyKey;
    this.address = address;
  }

  /**
   * Getter-method for the <code>id</code> attribute.
   * @return unique identification number
   */
  public Integer getId() {
    return id;
  }

  /**
   * Getter-method for the <code>name</code> attribute.
   * @return hotel name
   */
  public String getName() {
    return name;
  }

  /**
   * Setter-method for the <code>name</code> attribute.
   * @param name new hotel name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Getter-method for the <code>pricePerPerson</code> attribute.
   * @return price per night per person
   */
  public Double getPricePerPerson() {
    return pricePerPerson;
  }

  /**
   * Setter-method for the <code>pricePerPerson</code> attribute.
   * @param pricePerPerson new price per night per person
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
   * Getter-method for the <code>address</code> attribute.
   * @return hotel address
   */
  public Address getAddress() {
    return address;
  }

  /**
   * Setter-method for the <code>address</code> attribute.
   * @param address new hotel address
   */
  public void setAddress(Address address) {
    this.address = address;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj != null && obj.getClass().equals(this.getClass())) {
      Hotel hotel = (Hotel) obj;
      return
          ((id == null && hotel.getId() == null) || id.equals(hotel.getId())) &&
          ((name == null && hotel.getName() == null) || name.equals(hotel.getName())) &&
          ((pricePerPerson
              == null && hotel.getPricePerPerson() == null) || pricePerPerson.equals(hotel.getPricePerPerson())) &&
          ((currencyKey
              == null && hotel.getCurrencyKey() == null) || currencyKey.equals(hotel.getCurrencyKey())) &&
          ((address == null && hotel.getAddress() == null) || address.equals(hotel.getAddress()));
    }
    return false;
  }

  @Override
  public String toString() {
    return
        (name != null                             ? name + '\n'                                    : "" )
            + (address != null                    ? address.toString() + '\n'                                 : "" )
            + (pricePerPerson != null && currencyKey
            != null  ? "Price: " + pricePerPerson + " " + currencyKey : "" );
  }

  @Override
  public int hashCode() {
    return
        (String.valueOf(id != null  ? id.hashCode()       : null)
            + (name != null         ? name.hashCode()     : null)
            + (pricePerPerson != null        ? pricePerPerson.hashCode()    : null)
            + (currencyKey != null     ? currencyKey.hashCode() : null)
            + (address != null      ? address.hashCode()  : null)).hashCode();
  }

}
