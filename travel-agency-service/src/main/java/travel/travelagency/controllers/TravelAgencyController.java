package travel.travelagency.controllers;

import travel.travelagency.TravelAgencyServiceApplication;

/**
 * This abstract class is parent class to all controllers for the travel-agency-service
 * and implements the method <code>setApplication(TravelAgencyServiceApplication) : void</code>.
 * @author I551381
 * @version 1.0
 */
public abstract class TravelAgencyController {

    /**
     * Parent <code>TravelAgencyServiceApplication</code> object
     */
    protected TravelAgencyServiceApplication application;

    /**
     * public method to set the application a controller object belongs to
     * @param application new parent <code>TravelAgencyServiceApplication</code> object
     */
    public void setApplication(TravelAgencyServiceApplication application) {
        this.application = application;
    }

}
