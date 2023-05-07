package travel.travelagency;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.Mockito;
import travelagency.api.RestService;
import travelagency.api.RestServiceImpl;
import travelagency.service.entities.*;
import travelagency.service.service.data.TravelAgencyViewDataService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class RestServiceImplTest {

    private List<Booking> createBookingsList() {
        return List.of(
            new Booking(
                new Customer(
                    "DE98451201414511819849156156156156",
                    new PersonalData("Köln", "1. FC", "", LocalDate.of(1948, 2, 13), createAddress(1)),
                    createAddress(1)
                ),
                LocalDate.of(2023, 2, 1),
                Set.of(
                    new Trip(
                        Set.of(
                            new HotelBooking(
                                new Hotel(
                                    "Excelsior Hotel Ernst",
                                    500.0,
                                    createAddress(0)
                                ),
                                3,
                                2
                            )
                        ),
                        Set.of(
                            new FlightBooking(
                                new Flight(
                                    new FlightConnection("LH", "1007", "FRA", "LAX"),
                                    LocalDate.of(2023, 5, 1),
                                    LocalTime.of(12, 45, 0),
                                    "UTC+02:00",
                                    LocalDate.of(2023, 5, 1),
                                    LocalTime.of(15, 15, 0),
                                    "UTC-07:00",
                                    749.99
                                ),
                                2
                            )
                        )
                    )
                )
            )
        );
    }

    private Address createAddress(int n) {
        return switch(n) {
            case 0 -> new Address("Trankgasse", "1-5", "50667", "Köln", "Deutschland");
            case 1 -> new Address("Aachener Straße", "999", "50933", "Köln", "Deutschland");
            default -> null;
        };
    }

    /**
     * This tests whether the service performs correctly when the data service returns a list woth booking(s)
     */
    @Test
    public void testGetBookingsWithList() {
        String expectedJson = """
                [
                  {
                    "date": "2023-02-01",
                    "price": 4499.98,
                    "trips": [
                      {
                        "flights_price": 3000.0,
                        "flight_bookings": [
                          {
                            "flight": {
                              "flight_connection": {
                                "carrierID": "LH",
                                "connectionID": "1007",
                                "departureAirport": "FRA",
                                "arrivalAirport": "LAX"
                              },
                              "departure_timestamp": "2023-05-01T12:45+02:00[UTC+02:00]",
                              "price": 749.99,
                              "arrival_timestamp": "2023-05-01T15:15-07:00[UTC-07:00]"
                            },
                            "passengers": 2,
                            "price": 1499.98
                          }
                        ],
                        "hotels_price": 3000.0,
                        "hotel_bookings": [
                          {
                            "hotel": {
                              "name": "Excelsior Hotel Ernst",
                              "pricePerPerson": 500.0,
                              "address": {
                                "street": "Trankgasse",
                                "number": "1-5",
                                "zipCode": "50667",
                                "town": "Köln",
                                "country": "Deutschland"
                              }
                            },
                            "numberOfGuests": 3,
                            "numberOfNights": 2
                          }
                        ]
                      }
                    ],
                    "customer": {
                      "personal_data": {
                        "address": {
                          "street": "Aachener Straße",
                          "number": "999",
                          "zipCode": "50933",
                          "town": "Köln",
                          "country": "Deutschland"
                        },
                        "date_of_birth": "1948-02-13",
                        "last_name": "Köln",
                        "middle_name": "",
                        "first_name": "1. FC"
                      },
                      "iban": "DE98451201414511819849156156156156",
                      "billing_address": {
                        "street": "Aachener Straße",
                        "number": "999",
                        "zipCode": "50933",
                        "town": "Köln",
                        "country": "Deutschland"
                      }
                    }
                  }
                ]""";

        TravelAgencyViewDataService dataService = Mockito.mock(TravelAgencyViewDataService.class);
        Mockito.when(dataService.getBookings()).thenReturn(createBookingsList());

        RestService restService = new RestServiceImpl(dataService);

        String actualJson = restService.getBookings();

        assertEquals(expectedJson, actualJson);
    }

    /**
     * This tests whether the service performs correctly when the data service returns an empty list
     */
    @Test
    public void testGetBookingsWithEmptyList() {
        String expectedJson = "[]";

        TravelAgencyViewDataService dataService = Mockito.mock(TravelAgencyViewDataService.class);
        Mockito.when(dataService.getBookings()).thenReturn(new LinkedList<>());

        RestService restService = new RestServiceImpl(dataService);

        String actualJson = restService.getBookings();

        assertEquals(expectedJson, actualJson);
    }


}
