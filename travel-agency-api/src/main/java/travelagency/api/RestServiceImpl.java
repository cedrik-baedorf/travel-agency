package travelagency.api;

import travelagency.service.entities.*;
import travelagency.service.service.data.TravelAgencyViewDataService;

import java.util.*;

/**
 * The RestServiceImpl class implements the RestService interface.
 * It retrieves hotel and flight connection data from the database and converts the data into JSON format.
 *
 * @author I551381
 * @version 1.0
 */
public class RestServiceImpl implements RestService {

    private final TravelAgencyViewDataService DATA_SERVICE;

    public RestServiceImpl(TravelAgencyViewDataService dataService) {
        this.DATA_SERVICE = dataService;
    }

    /**
     * Retrieves booking data from the data service and returns it as a JSON-formatted string.
     *
     * @param dataService The data service used to retrieve booking data
     * @return A JSON-formatted string containing booking data.
     */
    public String getBookings() {
        List<Booking> bookings = DATA_SERVICE.getBookings();
        List<Map<String, Object>> formattedBookings = new ArrayList<>();
        for(Booking booking : bookings) {
            Map<String, Object> propertiesMap = new HashMap<>();
            propertiesMap.put("id", booking.getID());
            propertiesMap.put("date", booking.getDate().toString());
            propertiesMap.put("price", booking.getTotalPrice());
            propertiesMap.put("customer", getCustomer(booking.getCustomer()));
            propertiesMap.put("trips", getTrips(booking.getTripSet()));
            formattedBookings.add(propertiesMap);
        }
        System.out.println("Bookings sent as JSON");
        return GsonConverter.listToJSON(formattedBookings);
    }

    /**
     * This method converts a <code>Set</code> object of <code>Trip</code> objects into a
     * <code>List</code> object of <code>Map<String, Object></code> objects to be readable by
     * <code>GsonConverter</code>.
     * @param trips set of trips to be converted
     * @return list of converted set
     */
    private List<Map<String, Object>> getTrips(Set<Trip> trips) {
        List<Map<String, Object>> formattedTrips = new ArrayList<>();
        for(Trip trip : trips) {
            Map<String, Object> propertiesMap = new HashMap<>();
            propertiesMap.put("id", trip.getID());
            propertiesMap.put("hotel_bookings", trip.getHotelBookingSet());
            propertiesMap.put("hotels_price", trip.getTotalHotelPrice());
            propertiesMap.put("flight_bookings", getFlightBookings(trip.getFlightBookingSet()));
            propertiesMap.put("flights_price", trip.getTotalHotelPrice());
            formattedTrips.add(propertiesMap);
        }
        return formattedTrips;
    }

    /**
     * This method converts a <code>Set</code> object of <code>FlightBooking</code> objects into a
     * <code>List</code> object of <code>Map<String, Object></code> objects to be readable by
     * <code>GsonConverter</code>.
     * @param flightBookings set of flight bookings to be converted
     * @return list of converted set
     */
    private List<Map<String, Object>> getFlightBookings(Set<FlightBooking> flightBookings) {
        List<Map<String, Object>> formattedTrips = new ArrayList<>();
        for(FlightBooking flightBooking : flightBookings) {
            Map<String, Object> propertiesMap = new HashMap<>();
            propertiesMap.put("id", flightBooking.getID());
            propertiesMap.put("price", flightBooking.getTotalPrice());
            propertiesMap.put("passengers", flightBooking.getNumberOfPassengers());
            propertiesMap.put("flight", getFlight(flightBooking.getFlight()));
            formattedTrips.add(propertiesMap);
        }
        return formattedTrips;
    }

    /**
     * This method converts a <code>Flight</code> object into a <code>Map</code> object of its properties
     * to be readable by a <code>GsonConverter</code>.
     * @param flight <code>Flight</code> object to be converted
     * @return <code>Map</code> of the flight's key properties
     */
    private Map<String, Object> getFlight(Flight flight) {
        Map<String, Object> propertiesMap = new HashMap<>();
        propertiesMap.put("id", flight.getID());
        propertiesMap.put("flight_connection", flight.getFlightConnection());
        propertiesMap.put("departure_timestamp", flight.getDepartureTimestamp().toString());
        propertiesMap.put("arrival_timestamp", flight.getArrivalTimestamp().toString());
        propertiesMap.put("price", flight.getPricePerPerson());
        return propertiesMap;
    }

    /**
     * This method converts a <code>Customer</code> object into a <code>Map</code> object of its properties
     * to be readable by a <code>GsonConverter</code>.
     * @param customer <code>Customer</code> object to be converted
     * @return <code>Map</code> of the customer's key properties
     */
    private Map<String, Object> getCustomer(Customer customer) {
        Map<String, Object> propertiesMap = new HashMap<>();
        propertiesMap.put("id", customer.getID());
        propertiesMap.put("iban", customer.getIban());
        propertiesMap.put("billing_address", customer.getBillingAddress());
        propertiesMap.put("personal_data", getPersonalData(customer.getPersonalData()));
        return propertiesMap;
    }

    /**
     * This method converts a <code>PersonalData</code> object into a <code>Map</code> object of its properties
     * to be readable by a <code>GsonConverter</code>.
     * @param personalData <code>PersonalData</code> object to be converted
     * @return <code>Map</code> of the personal data record's key properties
     */
    private Map<String, Object> getPersonalData(PersonalData personalData) {
        Map<String, Object> propertiesMap = new HashMap<>();
        propertiesMap.put("id", personalData.getID());
        propertiesMap.put("last_name", personalData.getLastName());
        propertiesMap.put("first_name", personalData.getFirstName());
        propertiesMap.put("middle_name", personalData.getMiddleName());
        propertiesMap.put("date_of_birth", personalData.getDateOfBirth().toString());
        propertiesMap.put("address", personalData.getAddress());
        return propertiesMap;
    }
}
