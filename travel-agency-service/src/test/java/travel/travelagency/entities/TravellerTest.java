package travel.travelagency.entities;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TravellerTest {

  private static Traveller nullTraveller, emptyTraveller, traveller, copyTraveller, differentTraveller;

  @BeforeAll
  public static void initializeTravellers() {
    nullTraveller = null;
    emptyTraveller = new Traveller();
    traveller = new Traveller(
        "C12345678",
        "Berlin",
        new PersonalData(
            "Merkel",
            "Angela",
            "Dorothea",
            LocalDate.of(1954, 7, 17),
            new Address("Street", "18", "93726", "Town", "Country")
        )
    );
    copyTraveller = new Traveller(
        "C12345678",
        "Berlin",
        new PersonalData(
            "Merkel",
            "Angela",
            "Dorothea",
            LocalDate.of(1954, 7, 17),
            new Address("Street", "18", "93726", "Town", "Country")
        )
    );
    differentTraveller = new Traveller(
        "D01928345",
        "Hamburg",
        new PersonalData(
            "Scholz",
            "Olaf",
            "",
            LocalDate.of(1987, 11, 17),
            new Address("Way", "9", "65958", "Stadt", "Osmanien")
        )
    );
  }

  @Test()
  public void testEqualsMethodWithNull() {
    assertDoesNotThrow(() -> emptyTraveller.equals(nullTraveller));
    assertFalse(emptyTraveller.equals(nullTraveller));
  }

  @Test
  public void testEqualsMethodWithEmptyTraveller() {
    Traveller copyEmptyTraveller = new Traveller();
    assertDoesNotThrow(() -> emptyTraveller.equals(copyEmptyTraveller));
    assertTrue(emptyTraveller.equals(copyEmptyTraveller));
  }

  @Test
  public void testEqualsMethodWithIdenticalTraveller() {
    assertDoesNotThrow(() -> traveller.equals(copyTraveller));
    assertTrue(traveller.equals(copyTraveller));
  }

  @Test
  public void testEqualsMethodWithDifferentTraveller() {
    assertDoesNotThrow(() -> traveller.equals(differentTraveller));
    assertFalse(traveller.equals(differentTraveller));
  }

  @Test
  public void testEqualsMethodWithDifferentClass() {
    Object obj = new Object();
    assertDoesNotThrow(() -> traveller.equals(obj));
    assertFalse(traveller.equals(obj));
  }

  @Test
  public void testToStringMethod() {
    Traveller peterLustig = new Traveller(
        "C98765432",
        "Bärstadt",
        new PersonalData(
            "Lustig",
            "Peter",
            "Fritz Willi",
            LocalDate.of(1937, 10, 20),
            new Address("Street", "101a", "19824", "My Town", "Disneyland")
        )
    );

    assertEquals("""
        Peter Fritz Willi Lustig, 1937-10-20
        Street 101a, 19824 My Town, Disneyland
        Passport Number: C98765432
        Place of Birth : Bärstadt""",
        peterLustig.toString()
    );
  }

  @Test
  public void testHashCodeMethodWithIdenticalTraveller() {
    int expectedHashCode  = traveller.hashCode();
    int actualHashCode    = copyTraveller.hashCode();

    assertEquals(expectedHashCode, actualHashCode);
  }

  @Test
  public void testHashCodeMethodWithDifferentTraveller() {
    int unexpectedHashCode  = traveller.hashCode();
    int actualHashCode      = differentTraveller.hashCode();

    assertNotEquals(unexpectedHashCode, actualHashCode);
  }

}
