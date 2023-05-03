package travel.travelagency.entities;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AddressTest {

  private static Address nullAddress, emptyAddress, address, copyAddress, differentAddress;

  @BeforeAll
  public static void initializeAddresses() {
    nullAddress = null;
    emptyAddress = new Address();
    address = new Address("Street", "18", "93726", "Town", "Country");
    copyAddress = new Address("Street", "18", "93726", "Town", "Country");
    differentAddress = new Address("Strasse", "94a", "89454", "Stadt", "Land");
  }

  @Test()
  public void testEqualsMethodWithNull() {
    assertDoesNotThrow(() -> emptyAddress.equals(nullAddress));
    assertFalse(emptyAddress.equals(nullAddress));
  }

  @Test
  public void testEqualsMethodWithEmptyAddress() {
    Address copyEmptyAddress = new Address();
    assertDoesNotThrow(() -> emptyAddress.equals(copyEmptyAddress));
    assertTrue(emptyAddress.equals(copyEmptyAddress));
  }

  @Test
  public void testEqualsMethodWithIdenticalAddress() {
    assertDoesNotThrow(() -> address.equals(copyAddress));
    assertTrue(address.equals(copyAddress));
  }

  @Test
  public void testEqualsMethodWithDifferentAddress() {
    assertDoesNotThrow(() -> address.equals(differentAddress));
    assertFalse(address.equals(differentAddress));
  }

  @Test
  public void testEqualsMethodWithDifferentClass() {
    Object obj = new Object();
    assertDoesNotThrow(() -> address.equals(obj));
    assertFalse(address.equals(obj));
  }

  @Test
  public void testToStringMethod() {
    Address address = new Address(
      "Street",
      "101a",
      "19824",
      "My Town",
      "Disneyland");

    assertEquals(
      "Street 101a, 19824 My Town, Disneyland",
      address.toString()
    );
  }

  @Test
  public void testHashCodeMethodWithIdenticalAddress() {
    int expectedHashCode = address.hashCode();
    int actualHashCode = copyAddress.hashCode();

    assertEquals(expectedHashCode, actualHashCode);
  }

  @Test
  public void testHashCodeMethodWithDifferentAddress() {
    int unexpectedHashCode = address.hashCode();
    int actualHashCode = differentAddress.hashCode();

    assertNotEquals(unexpectedHashCode, actualHashCode);
  }

}
