package travelagency.service.service.consumption;

import java.time.*;
import java.util.*;

import org.junit.jupiter.api.Test;


import org.mockito.Mockito;
import travelagency.service.entities.*;
import travelagency.service.service.data.TravelAgencyViewDataService;

import static org.junit.jupiter.api.Assertions.*;

public class TravelAgencyViewConsumptionServiceImplementationTest {

    /**
     * This private inner class is used to set an id for a <code>Customer</code> object.
     * This class is only necessary for unit testing purposes.
     */
    private static class TestCustomer extends Customer {

        private final Integer ID;

        public TestCustomer(Integer id, String lastName) {
            this.setPersonalData(new PersonalData());
            this.getPersonalData().setLastName(lastName);
            this.ID = id;
        }

        @Override
        public Integer getID() { return ID; }

    }

    /**
     * This private inner class is used to set an id for a <code>Booking</code> object.
     * This class is only necessary for unit testing purposes.
     */
    private static class TestBooking extends Booking {

        private final Integer ID;
        private final double PRICE;

        public TestBooking(
                Integer id, Integer customerID, String lastName, LocalDate date, Double price, String currency
        ) {
            this.ID = id;
            this.setCustomer(new TestCustomer(customerID, lastName));
            this.setDate(date);
            this.PRICE = price;
        }

        public TestBooking(Integer id, Set<Trip> trips) {
            this.ID = id;
            this.setTripSet(trips);
            this.PRICE = 0;
        }

        @Override
        public Integer getID() { return ID; }

        @Override
        public double getTotalPrice() { return PRICE; }

    }

    /**
     * This private inner class is used to set an id for a <code>Trip</code> object.
     * This class is only necessary for unit testing purposes.
     */
    private static class TestTrip extends Trip {

        private final Integer ID;
        private final double PRICE;

        public TestTrip(Integer id, int hotels, int flights, double price) {
            this.ID = id;
            this.PRICE = price;
            Set<HotelBooking> hotelBookings = Mockito.mock(Set.class);
            Mockito.when(hotelBookings.size()).thenReturn(hotels);
            this.setHotelBookingSet(hotelBookings);
            Set<FlightBooking> flightBookings = Mockito.mock(Set.class);
            Mockito.when(flightBookings.size()).thenReturn(flights);
            this.setFlightBookingSet(flightBookings);
        }

        public TestTrip(Integer id, Set<HotelBooking> hotels, Set<FlightBooking> flights) {
            this.ID = id;
            this.PRICE = -1;
            this.setHotelBookingSet(hotels);
            this.setFlightBookingSet(flights);
        }

        @Override
        public Integer getID() { return ID; }

        @Override
        public double getTotalPrice() {
            return PRICE;
        }

        @Override
        public boolean equals(Object obj) {
            if(! this.getClass().equals(obj.getClass())) {
                TestBooking booking = (TestBooking) obj;
                return this.getID().equals(booking.getID());
            }
            return false;
        }

    }

    /**
     * This private inner class is used to set internal values for a <code>Hotel Booking</code>
     * object. This class is only necessary for unit testing purposes.
     */
    private static class TestHotelBooking extends HotelBooking {

        private final double PRICE;

        public TestHotelBooking(String name, Address address, int guests, int nights, double price) {
            this.PRICE = price;
            this.setNumberOfGuests(guests);
            this.setNumberOfNights(nights);
            this.setHotel(new Hotel(name, 0, "EUR", address));
        }

        @Override
        public double getTotalPrice() {
            return PRICE;
        }

    }

    /**
     * This private inner class is used to set internal values for a <code>FlightBooking</code>
     * object. This class is only necessary for unit testing purposes.
     */
    private static class TestFlightBooking extends FlightBooking {

        private final double PRICE;

