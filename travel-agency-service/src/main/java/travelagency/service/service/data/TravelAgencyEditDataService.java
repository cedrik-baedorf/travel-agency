package travelagency.service.service.data;

import travelagency.service.entities.Booking;
import travelagency.service.entities.FlightBooking;
import travelagency.service.entities.HotelBooking;
import travelagency.service.entities.Trip;

public interface TravelAgencyEditDataService {

  void updateBooking(Booking previousBooking, Booking newBooking);

  void updateTrip(Trip previousTrip, Trip newTrip);

  void updateHotelBooking(HotelBooking prviousHotelBooking, HotelBooking newHotelBooking);

  void updateBooking(FlightBooking previousFlightBooking, FlightBooking newFlightBooking);

}
