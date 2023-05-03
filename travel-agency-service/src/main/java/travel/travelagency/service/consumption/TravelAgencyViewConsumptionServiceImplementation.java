package travel.travelagency.service.consumption;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import travel.travelagency.entities.*;
import travel.travelagency.service.data.TravelAgencyViewDataService;

import java.util.LinkedList;
import java.util.List;

public class TravelAgencyViewConsumptionServiceImplementation implements TravelAgencyViewConsumptionService {

    static final Logger logger = LogManager.getLogger(TravelAgencyViewConsumptionServiceImplementation.class);

    private final TravelAgencyViewDataService dataService;

    public TravelAgencyViewConsumptionServiceImplementation(TravelAgencyViewDataService dataService) {
        this.dataService = dataService;
    }

    /**
     * This private method is used to map all the necessary attributes from a List of
     * <code>Booking</code> jpa entities to a List of <code>BookingConsumable</code> objects
     * containing all necessary information for the front end
     * @param bookings A List of all <code>Booking</code> jpa entities to be converted
     * @return A List of successfully converted <code>BookingConsumable</code> objects
     */
    private List<BookingConsumable> convertBookingEntitiesToConsumables(List<Booking> bookings) {
        List<BookingConsumable> bookingConsumables = new LinkedList<>();
        for (Booking booking : bookings) {
            PersonalData pDat = booking.getCustomer().getPersonalData();
            bookingConsumables.add(new BookingConsumable(
                booking.getID(),
                booking.getCustomer().getID(),
                (
                    (pDat.getFirstName() != null && ! pDat.getFirstName().isEmpty() ? pDat.getFirstName() + " " : "") +
                    (pDat.getMiddleName() != null && ! pDat.getMiddleName().isEmpty() ? pDat.getMiddleName() + " " : "") +
                    (pDat.getLastName() != null ? pDat.getLastName() : "")
                ),
                booking.getDate(),
                Math.floor(booking.getTotalPrice() * 100) / 100,
                "EUR"
            ));
        }
        return bookingConsumables;
    }

    /**
     * This private method is used to map all the necessary attributes from a List of
     * <code>Trip</code> jpa entities to a List of <code>TripConsumable</code> objects
     * containing all necessary information for the front end
     * @param trips A List of all <code>Trip</code> jpa entities to be converted
     * @return A List of successfully converted <code>TripConsumable</code> objects
     */
    private List<TripConsumable> convertTripEntitiesToConsumables(List<Trip> trips) {
        List<TripConsumable> tripConsumables = new LinkedList<>();
        for (Trip trip : trips) {
            tripConsumables.add(new TripConsumable(
                trip.getID(),
                trip.getHotelBookingSet().size(),
                trip.getFlightBookingSet().size(),
                trip.getTotalPrice()
            ));
        }
        return tripConsumables;
    }

    /**
     * This private method is used to map all the necessary attributes from a List of
     * <code>HotelBooking</code> jpa entities to a List of <code>HotelBookingConsumable</code> objects
     * containing all necessary information for the front end
     * @param hotelBookings A List of all <code>HotelBooking</code> jpa entities to be converted
     * @return A List of successfully converted <code>HotelBookingConsumable</code> objects
     */
    private List<HotelBookingConsumable> convertHotelBookingEntitiesToConsumables(List<HotelBooking> hotelBookings) {
        List<HotelBookingConsumable> hotelBookingConsumables = new LinkedList<>();
        for (HotelBooking hotelBooking : hotelBookings) {
            hotelBookingConsumables.add(new HotelBookingConsumable(
                hotelBooking.getHotel().getName(),
                hotelBooking.getHotel().getAddress().toString(),
                hotelBooking.getNumberOfGuests(),
                hotelBooking.getNumberOfNights(),
                hotelBooking.getTotalPrice()
            ));
        }
        return hotelBookingConsumables;
    }