        public TestFlightBooking(
                String from, LocalDate depDate, LocalTime depTime,
                String to, LocalDate arrDate, LocalTime arrTime,
                Integer passengers, Integer duration, Double price
        ) {
            this.PRICE = price;
            this.setNumberOfPassengers(passengers);
            Flight flight = new Flight() {
                @Override
                public Duration getFlightDuration() {
                    return Duration.ofMinutes(duration);
                }
            };
            FlightConnection connection = new FlightConnection();
            connection.setDepartureAirport(from);
            connection.setArrivalAirport(to);
            flight.setFlightConnection(connection);
            flight.setDepartureTimestamp(ZonedDateTime.of(depDate, depTime, ZoneId.of("UTC+0000")));
            flight.setArrivalTimestamp(ZonedDateTime.of(arrDate, arrTime, ZoneId.of("UTC+0000")));
            this.setFlight(flight);
        }

        @Override
        public double getTotalPrice() {
            return PRICE;
        }

    }

    private TravelAgencyViewDataService createDataService(
        List<Booking> resultList, Integer customerID, String customerName
    ) {
        TravelAgencyViewDataService dataService = Mockito.mock(TravelAgencyViewDataService.class);
        if(customerID != null && customerName != null)
            Mockito.when(dataService.getBookings(customerID, customerName)).thenReturn(resultList);
        else if(customerID != null)
            Mockito.when(dataService.getBookings(customerID)).thenReturn(resultList);
        else if(customerName != null)
            Mockito.when(dataService.getBookings(customerName)).thenReturn(resultList);
        return dataService;
    }

    private TravelAgencyViewDataService createDataService(
        Booking resultBooking, Integer bookingID, Integer customerID, String customerName
    ) {
        TravelAgencyViewDataService dataService = Mockito.mock(TravelAgencyViewDataService.class);
        if(bookingID != null && customerID != null && customerName != null)
            Mockito.when(dataService.getBooking(bookingID, customerID, customerName)).thenReturn(resultBooking);
        else if(bookingID != null && customerID != null)
            Mockito.when(dataService.getBooking(bookingID, customerID)).thenReturn(resultBooking);
        else if(bookingID != null && customerName != null)
            Mockito.when(dataService.getBooking(bookingID, customerName)).thenReturn(resultBooking);
        else if(bookingID != null)
            Mockito.when(dataService.getBooking(bookingID)).thenReturn(resultBooking);
        return dataService;
    }

    private TravelAgencyViewDataService createDataService(Trip resultTrip, int tripID) {
        TravelAgencyViewDataService dataService = Mockito.mock(TravelAgencyViewDataService.class);
        Mockito.when(dataService.getTrip(tripID)).thenReturn(resultTrip);
        return dataService;
    }

    private List<Booking> createBookingList() {
        return List.of(
            new TestBooking(1, 1, "Maier", LocalDate.now(), 50.0, "EUR"),
            new TestBooking(2, 1, "Maier", LocalDate.now(), 100.0, "EUR"),
            new TestBooking(3, 2, "Weiß", LocalDate.now(), 20.9, "EUR")
        );
    }

    private List<BookingConsumable> createBookingConsumableList() {
        return List.of(
            new BookingConsumable(1, 1, "Maier", LocalDate.now(), 50.0, "EUR"),
            new BookingConsumable(2, 1, "Maier", LocalDate.now(), 100.0, "EUR"),
            new BookingConsumable(3, 2, "Weiß", LocalDate.now(), 20.9, "EUR")
        );
    }

    /**
     * This method creates a <code>Set</code> of <code>Trip</code> objects for
     * testing purposes.
     * @return <code>Set<Trip></code> object
     */
    private Set<Trip> createTripSet() {
        return Set.of(
            new TestTrip(1, 5, 7, 99.99),
            new TestTrip(3, 0, 1, 29.99),
            new TestTrip(5, 4, 0, 129.99)
        );
    }

    private List<TripConsumable> createTripConsumableList() {
        return List.of(
            new TripConsumable(1, 5, 7, 99.99),
            new TripConsumable(3, 0, 1, 29.99),
            new TripConsumable(5, 4, 0, 129.99)
        );
    }

