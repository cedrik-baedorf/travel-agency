package travelagency.service.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class TravelAgencyDatabaseAuthenticator {

    static final Logger logger = LogManager.getLogger(TravelAgencyDatabaseAuthenticator.class);

    private static final String MSG_UNABLE_TO_LOGIN =
        "Unable to log to persistence unit using username '%s' and password = '%s'";

    /**
     * Public static method returns a <code>TravelAgencyEntityManagerFactory</code> object
     * connected to the database with the credentials provided.
     * If the provided credentials are invalid, an Exception will be thrown instead.
     * @param username database username
     * @param password datebase password to the username provided
     * @return entity manager factory
     */
    public static TravelAgencyEntityManagerFactory loginToDataBase(String username, String password) {
        Map<String, String> loginProperties = new HashMap<>();
        loginProperties.put("javax.persistence.jdbc.user", username);
        loginProperties.put("javax.persistence.jdbc.password", password);
        try {
            return new TravelAgencyEntityManagerFactory(loginProperties);
        } catch(RuntimeException e) {
            final String MSG = String.format(MSG_UNABLE_TO_LOGIN, username, password);
            logger.warn(MSG);
            throw new RuntimeException(MSG);
        }
    }
}
