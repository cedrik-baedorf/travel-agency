package travel.travelagency.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;

/**
 * This class is a jpa entity to the corresponding table 'CUSTOMER'
 * in the database 'travel-agency-service_db'.
 * @author I551381
 * @version 1.0
 */
@Entity
@Table(name = "CUSTOMER")
public class Customer {

  /**
   * Unique identifier for each personal customer record (primary key in the database).
   * This value will be generated automatically.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CUSTOMER_ID")
  private Integer id;

  /**
   * IBAN of the customer's bank account
   */
  @Column(name = "IBAN")
  private String iban;

  /**
   * personal data record of the customer
   */
  @ManyToOne
  @JoinColumn(name = "PERSONAL_DATA_ID")
  private PersonalData personalData;

  /**
   * billing address (may be identical with the home address)
   */
  @ManyToOne
  @JoinColumn(name = "BILLING_ADDRESS_ID")
  private Address billingAddress;

  /**
   * Constructor creates a <code>Customer</code> object with initial attributes
   */
  public Customer() { }

  /**
   * Constructor creates a <code>Customer</code> object with specified attributes
   * @param iban IBAN of the customer's bank account
   * @param personalData personal data record of the customer
   * @param billingAddress billing address (may be identical with the home address)
   */
  public Customer(String iban, PersonalData personalData, Address billingAddress) {
    this.iban = iban;
    this.personalData = personalData;
    this.billingAddress = billingAddress;
  }

  /**
   * Getter-method for the <code>id</code> attribute.
   * @return unique identification number
   */
  public Integer getID() {
    return id;
  }

  /**
   * Getter-method for the <code>iban</code> attribute.
   * @return IBAN of the customer's bank account
   */
  public String getIban() {
    return iban;
  }

  /**
   * Setter-method for the <code>iban</code> attribute.
   * @param iban IBAN of the customer's bank account
   */
  public void setIban(String iban) {
    this.iban = iban;
  }

  /**
   * Getter-method for the <code>personalData</code> attribute.
   * @return personal data record of the customer
   */
  public PersonalData getPersonalData() {
    return personalData;
  }

  /**
   * Setter-method for the <code>personalData</code> attribute.
   * @param personalData personal data record of the customer
   */
  public void setPersonalData(PersonalData personalData) {
    this.personalData = personalData;
  }

  /**
   * Getter-method for the <code>billingAddress</code> attribute.
   * @return billing address (may be identical with the home address)
   */
  public Address getBillingAddress() {
    return billingAddress;
  }

  /**
   * Setter-method for the <code>billingAddress</code> attribute.
   * @param billingAddress billing address (may be identical with the home address)
   */
  public void setBillingAddress(Address billingAddress) {
    this.billingAddress = billingAddress;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj != null && obj.getClass().equals(this.getClass())) {
      Customer customer = (Customer) obj;
      return
          ((id == null && customer.getID() == null) || id.equals(customer.getID())) &&
          ((iban == null && customer.getIban() == null) || iban.equals(customer.getIban())) &&
          ((personalData == null && customer.getPersonalData() == null)
              || personalData.equals(customer.getPersonalData())) &&
          ((billingAddress == null && customer.getBillingAddress() == null)
              || billingAddress.equals(customer.getBillingAddress()));
    }
    return false;
  }

  @Override
  public String toString() {
    return
        (personalData != null ? personalData.toString() + '\n' : "" )
        + (iban != null ? iban + '\n' : "" )
        + (id != null ? "Customer Number: " + id + '\n' : "" )
        + (billingAddress != null ? "Billing Address: " + billingAddress : "" );
  }

  @Override
  public int hashCode() {
    return
        (String.valueOf(id != null ? id.hashCode() : null)
        + (iban != null ? iban.hashCode() : null)
        + (personalData != null ? personalData.hashCode() : null)
        + (billingAddress != null ? billingAddress.hashCode() : null)).hashCode();
  }

}