    /**
     * This method creates a <code>Set</code> of <code>HotelBooking</code> objects for
     * testing purposes.
     * @return <code>Set<HotelBooking></code> object
     */
    private Set<HotelBooking> createHotelBookingSet() {
        return Set.of(
            new TestHotelBooking("Excelsior", createAddress(1), 4, 2, 99.99),
            new TestHotelBooking("Hotel", createAddress(2), 5, 0, 29.99)
        );
    }

    private List<HotelBookingConsumable> createHotelBookingConsumableList() {
        return List.of(
            new HotelBookingConsumable(
                "Excelsior", createAddress(1).toString(),
                4, 2, 99.99),
            new HotelBookingConsumable(
                "Hotel", createAddress(2).toString(),
                5, 0, 29.99)
        );
    }

    private Address createAddress(int n) {
        return switch(n) {
            case 1 -> new Address(
                    "Street", "5", "12345", "Town", "Country");
            case 2 -> new Address(
                    "Strasse", "1A", "98765", "Stadt", "Land");
            default -> null;
        };
    }

    private Set<FlightBooking> createFlightBookingSet() {
        return Set.of(
            new TestFlightBooking(
                "FRA",
                LocalDate.of(2023, 5, 14),
                LocalTime.of(11, 0, 0),
                "ATL",
                LocalDate.of(2023, 5, 14),
                LocalTime.of(16, 0, 0),
                1, 600, 999.99
            ),
            new TestFlightBooking(
                "ATL",
                LocalDate.of(2023, 7, 29),
                LocalTime.of(11, 0, 0),
                "FRA",
                LocalDate.of(2023, 7, 29),
                LocalTime.of(21, 0, 0),
                5, 600, 1299.99
            )
        );
    }

    private List<FlightBookingConsumable> createFlightBookingConsumableList() {
        return List.of(
            new FlightBookingConsumable(
                "FRA", "2023-05-14", "11:00",
                "ATL", "2023-05-14", "16:00",
                1, 600, 999.99
            ),
            new FlightBookingConsumable(
                "ATL", "2023-07-29", "11:00",
                "FRA", "2023-07-29", "21:00",
                5, 600, 1299.99
            )
        );
    }

    /**
     * This method tests the <code>getBookings(int customerID)</code> method with a valid customer ID to which a
     * corresponding <code>Booking</code> object is provided by the data service.
     */
    @Test
    public void testGetBookingsMethodWithCustomerID() {
        int customerID = 1;

        List<BookingConsumable> expectedBookingConsumables = createBookingConsumableList().stream().filter(
            e -> e.customerID().equals(customerID)
        ).toList();

        List<Booking> resultBookings = createBookingList().stream().filter(
                e -> e.getCustomer().getID().equals(customerID)
        ).toList();

        TravelAgencyViewDataService dataService = createDataService(resultBookings, customerID, null);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<BookingConsumable> actualBookingConsumables = service.getBookings(customerID);

        assertEquals(expectedBookingConsumables, actualBookingConsumables);
    }

    /**
     * This method tests the <code>getBookings(int customerID)</code> method with an invalid customer ID to which no
     * corresponding <code>Booking</code> object is provided by the data service.
     */
    @Test
    public void testGetBookingMethodWithInvalidCustomerID() {
        int customerID = 0;

        List<BookingConsumable> expectedBookingConsumables = new LinkedList<>();

        TravelAgencyViewDataService dataService = createDataService(null, customerID, null);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<BookingConsumable> actualBookingConsumables = service.getBookings(customerID);

        assertEquals(expectedBookingConsumables, actualBookingConsumables);
    }

