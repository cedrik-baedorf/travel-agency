package travelagency.service.database;

/**
 * This interface defines a service which can be used to
 * authenticate users for the database service requested.
 * @author I551381
 * @version 1.0
 */
public interface TravelAgencyAuthenticator {

    /**
     * Public method verified the validity of the credentials.
     * If the correct credentials are provided, this method returns a
     * <code>TravelAgencyEntityManagerFactory</code> object connected to the database
     * specified in the 'db.properties' file.
     * If the provided credentials are invalid, an Exception will be thrown instead.
     * @param username username
     * @param password password to the username provided
     * @return entity manager factory
     */
    public TravelAgencyEntityManagerFactoryImplementation loginToDataBase(String username, String password);


}