package travelagency.service.database;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.service.spi.ServiceException;

/**
 * This class implements the interface <code>TravelAgencyEntityManagerFactory</code>.
 * This class authenticates users by using the users from the database for user management.
 * @author I551381
 * @version 1.0
 */
public class TravelAgencyEntityManagerFactoryImplementation implements TravelAgencyEntityManagerFactory {

  /**
   * Logger for errors and additional information
   */
  static final Logger logger = LogManager.getLogger(TravelAgencyEntityManagerFactoryImplementation.class);

  private static final String MSG_MISSING_PROPERTY =
        "Unable to create EntityManagerFactor without property %s";

  private static final String MSG_UNABLE_TO_CREATE =
        "Unable to create EntityManagerFactory using properties %s and persistence unit %s";

  /**
   * private <code>EntityManagerFactory</code> used to create <code>EntityManager</code> objects.
   */
  private final EntityManagerFactory entityManagerFactory;

  /**
   * This constructor reads the provided database properties file and creates an <code>EntityManagerFactory</code>
   * object which creates <code>EntityManager</code> objects.
   */
  public TravelAgencyEntityManagerFactoryImplementation(Map<String, String> loginProperties) {
    //check if user property is set
    if(! loginProperties.containsKey(USER_PROPERTY))
      missingProperty(USER_PROPERTY);

    //check if password property is set
    if(! loginProperties.containsKey(PASSWORD_PROPERTY))
      missingProperty(PASSWORD_PROPERTY);

    //load db properties
    String dbPropertiesPath = "db.properties";
    String persistenceUnit = null;
    Properties p = getDBAccessProperties(dbPropertiesPath);

    //load jdbc driver
    if(p.containsKey(DRIVER_PROPERTY))
      loginProperties.put(DRIVER_PROPERTY, p.getProperty(DRIVER_PROPERTY));
    else
      missingProperty(DRIVER_PROPERTY);

    //load jdbc url
    if(p.containsKey(URL_PROPERTY))
      loginProperties.put(URL_PROPERTY, p.getProperty(URL_PROPERTY));
    else
      missingProperty(URL_PROPERTY);

    //try creating EntityManagerFactory
    try {
      persistenceUnit = p.getProperty("persistence_unit");
      if (persistenceUnit != null)
        entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnit, loginProperties);
      else {
        final String MSG = "'persistence_unit' property not found in database properties";
        throw new RuntimeException(MSG);
      }
    } catch (Exception e) {
      final String MSG = String.format(MSG_UNABLE_TO_CREATE, loginProperties, persistenceUnit);
      logger.error(MSG);
      throw new ServiceException(MSG);
    }
  }

  /**
   * This method is used to create an <code>EntityManager</code> object that can persist, read, update, and delete
   * Entities in the database connected to the peristence unit specified in the constructor.
   * @return <code>EntityManager</code> object connected to the database
   */
  public EntityManager createEntityManager() {
    return entityManagerFactory.createEntityManager();
  }

  private Properties getDBAccessProperties(String dbPropertiesPath) {
    Properties dbAccessProperties;
    try(InputStream is = getClass().getClassLoader().getResourceAsStream(dbPropertiesPath)) {
      dbAccessProperties = new Properties();
      dbAccessProperties.load( is );
    } catch (Exception e) {
      final String msg = "Loading database connection properties failed";
      throw new RuntimeException(msg);
    }
    return dbAccessProperties;
  }

  private void missingProperty(String property) throws RuntimeException {
    final String MSG = String.format(MSG_MISSING_PROPERTY, property);
    logger.warn(MSG);
    throw new RuntimeException(MSG);
  }

}
