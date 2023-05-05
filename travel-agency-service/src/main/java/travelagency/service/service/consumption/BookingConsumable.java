package travelagency.service.service.consumption;

import java.time.LocalDate;

public record BookingConsumable(
    Integer bookingID, Integer customerID, String customerName, LocalDate date, Double totalPrice, String currencyKey
) {

    /**
     * Additional getter for tableView-compatibility
     * @return bookingID
     */
    public Integer getBookingID() { return bookingID; }

    /**
     * Additional getter for tableView-compatibility
     * @return customerID
     */
    public Integer getCustomerID() { return customerID; }

    /**
     * Additional getter for tableView-compatibility
     * @return customerName
     */
    public String getCustomerName() { return customerName; }

    /**
     * Additional getter for tableView-compatibility
     * @return date
     */
    public LocalDate getDate() { return date; }

    /**
     * Additional getter for tableView-compatibility
     * @return totalPrice
     */
    public Double getTotalPrice() { return totalPrice; }

    /**
     * Additional getter for tableView-compatibility
     * @return currencyKey
     */
    public String getCurrencyKey() { return currencyKey; }


}