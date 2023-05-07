package travelagency.service.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

/**
 * This class is a jpa entity to the corresponding table 'ADDRESS'
 * in the database 'travel-agency-service_db'.
 * @author I551381
 * @version 1.0
 */
@Entity
@Table(name = "ADDRESS")
public class Address {

  /**
   * Unique identifier for each address record (primary key in the database).
   * This value will be generated automatically.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ADDRESS_ID")
  private Integer id;

  /**
   * street name
   */
  @Column(name = "STREET")
  private String street;

  /**
   * house number
   */
  @Column(name = "NUMBER")
  private String number;

  /**
   * zip code
   */
  @Column(name = "ZIP")
  private String zipCode;

  /**
   * town name
   */
  @Column(name = "TOWN")
  private String town;

  /**
   * country of residence
   */
  @Column(name = "COUNTRY")
  private String country;

  /**
   * Constructor creates an <code>Address</code> object with initial attributes
   */
  public Address() { }

  /**
   * Constructor creates an <code>Address</code> object with specified attributes
   * @param street street name
   * @param number house number
   * @param zipCode zip code
   * @param town town name
   * @param country country of residence
   */
  public Address(String street, String number, String zipCode, String town, String country) {
    this.street = street;
    this.number = number;
    this.zipCode = zipCode;
    this.town = town;
    this.country = country;
  }

  /**
   * Getter-method for the <code>id</code> attribute.
   * @return unique identification number
   */
  public Integer getId() {
    return id;
  }

  /**
   * Getter-method for the <code>street</code> attribute.
   * @return street name
   */
  public String getStreet() {
    return street;
  }

  /**
   * Setter-method for the <code>street</code> attribute.
   * @param street new street name
   */
  public void setStreet(String street) {
    this.street = street;
  }

  /**
   * Getter-method for the <code>number</code> attribute.
   * @return house number
   */
  public String getNumber() {
    return number;
  }

  /**
   * Setter-method for the <code>number</code> attribute.
   * @param number new house number
   */
  public void setNumber(String number) {
    this.number = number;
  }

  /**
   * Getter-method for the <code>zipCode</code> attribute.
   * @return zip code
   */
  public String getZipCode() {
    return zipCode;
  }

  /**
   * Setter-method for the <code>zipCode</code> attribute.
   * @param zipCode new zip code
   */
  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  /**
   * Getter-method for the <code>town</code> attribute.
   * @return town name
   */
  public String getTown() {
    return town;
  }

  /**
   * Setter-method for the <code>town</code> attribute.
   * @param town new town name
   */
  public void setTown(String town) {
    this.town = town;
  }

  /**
   * Getter-method for the <code>country</code> attribute.
   * @return country of residence
   */
  public String getCountry() {
    return country;
  }

  /**
   * Setter-method for the <code>country</code> attribute.
   * @param country new country of residence
   */
  public void setCountry(String country) {
    this.country = country;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj != null && obj.getClass().equals(getClass())) {
      Address address = (Address) obj;
      return
        ((id == null && address.getId() == null) || id.equals(address.getId())) &&
        ((street == null && address.getStreet() == null) || street.equals(address.getStreet())) &&
        ((number == null && address.getNumber() == null) || number.equals(address.getNumber())) &&
        ((zipCode == null && address.getZipCode() == null) || zipCode.equals(address.getZipCode())) &&
        ((town == null && address.getTown() == null) || town.equals(address.getTown())) &&
        ((country == null && address.getCountry() == null) || country.equals(address.getCountry()));
    }
    return false;
  }

  @Override
  public String toString() {
    return
        street +
        " " +
            number +
        ", " +
        zipCode +
        " " +
            town +
        ", " +
        country;
  }

  @Override
  public int hashCode() {
    String appendedHashCodes = String.valueOf(id != null ? id.hashCode() : null)
        + (street != null ? street.hashCode() : null)
        + (number != null ? number.hashCode() : null)
        + (zipCode != null ? zipCode.hashCode() : null)
        + (town != null ? town.hashCode() : null)
        + (country != null ? country.hashCode() : null);
    return appendedHashCodes.hashCode();
  }

}