    /**
     * This method tests the <code>getBookings(String customerName)</code> method with a valid customer's last name to which a
     * corresponding <code>Booking</code> object is provided by the data service.
     */
    @Test
    public void testGetBookingsMethodWithCustomerName() {
        String customerName = "Maier";

        List<BookingConsumable> expectedBookingConsumables = createBookingConsumableList().stream().filter(
                e -> e.customerName().equals(customerName)
        ).toList();

        List<Booking> resultBookings = createBookingList().stream().filter(
                e -> e.getCustomer().getPersonalData().getLastName().equals(customerName)
        ).toList();

        TravelAgencyViewDataService dataService = createDataService(resultBookings, null, customerName);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<BookingConsumable> actualBookingConsumables = service.getBookings(customerName);

        assertEquals(expectedBookingConsumables, actualBookingConsumables);
    }

    /**
     * This method tests the <code>getBookings(String customerName)</code> method with an invalid customer's
     * last name to which no corresponding <code>Booking</code> object is provided by the data service.
     */
    @Test
    public void testGetBookingMethodWithInvalidCustomerName() {
        String customerName = "Jurgen";

        List<BookingConsumable> expectedBookingConsumables = new LinkedList<>();

        TravelAgencyViewDataService dataService = createDataService(null, null, customerName);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<BookingConsumable> actualBookingConsumables = service.getBookings(customerName);

        assertEquals(expectedBookingConsumables, actualBookingConsumables);
    }

    /**
     * This method tests the <code>getBookings(String customerID, customerName)</code> method
     * with a combination of customer id and customer's last name
     * to which a corresponding <code>Booking</code> object is provided by the data service.
     */
    @Test
    public void testGetBookingsMethodWithCustomerIdAndName() {
        int customerID = 1;
        String customerName = "Maier";

        List<BookingConsumable> expectedBookingConsumables = createBookingConsumableList().stream().filter(
                e -> e.customerID().equals(customerID) &&
                        e.customerName().equals(customerName)
        ).toList();

        List<Booking> resultBookings = createBookingList().stream().filter(
                e -> e.getCustomer().getID().equals(customerID) &&
                    e.getCustomer().getPersonalData().getLastName().equals(customerName)
        ).toList();

        TravelAgencyViewDataService dataService = createDataService(resultBookings, customerID, customerName);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<BookingConsumable> actualBookingConsumables = service.getBookings(customerID, customerName);

        assertEquals(expectedBookingConsumables, actualBookingConsumables);
    }

    /**
     * This method tests the <code>getBookings(String customerID, customerName)</code> method
     * with a combination of customer id and a customer's last name
     * to which no corresponding <code>Booking</code> object is provided by the data service.
     */
    @Test
    public void testGetBookingMethodWithCustomerIdAndInvalidName() {
        int customerID = 1;
        String customerName = "Weiß";

        List<BookingConsumable> expectedBookingConsumables = new LinkedList<>();

        TravelAgencyViewDataService dataService = createDataService(null, customerID, customerName);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<BookingConsumable> actualBookingConsumables = service.getBookings(customerID, customerName);

        assertEquals(expectedBookingConsumables, actualBookingConsumables);
    }

    /**
     * This method tests the <code>getBooking(int bookingID)</code> method with a valid booking ID to which a
     * corresponding <code>Booking</code> object is provided by the data service.
     */
    @Test
    public void testGetBookingMethodWithBookingID() {
        int bookingID = 2;

        List<BookingConsumable> expectedBookingConsumables = createBookingConsumableList().stream().filter(
                e -> e.bookingID().equals(bookingID)
        ).toList();

        Booking resultBooking = createBookingList().stream().filter(
                e -> e.getID().equals(bookingID)
        ).toList().get(0);

        TravelAgencyViewDataService dataService = createDataService(resultBooking, bookingID, null, null);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<BookingConsumable> actualBookingConsumables = service.getBooking(bookingID);

        assertEquals(expectedBookingConsumables, actualBookingConsumables);
    }

    /**
     * This method tests the <code>getBooking(int bookingID)</code> method with an invalid booking ID to which no
     * corresponding <code>Booking</code> object is provided by the data service.
     */
    @Test
    public void testGetBookingMethodWithInvalidBookingID() {
        int bookingID = 0;

        List<BookingConsumable> expectedBookingConsumables = new LinkedList<>();

        TravelAgencyViewDataService dataService = createDataService(null, bookingID, null, null);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<BookingConsumable> actualBookingConsumables = service.getBooking(bookingID);

        assertEquals(expectedBookingConsumables, actualBookingConsumables);
    }

