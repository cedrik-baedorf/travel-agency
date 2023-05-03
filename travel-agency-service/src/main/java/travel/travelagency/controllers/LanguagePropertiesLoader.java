package travel.travelagency.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.util.Properties;

public class LanguagePropertiesLoader {

    static final Logger logger = LogManager.getLogger(LanguagePropertiesLoader.class);

    private static final String MSG_FAILED_TO_LOAD_PROPERTIES = "Loading %s failed";

    private static final String DEFAULT_PROPERTIES_FILE = "en_US.properties";

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
