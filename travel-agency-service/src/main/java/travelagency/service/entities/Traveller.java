package travelagency.service.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "TRAVELLER")
public class Traveller {

  @Id
  @Column(name = "PASSPORT_ID")
  private String passportID;

  @Column(name = "PLACE_OF_BIRTH")
  private String placeOfBirth;

  @ManyToOne
  @JoinColumn(name = "PERSONAL_DATA_ID")
  private PersonalData personalData;

  public Traveller() { }

  public Traveller(String passportID, String placeOfBirth, PersonalData personalData) {
    this.passportID = passportID;
    this.placeOfBirth = placeOfBirth;
    this.personalData = personalData;
  }

  public String getPassportID() {
    return passportID;
  }

  public void setPassportID(String passportID) {
    this.passportID = passportID;
  }

  public String getPlaceOfBirth() {
    return placeOfBirth;
  }

  public void setPlaceOfBirth(String placeOfBirth) {
    this.placeOfBirth = placeOfBirth;
  }

  public PersonalData getPersonalData() {
    return personalData;
  }

  public void setPersonalData(PersonalData personalData) {
    this.personalData = personalData;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj != null && obj.getClass().equals(getClass())) {
      Traveller traveller = (Traveller) obj;
      return
          ((passportID == null && traveller.getPassportID() == null)
              || passportID.equals(traveller.getPassportID())) &&
          ((placeOfBirth == null && traveller.getPlaceOfBirth() == null)
              || placeOfBirth.equals(traveller.getPlaceOfBirth())) &&
          ((personalData == null && traveller.getPersonalData() == null)
              || personalData.equals(traveller.getPersonalData()));
    }
    return false;
  }

  @Override
  public String toString() {
    return
        (personalData != null ? personalData.toString() + '\n' : "" )
            + (passportID != null   ? "Passport Number: " + passportID + '\n'  : "" )
            + (placeOfBirth != null ? "Place of Birth : " + placeOfBirth : "" );
  }

  @Override
  public int hashCode() {
    return
        (String.valueOf(passportID != null ? passportID.hashCode() : null)
            + (placeOfBirth != null ? placeOfBirth.hashCode() : null)
            + (personalData != null ? personalData.hashCode() : null)).hashCode();
  }

}