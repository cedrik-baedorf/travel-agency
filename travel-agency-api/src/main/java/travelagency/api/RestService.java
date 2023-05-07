package travelagency.api;

import travelagency.service.service.data.TravelAgencyViewDataService;

import java.sql.Connection;

/**
 * This interface fetches the requested data from the database via prepared statements.
 * @author I551381
 * @version 1.0
 */
public interface RestService {

    /**
     * This method used the <code>TravelAgencyViewDataService</code> object provided to the constructor
     * to retrieve bookings from the database and convert them into a json <code>String</code>.
     * @return string formatted to fit the json requirements
     */
    public String getBookings();

}