    /**
     * This private method is used to map all the necessary attributes from a List of
     * <code>FlightBooking</code> jpa entities to a List of <code>FlightBookingConsumable</code> objects
     * containing all necessary information for the front end
     * @param flightBookings A List of all <code>FlightBooking</code> jpa entities to be converted
     * @return A List of successfully converted <code>FlightBookingConsumable</code> objects
     */
    private List<FlightBookingConsumable> convertFlightBookingEntityToConsumable(List<FlightBooking> flightBookings) {
        List<FlightBookingConsumable> flightBookingConsumables = new LinkedList<>();
        for (FlightBooking flightBooking : flightBookings) {
            Flight flight = flightBooking.getFlight();
            FlightConnection connection = flight.getFlightConnection();
            flightBookingConsumables.add(new FlightBookingConsumable(
                connection.getDepartureAirport(),
                flight.getDepartureTimestamp().toLocalDate().toString(),
                flight.getDepartureTimestamp().toLocalTime().toString(),
                connection.getArrivalAirport(),
                flight.getArrivalTimestamp().toLocalDate().toString(),
                flight.getArrivalTimestamp().toLocalTime().toString(),
                flightBooking.getNumberOfPassengers(),
                (int) flight.getFlightDuration().toMinutes(),
                flightBooking.getTotalPrice()
            ));
        }
        return flightBookingConsumables;
    }

    @Override
    public List<BookingConsumable> getBookings(int customerID, String customerLastName) {
        List<Booking> bookingList = dataService.getBookings(customerID, customerLastName);
        return bookingList == null ? new LinkedList<>() : convertBookingEntitiesToConsumables(bookingList);
    }

    @Override
    public List<BookingConsumable> getBookings(int customerID) {
        List<Booking> bookingList = dataService.getBookings(customerID);
        return bookingList == null ? new LinkedList<>() : convertBookingEntitiesToConsumables(bookingList);
    }

    @Override
    public List<BookingConsumable> getBookings(String customerLastName) {
        List<Booking> bookingList = dataService.getBookings(customerLastName);
        return bookingList == null ? new LinkedList<>() : convertBookingEntitiesToConsumables(bookingList);
    }

    @Override
    public List<BookingConsumable> getBookings() {
        List<Booking> bookingList = dataService.getBookings();
        return bookingList == null ? new LinkedList<>() : convertBookingEntitiesToConsumables(bookingList);
    }

    @Override
    public List<BookingConsumable> getBooking(int bookingID, int customerID, String customerLastName) {
        Booking booking = dataService.getBooking(bookingID, customerID, customerLastName);
        return booking == null ? new LinkedList<>() : convertBookingEntitiesToConsumables(List.of(booking));
    }

    @Override
    public List<BookingConsumable> getBooking(int bookingID, int customerID) {
        Booking booking = dataService.getBooking(bookingID, customerID);
        return booking == null ? new LinkedList<>() : convertBookingEntitiesToConsumables(List.of(booking));
    }

    @Override
    public List<BookingConsumable> getBooking(int bookingID, String customerLastName) {
        Booking booking = dataService.getBooking(bookingID, customerLastName);
        return booking == null ? new LinkedList<>() : convertBookingEntitiesToConsumables(List.of(booking));
    }

    @Override
    public List<BookingConsumable> getBooking(int bookingID) {
        Booking booking = dataService.getBooking(bookingID);
        return booking == null ? new LinkedList<>() : convertBookingEntitiesToConsumables(List.of(booking));
    }

    @Override
    public List<TripConsumable> getTrips(int bookingID) {
        Booking booking = dataService.getBooking(bookingID);
        if(booking == null) {
            final String MSG = "No Booking with booking id = %s found";
            logger.warn(MSG);
            throw new RuntimeException(MSG);
        }
        return convertTripEntitiesToConsumables(booking.getTripSet().stream().toList());
    }

    @Override
    public List<HotelBookingConsumable> getHotelBookings(int tripID) {
        Trip trip = dataService.getTrip(tripID);
        if(trip == null) {
            final String MSG = "No Trip with trip id = %s found";
            logger.warn(MSG);
            throw new RuntimeException(MSG);
        }
        return convertHotelBookingEntitiesToConsumables(trip.getHotelBookingSet().stream().toList());
    }

    @Override
    public List<FlightBookingConsumable> getFlightBookings(int tripID) {
        Trip trip = dataService.getTrip(tripID);
        if(trip == null) {
            final String MSG = "No Trip with trip id = %s found";
            logger.warn(MSG);
            throw new RuntimeException(MSG);
        }
        return convertFlightBookingEntityToConsumable(trip.getFlightBookingSet().stream().toList());
    }
}