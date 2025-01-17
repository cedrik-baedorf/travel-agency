package travelagency.service.database;

import travelagency.service.service.consumption.TravelAgencyViewConsumptionService;
import travelagency.service.service.data.TravelAgencyViewDataService;

import javax.persistence.EntityManager;

/**
 * This interface defines a service which can be used to create entities managers
 * for the persistence unit defined in the <code>db.properties</code> file.
 * @author I551381
 * @version 1.0
 */
public interface TravelAgencyServiceFactory {

    /**
     * Name of the <code>driver</code> property in the <code>persistence.xml</code> file
     */
    String DRIVER_PROPERTY = "javax.persistence.jdbc.driver";

    /**
     * Name of the <code>url</code> property in the <code>persistence.xml</code> file
     */
    String URL_PROPERTY = "javax.persistence.jdbc.url";

    /**
     * Name of the <code>user</code> property in the <code>persistence.xml</code> file
     */
    String USER_PROPERTY = "javax.persistence.jdbc.user";

    /**
     * Name of the <code>password</code> property in the <code>persistence.xml</code> file
     */
    String PASSWORD_PROPERTY = "javax.persistence.jdbc.password";

    /**
     * This public method shall return an <code>EntityManager</code> connected to the persistence unit
     * specified in the <code>db.properties</code> file
     * @return <code>EntityManager</code> object to be used to find and persist entities
     */
    EntityManager createEntityManager();

    /**
     * This public method shall return an <code>TravelAgencyViewDataService</code> connected to the persistence unit
     * specified in the <code>db.properties</code> file
     * @return <code>TravelAgencyViewDataService</code> object to be used as a data service
     */
    TravelAgencyViewDataService createViewDataService();

    /**
     * This public method shall return an <code>TravelAgencyViewConsumptionService</code> connected to the persistence unit
     * specified in the <code>db.properties</code> file
     * @return <code>TravelAgencyViewConsumptionService</code> object to be used as a consumption service
     */
    TravelAgencyViewConsumptionService createViewConsumptionService();

}
