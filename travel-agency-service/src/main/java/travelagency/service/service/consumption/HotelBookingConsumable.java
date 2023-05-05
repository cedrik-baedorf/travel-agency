package travelagency.service.service.consumption;

public record HotelBookingConsumable(
    String hotelName, String address, Integer numberOfGuests, Integer numberOfNights, Double totalPrice) {
}