    /**
     * This method tests the <code>getBooking(String bookingID, customerID)</code> method
     * with a combination of booking id and customer id
     * to which a corresponding <code>Booking</code> object is provided by the data service.
     */
    @Test
    public void testGetBookingMethodWithBookingAndCustomerID() {
        int bookingID = 2;
        int customerID = 1;

        List<BookingConsumable> expectedBookingConsumables = createBookingConsumableList().stream().filter(
                e -> e.bookingID().equals(bookingID) && e.customerID().equals(customerID)
        ).toList();

        Booking resultBooking = createBookingList().stream().filter(
                e -> e.getID().equals(bookingID) &&
                        e.getCustomer().getID().equals(customerID)
        ).toList().get(0);

        TravelAgencyViewDataService dataService = createDataService(resultBooking, bookingID, customerID, null);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<BookingConsumable> actualBookingConsumables = service.getBooking(bookingID, customerID);

        assertEquals(expectedBookingConsumables, actualBookingConsumables);
    }

    /**
     * This method tests the <code>getBooking(String bookingID, customerID)</code> method
     * with a combination of booking id and customer id
     * to which no corresponding <code>Booking</code> object is provided by the data service.
     */
    @Test
    public void testGetBookingMethodWithInvalidBookingAndCustomerID() {
        int bookingID = 1;
        int customerID = 3;

        List<BookingConsumable> expectedBookingConsumables = new LinkedList<>();

        TravelAgencyViewDataService dataService = createDataService(null, bookingID, customerID, null);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<BookingConsumable> actualBookingConsumables = service.getBooking(bookingID,customerID);

        assertEquals(expectedBookingConsumables, actualBookingConsumables);
    }

    /**
     * This method tests the <code>getBooking(String bookingID, customerName)</code> method
     * with a combination of booking id and customer's last name
     * to which a corresponding <code>Booking</code> object is provided by the data service.
     */
    @Test
    public void testGetBookingMethodWithBookingIDAndCustomerName() {
        int bookingID = 3;
        String customerName = "Weiß";

        List<BookingConsumable> expectedBookingConsumables = createBookingConsumableList().stream().filter(
                e -> e.bookingID().equals(bookingID) && e.customerName().equals(customerName)
        ).toList();

        Booking resultBooking = createBookingList().stream().filter(
                e -> e.getID().equals(bookingID) &&
                        e.getCustomer().getPersonalData().getLastName().equals(customerName)
        ).toList().get(0);

        TravelAgencyViewDataService dataService = createDataService(resultBooking, bookingID, null, customerName);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<BookingConsumable> actualBookingConsumables = service.getBooking(bookingID, customerName);

        assertEquals(expectedBookingConsumables, actualBookingConsumables);
    }

    /**
     * This method tests the <code>getBooking(String bookingID, customerName)</code> method
     * with a combination of booking id and customer's last name
     * to which no corresponding <code>Booking</code> object is provided by the data service.
     */
    @Test
    public void testGetBookingMethodWithInvalidBookingIDAndCustomerName() {
        int bookingID = 3;
        String customerName = "Maier";

        List<BookingConsumable> expectedBookingConsumables = new LinkedList<>();

        TravelAgencyViewDataService dataService = createDataService(null, bookingID, null, customerName);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<BookingConsumable> actualBookingConsumables = service.getBooking(bookingID, customerName);

        assertEquals(expectedBookingConsumables, actualBookingConsumables);
    }

