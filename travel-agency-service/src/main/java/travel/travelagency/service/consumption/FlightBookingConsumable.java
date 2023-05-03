package travel.travelagency.service.consumption;

public record FlightBookingConsumable(
        String departure, String departureDate, String departureTime,
        String arrival, String arrivalDate, String arrivalTime,
        Integer numberOfPassengers, Integer flightDuration, Double price
) {

}
