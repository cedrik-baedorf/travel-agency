package travelagency.service.service.consumption;

public record FlightBookingConsumable(
        String flightCode,
        String departure, String departureDate, String departureTime, String departureTimeZone,
        String arrival, String arrivalDate, String arrivalTime, String arrivalTimeZone,
        Integer numberOfPassengers, Integer flightDuration, Double price, String currencyKey
) {

}
