package travel.travelagency.service.data;

import travel.travelagency.entities.Booking;
import travel.travelagency.entities.FlightBooking;
import travel.travelagency.entities.HotelBooking;
import travel.travelagency.entities.Trip;

public interface TravelAgencyEditDataService {

  void updateBooking(Booking previousBooking, Booking newBooking);

  void updateTrip(Trip previousTrip, Trip newTrip);

  void updateHotelBooking(HotelBooking prviousHotelBooking, HotelBooking newHotelBooking);

  void updateBooking(FlightBooking previousFlightBooking, FlightBooking newFlightBooking);

}
