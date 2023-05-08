package travelagency.service.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * This class implements the interface <code>TravelAgencyAuthenticator</code>.
 * This class authenticates users by using the users from the database for user management.
 * @author I551381
 * @version 1.0
 */
public class TravelAgencyDatabaseAuthenticator implements TravelAgencyAuthenticator {

    /**
     * Logger for errors and additional information
     */
    static final Logger logger = LogManager.getLogger(TravelAgencyDatabaseAuthenticator.class);

    /**
     * Error message for an unsuccessful login attempt
     */
    private static final String MSG_UNABLE_TO_LOGIN =
        "Unable to log to persistence unit using username '%s' and password = '%s'";

    /**
     * Message for a successful login attempt
     */
    private static final String MSG_NEW_LOGIN =
            "User %s logged into the database";

    /**
     * This method implements the method <code>loginToDataBase(String, String)</code> from the interface by
     * attempting to create a <code>TravelAgencyEntityManagerFactory</code> object with the <code>username</code>
     * and <code>password</code> parameters provided
     * @param username database username
     * @param password datebase password to the username provided
     * @return <code>TravelAgencyEntityManagerFactory</code> object
     */
    @Override
    public TravelAgencyServiceFactory loginToDataBase(String username, String password) {
        Map<String, String> loginProperties = new HashMap<>();
        loginProperties.put("javax.persistence.jdbc.user", username);
        loginProperties.put("javax.persistence.jdbc.password", password);
        try {
            TravelAgencyServiceFactory factory = new TravelAgencyServiceFactoryImplementation(loginProperties);
            logger.info(String.format(MSG_NEW_LOGIN, username));
            return factory;
        } catch(RuntimeException e) {
            final String MSG = String.format(MSG_UNABLE_TO_LOGIN, username, password);
            logger.warn(MSG);
            throw new RuntimeException(MSG);
        }
    }
}
