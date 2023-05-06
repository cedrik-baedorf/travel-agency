package travelagency.service.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * This class is dedicated to load language properties for java fx elements
 * @author I551381
 * @version 1.0
 */
public class LanguagePropertiesLoader {

    /**
     * Logger for errors and additional information
     */
    static final Logger logger = LogManager.getLogger(LanguagePropertiesLoader.class);

    /**
     * Error message for an unsuccessful attempt to load the properties file.
     */
    private static final String MSG_FAILED_TO_LOAD_PROPERTIES = "Loading %s failed";

    /**
     * Default language file if the language file specified could not be loaded.
     */
    private static final String DEFAULT_PROPERTIES_FILE = "en_US.properties";

    /**
     * This method loads the specified language file into a <code>Properties</code>
     * object with the default <code>Properties</code> object containing the properties
     * from the <code>DEFAULT_PROPERTIES_FILE</code>.
     * @param directory directory of the language files
     * @param propertiesFile language file
     * @return <code>Properties</code> object containing language strings for the ui
     */
    public static Properties loadProperties(String directory, String propertiesFile) {
        String propertiesPath = directory + propertiesFile;
        Properties languageProperties = propertiesFile.equals(DEFAULT_PROPERTIES_FILE) ?
                new Properties() : new Properties(loadProperties(directory, DEFAULT_PROPERTIES_FILE));
        try (FileInputStream is = new FileInputStream(propertiesPath)) {
            languageProperties.load(is);
        } catch (Exception e) {
            final String MSG = String.format(MSG_FAILED_TO_LOAD_PROPERTIES, propertiesPath);
            logger.error(MSG);
            throw new RuntimeException(MSG);
        }
        return languageProperties;
    }

}
