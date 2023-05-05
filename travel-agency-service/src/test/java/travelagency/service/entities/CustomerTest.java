package travelagency.service.entities;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CustomerTest {

  private static Customer nullCustomer, emptyCustomer, customer, copyCustomer, differentCustomer;

  @BeforeAll
  public static void initializeCustomers() {
    nullCustomer = null;
    emptyCustomer = new Customer();
    customer = new Customer(
        "DE19582919875254589658745236512589",
        new PersonalData(
            "Merkel",
            "Angela",
            "Dorothea",
            LocalDate.of(1954, 7, 17),
            new Address("Street", "18", "93726", "Town", "Country")
        ),
        new Address("Street","101a","19824","My Town","Disneyland" )
    );
    copyCustomer = new Customer(
        "DE19582919875254589658745236512589",
        new PersonalData(
            "Merkel",
            "Angela",
            "Dorothea",
            LocalDate.of(1954, 7, 17),
            new Address("Street", "18", "93726", "Town", "Country")
        ),
        new Address("Street","101a","19824","My Town","Disneyland" )
    );
    differentCustomer = new Customer(
        "DE12457886135615487659132658132548",
        new PersonalData(
            "Scholz",
            "Olaf",
            "",
            LocalDate.of(1987, 11, 17),
            new Address("Way", "9", "65958", "Stadt", "Osmanien")
        ),
        new Address("Dwy", "18b", "HKL21", "Town", "Ivy")
    );
  }

  @Test()
  public void testEqualsMethodWithNull() {
    assertDoesNotThrow(() -> emptyCustomer.equals(nullCustomer));
    assertFalse(emptyCustomer.equals(nullCustomer));
  }

  @Test
  public void testEqualsMethodWithEmptyCustomer() {
    Customer copyEmptyCustomer = new Customer();
    assertDoesNotThrow(() -> emptyCustomer.equals(copyEmptyCustomer));
    assertTrue(emptyCustomer.equals(copyEmptyCustomer));
  }

  @Test
  public void testEqualsMethodWithIdenticalCustomer() {
    assertDoesNotThrow(() -> customer.equals(copyCustomer));
    assertTrue(customer.equals(copyCustomer));
  }

  @Test
  public void testEqualsMethodWithDifferentCustomer() {
    assertDoesNotThrow(() -> customer.equals(differentCustomer));
    assertFalse(customer.equals(differentCustomer));
  }

  @Test
  public void testEqualsMethodWithDifferentClass() {
    Object obj = new Object();
    assertDoesNotThrow(() -> customer.equals(obj));
    assertFalse(customer.equals(obj));
  }

  @Test
  public void testToStringMethod() {
    Customer peterLustig = new Customer(
        "DE19582919875254589658745236512589",
        new PersonalData(
            "Lustig",
            "Peter",
            "Fritz Willi",
            LocalDate.of(1937, 10, 20),
            new Address("Street", "101a", "19824", "My Town", "Disneyland")
        ),
        new Address("Billing Street","69b","98425","Cash Town","Cashhausen" )
    );

    assertEquals("""
        Peter Fritz Willi Lustig, 1937-10-20
        Street 101a, 19824 My Town, Disneyland
        DE19582919875254589658745236512589
        Billing Address: Billing Street 69b, 98425 Cash Town, Cashhausen""",
        peterLustig.toString()
    );
  }

  @Test
  public void testHashCodeMethodWithIdenticalCustomer() {
    int expectedHashCode  = customer.hashCode();
    int actualHashCode    = copyCustomer.hashCode();

    assertEquals(expectedHashCode, actualHashCode);
  }

  @Test
  public void testHashCodeMethodWithDifferentCustomer() {
    int unexpectedHashCode  = customer.hashCode();
    int actualHashCode      = differentCustomer.hashCode();

    assertNotEquals(unexpectedHashCode, actualHashCode);
  }

}
