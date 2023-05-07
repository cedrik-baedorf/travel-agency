package travelagency.service.service.consumption;

public record TripConsumable(
    Integer tripID, Integer numberOfHotels, Integer numberOfFlights, Double totalPrice, String currencyKey
) {

    /**
     * Additional getter for tableView-compatibility
     * @return trip id
     */
    public Integer getTripID() { return tripID; }

    /**
     * Additional getter for tableView-compatibility
     * @return number of hotels
     */
    public Integer getNumberOfHotels() { return numberOfHotels; }

    /**
     * Additional getter for tableView-compatibility
     * @return number of flights
     */
    public Integer getNumberOfFlights() { return numberOfFlights; }

    /**
     * Additional getter for tableView-compatibility
     * @return total price
     */
    public Double getTotalPrice() { return totalPrice; }

    /**
     * Additional getter for tableView-compatibility
     * @return currencyKey
     */
    public String getCurrencyKey() { return currencyKey; }

}
