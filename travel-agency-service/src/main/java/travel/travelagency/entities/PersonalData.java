package travel.travelagency.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;

/**
 * This class is a jpa entity to the corresponding table 'PERSONAL_DATA'
 * in the database 'travel-agency-service_db'.
 * @author I551381
 * @version 1.0
 */
@Entity
@Table(name = "PERSONAL_DATA")
public class PersonalData {

  /**
   * Unique identifier for each personal data record (primary key in the database).
   * This value will be generated automatically.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "PERSONAL_DATA_ID")
  private Integer id;

  /**
   * last name(s)
   */
  @Column(name = "LAST_NAME")
  private String lastName;

  /**
   * first name(s)
   */
  @Column(name = "FIRST_NAME")
  private String firstName;

  /**
   * middle name(s)
   */
  @Column(name = "MIDDLE_NAME")
  private String middleName;

  /**
   * date of birth
   */
  @Column(name = "DATE_OF_BIRTH")
  private LocalDate dateOfBirth;

  /**
   * home address
   */
  @ManyToOne
  @JoinColumn(name = "ADDRESS_ID")
  private Address address;

  /**
   * Constructor creates a <code>PersonalData</code> object with initial attributes
   */
  public PersonalData() { }

  /**
   * Constructor creates a <code>PersonalData</code> object with specified attributes
   * @param lastName last name(s)
   * @param firstName first name(s)
   * @param middleName middle name(s)
   * @param dateOfBirth date of birth
   * @param address home address
   */
  public PersonalData(String lastName, String firstName, String middleName, LocalDate dateOfBirth, Address address) {
    this.lastName = lastName;
    this.firstName = firstName;
    this.middleName = middleName;
    this.dateOfBirth = dateOfBirth;
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
   * Getter-method for the <code>lastName</code> attribute.
   * @return last name(s)
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Setter-method for the <code>lastName</code> attribute.
   * @param lastName new last name(s)
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Getter-method for the <code>firstName</code> attribute.
   * @return first name(s)
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Setter-method for the <code>firstName</code> attribute.
   * @param firstName new first name(s)
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Getter-method for the <code>middleNames</code> attribute.
   * @return middle name(s)
   */
  public String getMiddleName() {
    return middleName;
  }

  /**
   * Setter-method for the <code>middleNames</code> attribute.
   * @param middleName new middle name(s)
   */
  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  /**
   * Getter-method for the <code>dateOfBirth</code> attribute.
   * @return date of birth
   */
  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  /**
   * Setter-method for the <code>dateOfBirth</code> attribute.
   * @param dateOfBirth new date of birth
   */
  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  /**
   * Getter-method for the <code>address</code> attribute.
   * @return home address
   */
  public Address getAddress() {
    return address;
  }

  /**
   * Setter-method for the <code>address</code> attribute.
   * @param address new home address
   */
  public void setAddress(Address address) {
    this.address = address;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj != null && obj.getClass().equals(this.getClass())) {
      PersonalData data = (PersonalData) obj;
      return
          ((id == null && data.getId() == null) || id.equals(data.getId())) &&
          ((lastName == null && data.getLastName() == null) || lastName.equals(data.getLastName())) &&
          ((firstName == null && data.getFirstName() == null) || firstName.equals(data.getFirstName())) &&
          ((middleName == null && data.getMiddleName() == null) || middleName.equals(data.getMiddleName())) &&
          ((dateOfBirth == null && data.getDateOfBirth() == null) || dateOfBirth.equals(data.getDateOfBirth())) &&
          ((address == null && data.getAddress() == null) || address.equals(data.getAddress()));
    }
    return false;
  }

  @Override
  public String toString() {
    return
      firstName +
      " " +
              middleName +
      " " +
      lastName +
      (dateOfBirth != null ? ", " + dateOfBirth : "") +
      (address     != null ? "\n" + address : "");
  }

  @Override
  public int hashCode() {
    String appendedHashCodes = String.valueOf(id != null ? id.hashCode() : null)
        + (lastName != null ? lastName.hashCode() : null)
        + (firstName != null ? firstName.hashCode() : null)
        + (middleName != null ? middleName.hashCode() : null)
        + (dateOfBirth != null ? dateOfBirth.hashCode() : null)
        + (address != null ? address.hashCode() : null);
    return appendedHashCodes.hashCode();
  }
}
