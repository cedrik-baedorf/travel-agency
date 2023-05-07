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
import travelagency.service.service.consumption.TravelAgencyViewConsumptionService;
import travelagency.service.service.consumption.TravelAgencyViewConsumptionServiceImplementation;
import travelagency.service.service.data.TravelAgencyViewDataService;
import travelagency.service.service.data.TravelAgencyViewDataServiceImplementation;

/**
 * This class implements the interface <code>TravelAgencyEntityManagerFactory</code>.
 * This class authenticates users by using the users from the database for user management.
 * @author I551381
 * @version 1.0
 */
public class TravelAgencyServiceFactoryImplementation implements TravelAgencyServiceFactory {

  /**
   * Logger for errors and additional information.
   */
  static final Logger logger = LogManager.getLogger(TravelAgencyServiceFactoryImplementation.class);

  /**
   * Error message for a missing property.
   */
  private static final String MSG_MISSING_PROPERTY =
        "Unable to create EntityManagerFactory without property %s";

  /**
   * Error message for an unsuccessful attempt to create an <code>EntityManagerFactory</code> object.
   */
  private static final String MSG_UNABLE_TO_CREATE =
        "Unable to create EntityManagerFactory using properties %s and persistence unit %s";

  /**
   * private <code>EntityManagerFactory</code> used to create <code>EntityManager</code> objects.
   */
  private final EntityManagerFactory entityManagerFactory;

  /**
   * This constructor creates a <code>TravelAgencyEntityManagerFactory</code> object which is connected to the
   * persistence unit specified in the <code>db.properties</code> file using the login properties provided and
   * driver and url properties defined in the <code>db.properties</code> file.
   * @param loginProperties <code>Map</code> object with persistence unit properties
   */
  public TravelAgencyServiceFactoryImplementation(Map<String, String> loginProperties) {
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
   * This method implements the method <code>createEntityManager()</code> from the interface by using the
   * <code>createEntityManager()</code> method of the <code>EntityManagerFactory</code> created in the constructor
   * @return <code>EntityManager</code> object to be used to find and persist entities
   */
  public EntityManager createEntityManager() {
    return entityManagerFactory.createEntityManager();
  }

  /**
   * This method implements the method <code>createViewDataService()</code> from the interface by using the
   * <code>createEntityManager()</code> method of this instance.
   * @return <code>TravelAgencyViewDataService</code> object to be used as a data service
   */
  public TravelAgencyViewDataService createViewDataService() {
    return new TravelAgencyViewDataServiceImplementation(createEntityManager());
  }

  /**
   * This method implements the method <code>createViewConsumptionService()</code> from the interface by using the
   * <code>createViewDataService()</code> method of this instance.
   * @return <code>TravelAgencyViewConsumptionService</code> object to be used as a consumption service
   */
  public TravelAgencyViewConsumptionService createViewConsumptionService() {
    return new TravelAgencyViewConsumptionServiceImplementation(createViewDataService());
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