    /**
     * This method tests the <code>getBooking(String bookingID, customerID, customerName)</code> method
     * with a combination of booking id, customer id, and customer's last name
     * to which a corresponding <code>Booking</code> object is provided by the data service.
     */
    @Test
    public void testGetBookingMethodWithBookingIDCustomerIDAndName() {
        int bookingID = 3;
        int customerID = 2;
        String customerName = "Weiß";

        List<BookingConsumable> expectedBookingConsumables = createBookingConsumableList().stream().filter(
                e -> e.bookingID().equals(bookingID) &&
                        e.customerID().equals(customerID) &&
                        e.customerName().equals(customerName)
        ).toList();

        Booking resultBooking = createBookingList().stream().filter(
                e -> e.getID().equals(bookingID) &&
                        e.getCustomer().getID().equals(customerID) &&
                        e.getCustomer().getPersonalData().getLastName().equals(customerName)
        ).toList().get(0);

        TravelAgencyViewDataService dataService = createDataService(resultBooking, bookingID, customerID, customerName);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<BookingConsumable> actualBookingConsumables = service.getBooking(bookingID, customerID, customerName);

        assertEquals(expectedBookingConsumables, actualBookingConsumables);
    }

    /**
     * This method tests the <code>getBooking(String bookingID, customerID, customerName)</code> method
     * with a combination of booking id, customer id, and customer's last name
     * to which no corresponding <code>Booking</code> object is provided by the data service.
     */
    @Test
    public void testGetBookingMethodWithInvalidBookingIDCustomerIDAndName() {
        int bookingID = 3;
        int customerID = 1;
        String customerName = "Weiß";

        List<BookingConsumable> expectedBookingConsumables = new LinkedList<>();

        TravelAgencyViewDataService dataService = createDataService(null, bookingID, customerID, customerName);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<BookingConsumable> actualBookingConsumables = service.getBooking(bookingID, customerID, customerName);

        assertEquals(expectedBookingConsumables, actualBookingConsumables);
    }

    /**
     * This method tests the <code>getTrips(int bookingID)</code> method with a valid booking ID to which a
     * corresponding <code>Booking</code> object exists whose trips must match the expected trips.
     */
    @Test
    public void testGetTripsMethodWithValidID() {
        int bookingID = 1;

        List<TripConsumable> expectedTripConsumables = createTripConsumableList();

        Booking resultBooking = new TestBooking(bookingID, createTripSet());

        TravelAgencyViewDataService dataService = createDataService(resultBooking, bookingID, null, null);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<TripConsumable> actualTripConsumables = service.getTrips(bookingID);

        assertTrue(expectedTripConsumables.containsAll(actualTripConsumables)
            && actualTripConsumables.containsAll(expectedTripConsumables));
    }

    /**
     * This method tests the <code>getTrips(int bookingID)</code> method with a valid booking ID to which a
     * corresponding <code>Booking</code> object exists which does not contain any trips.
     */
    @Test
    public void testGetTripsMethodWithoutTrips() {
        int bookingID = 2;

        List<TripConsumable> expectedTripConsumables = new LinkedList<>();

        Booking resultBooking = new TestBooking(bookingID, new HashSet<>());

        TravelAgencyViewDataService dataService = createDataService(resultBooking, bookingID, null, null);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<TripConsumable> actualTripConsumables = service.getTrips(bookingID);

        assertEquals(expectedTripConsumables, actualTripConsumables);
    }

    /**
     * This method tests the <code>getTrips(int bookingID)</code> method with an invalid booking ID to which no
     * corresponding <code>Booking</code> object exists.
     */
    @Test
    public void testGetTripsMethodWithInvalidID() {
        int bookingID = 0;

        TravelAgencyViewDataService dataService = createDataService(null, bookingID, null, null);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        assertThrows(RuntimeException.class, () -> service.getTrips(bookingID));
    }

