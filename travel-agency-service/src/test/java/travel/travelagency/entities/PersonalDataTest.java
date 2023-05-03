package travel.travelagency.entities;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class PersonalDataTest {

  private static PersonalData nullPersonalData, emptyPersonalData, personalData, copyPersonalData, differentPersonalData;

  @BeforeAll
  public static void initializePersonalData() {
    nullPersonalData = null;
    emptyPersonalData = new PersonalData();
    personalData = new PersonalData(
        "Merkel",
        "Angela",
        "Dorothea",
        LocalDate.of(1954, 7, 17),
        new Address("Street", "18", "93726", "Town", "Country")
    );
    copyPersonalData = new PersonalData(
        "Merkel",
        "Angela",
        "Dorothea",
        LocalDate.of(1954, 7, 17),
        new Address("Street", "18", "93726", "Town", "Country")
    );
    differentPersonalData = new PersonalData(
        "Scholz",
        "Olaf",
        "",
        LocalDate.of(1987, 11, 17),
        new Address("Way", "9", "65958", "Stadt", "Osmanien")
    );
  }

  @Test()
  public void testEqualsMethodWithNull() {
    assertDoesNotThrow(() -> emptyPersonalData.equals(nullPersonalData));
    assertFalse(emptyPersonalData.equals(nullPersonalData));
  }

  @Test
  public void testEqualsMethodWithEmptyPersonalData() {
    PersonalData copyEmptyPersonalData = new PersonalData();
    assertDoesNotThrow(() -> emptyPersonalData.equals(copyEmptyPersonalData));
    assertTrue(emptyPersonalData.equals(copyEmptyPersonalData));
  }

  @Test
  public void testEqualsMethodWithIdenticalPersonalData() {
    assertDoesNotThrow(() -> personalData.equals(copyPersonalData));
    assertTrue(personalData.equals(copyPersonalData));
  }

  @Test
  public void testEqualsMethodWithDifferentPersonalData() {
    assertDoesNotThrow(() -> personalData.equals(differentPersonalData));
    assertFalse(personalData.equals(differentPersonalData));
  }

  @Test
  public void testEqualsMethodWithDifferentClass() {
    Object obj = new Object();
    assertDoesNotThrow(() -> personalData.equals(obj));
    assertFalse(personalData.equals(obj));
  }

  @Test
  public void testToStringMethod() {
    PersonalData personalData = new PersonalData(
      "Lustig",
      "Peter",
      "Fritz Willi",
      LocalDate.of(1937, 10, 20),
      new Address(
        "Street",
        "101a",
        "19824",
        "My Town",
        "Disneyland")
    );

    assertEquals("""
        Peter Fritz Willi Lustig, 1937-10-20
        Street 101a, 19824 My Town, Disneyland""",
        personalData.toString()
    );
  }

  @Test
  public void testHashCodeMethodWithIdenticalPersonalData() {
    int expectedHashCode = personalData.hashCode();
    int actualHashCode = copyPersonalData.hashCode();

    assertEquals(expectedHashCode, actualHashCode);
  }

  @Test
  public void testHashCodeMethodWithDifferentPersonalData() {
    int unexpectedHashCode = personalData.hashCode();
    int actualHashCode = differentPersonalData.hashCode();

    assertNotEquals(unexpectedHashCode, actualHashCode);
  }

}