    /**
     * This method tests the <code>getHotelBookings(int tripID)</code> method with a valid
     * trip ID to which a corresponding <code>Trip</code> object
     * exists whose hotel bookings must match the expected hotel bookings.
     */
    @Test
    public void testGetHotelBookingsMethodWithValidID() {
        int tripID = 1;

        List<HotelBookingConsumable> expectedHotelBookingConsumables = createHotelBookingConsumableList();

        Trip resultTrip = new TestTrip(tripID, createHotelBookingSet(), null);

        TravelAgencyViewDataService dataService = createDataService(resultTrip, tripID);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<HotelBookingConsumable> actualHotelBookingConsumables = service.getHotelBookings(tripID);

        assertTrue(expectedHotelBookingConsumables.containsAll(actualHotelBookingConsumables)
                && actualHotelBookingConsumables.containsAll(expectedHotelBookingConsumables));
    }

    /**
     * This method tests the <code>getHotelBookings(int tripID)</code> method with a valid trip ID to
     * which a corresponding <code>Trip</code> object exists which does not contain any hotel bookings.
     */
    @Test
    public void testGetHotelBookingsMethodWithoutTrips() {
        int tripID = 2;

        List<HotelBookingConsumable> expectedHotelBookingConsumables = new LinkedList<>();

        Trip resultTrip = new TestTrip(tripID, new HashSet<>(), new HashSet<>());

        TravelAgencyViewDataService dataService = createDataService(resultTrip, tripID);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<HotelBookingConsumable> actualHotelBookingConsumables = service.getHotelBookings(tripID);

        assertEquals(expectedHotelBookingConsumables, actualHotelBookingConsumables);
    }

    /**
     * This method tests the <code>getHotelBookings(int tripID)</code> method with an invalid
     * trip ID to which no corresponding <code>Trip</code> object exists.
     */
    @Test
    public void testGetHotelBookingsMethodWithInvalidID() {
        int tripID = 0;

        TravelAgencyViewDataService dataService = createDataService(null, tripID);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        assertThrows(RuntimeException.class, () -> service.getHotelBookings(tripID));
    }

    /**
     * This method tests the <code>getFlightBookings(int tripID)</code> method with a valid
     * trip ID to which a corresponding <code>Trip</code> object
     * exists whose flight bookings must match the expected flight bookings.
     */
    @Test
    public void testGetFlightBookingsMethodWithValid() {
        int tripID = 1;

        List<FlightBookingConsumable> expectedFlightBookingConsumables = createFlightBookingConsumableList();

        Trip resultTrip = new TestTrip(tripID, null, createFlightBookingSet());

        TravelAgencyViewDataService dataService = createDataService(resultTrip, tripID);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<FlightBookingConsumable> actualFlightBookingConsumables = service.getFlightBookings(tripID);

        assertTrue(expectedFlightBookingConsumables.containsAll(actualFlightBookingConsumables)
                && actualFlightBookingConsumables.containsAll(expectedFlightBookingConsumables));
    }

    /**
     * This method tests the <code>getFlightBookings(int tripID)</code> method with a valid trip ID to
     * which a corresponding <code>Trip</code> object exists which does not contain any flight bookings.
     */
    @Test
    public void testGetFlightBookingsMethodWithoutTrips() {
        int tripID = 2;

        List<FlightBookingConsumable> expectedFlightBookingConsumables = new LinkedList<>();

        Trip resultTrip = new TestTrip(tripID, new HashSet<>(), new HashSet<>());

        TravelAgencyViewDataService dataService = createDataService(resultTrip, tripID);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        List<FlightBookingConsumable> actualFlightBookingConsumables = service.getFlightBookings(tripID);

        assertEquals(expectedFlightBookingConsumables, actualFlightBookingConsumables);
    }

    /**
     * This method tests the <code>getFlightBookings(int tripID)</code> method with an invalid
     * trip ID to which no corresponding <code>Trip</code> object exists.
     */
    @Test
    public void testGetFlightBookingsMethodWithInvalidID() {
        int tripID = 0;

        TravelAgencyViewDataService dataService = createDataService(null, tripID);

        TravelAgencyViewConsumptionService service = new TravelAgencyViewConsumptionServiceImplementation(dataService);

        assertThrows(RuntimeException.class, () -> service.getFlightBookings(tripID));
    }

}
