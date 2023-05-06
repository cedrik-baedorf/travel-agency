package travelagency.service.controllers;

import java.io.IOException;
import java.util.Properties;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.PasswordField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import travelagency.service.TravelAgencyServiceApplication;
import travelagency.service.database.TravelAgencyAuthenticator;
import travelagency.service.database.TravelAgencyDatabaseAuthenticator;
import travelagency.service.database.TravelAgencyEntityManagerFactoryImplementation;

/**
 * Controller to view 'landing_page.fxml'
 * @author I551381
 * @version 1.0
 */
public class LandingPageController extends TravelAgencyController {

    /**
     * Logger for errors and additional information
     */
    static final Logger logger = LogManager.getLogger(LandingPageController.class);

    /**
     * Name of the corresponding <code>.fxml</code> file
     */
    public static final String VIEW_NAME = "landing_page.fxml";

    //java fx elements
    @FXML public Text headerMessage;
    @FXML public Group usernameGroup;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordTextField;
    @FXML public Text infoMessage;
    @FXML public Button loginButton;

    /**
     * Authenticator to create database connection
     */
    private static TravelAgencyAuthenticator authenticator = new TravelAgencyDatabaseAuthenticator();

    /**
     * Text to be displayed when login credentials are falsely
     */
    private String msg_invalid_credentials;

    /**
     * Text to be displayed while attempting to log in
     */
    private String msg_login_attempt;

    /**
     * Text to be displayed if this controller is unable to load the next page
     */
    private String msg_view_not_loaded;

    /**
     * Constructor for this controller passing the <code>Application</code> object this
     * instance belongs to
     * @param application Application calling the contructor
     */
    public LandingPageController(TravelAgencyServiceApplication application) {
        this.application = application;
    }

    /**
     * This method is called when the landing_page.fxml file is loaded
     */
    public void initialize() {
        setTexts(application.getLanguageFile());
        usernameTextField.requestFocus();
    }

    /**
     * This private method sets all texts to the corresponding translation in the language file provided.
     * @param languageFile language file name
     */
    private void setTexts(String languageFile) {
        //load texts for java fx elements
        Properties languageProperties = LanguagePropertiesLoader.loadProperties(
                TravelAgencyServiceApplication.LANGUAGE_DIRECTORY + "landing_page/", languageFile
        );
        headerMessage.setText(languageProperties.getProperty("login.title", "Welcome back!"));
        usernameTextField.setPromptText(languageProperties.getProperty("login.username", "Username"));
        passwordTextField.setPromptText(languageProperties.getProperty("login.password", "Password"));
        loginButton.setText(languageProperties.getProperty("login.login", "LOG IN"));

        //load texts for error messages
        Properties errorMessageProperties = LanguagePropertiesLoader.loadProperties(
            TravelAgencyServiceApplication.LANGUAGE_DIRECTORY + "error_messages/", languageFile
        );
        msg_invalid_credentials = errorMessageProperties.getProperty(
            "landingPage.invalidDatabaseCredentials", "Invalid credentials"
        );
        msg_view_not_loaded = errorMessageProperties.getProperty(
            "landingPage.unableToLoadView", "Unable to load service"
        );

        //load texts for info texts
        Properties infoTextProperties = LanguagePropertiesLoader.loadProperties(
            TravelAgencyServiceApplication.LANGUAGE_DIRECTORY + "info_texts/", languageFile
        );
        msg_login_attempt = infoTextProperties.getProperty(
            "landingPage.attemptLogin", "Attempting login..."
        );
    }

    /**
     * This method calls the <code>attempLogin()</code> method.
     * It is triggered when the log in button is pressed
     */
    @FXML
    private void _loginButton_onClick() {
        attemptLogin();
    }

    /**
     * This method calls the <code>attempLogin()</code> method.
     * It is triggered when the username text field is entered
     */
    public void _usernameTextField_onClick() {
        attemptLogin();
    }

    /**
     * This method calls the <code>attempLogin()</code> method.
     * It is triggered when the password text field is entered
     * @param actionEvent automatically generated actionEvent from the runtime
     */
    public void _passwordTextField_onClick(ActionEvent actionEvent) {
        actionEvent.consume();
        attemptLogin();
    }

    /**
     * This private method tries to login into the database using the
     * credentials of the text fields username and password.
     */
    private void attemptLogin() {
        infoMessage.setFill(Color.BLACK);
        infoMessage.setText(msg_login_attempt);
        new Thread(() -> {
            try {
                TravelAgencyEntityManagerFactoryImplementation factory = authenticator.loginToDataBase(
                        usernameTextField.getText(), passwordTextField.getText()
                );
                FXMLLoader loader = TravelAgencyServiceApplication.getFXMLLoader(StartingPageController.VIEW_NAME);
                application.setEntityManagerFactory(factory);
                loader.setControllerFactory(c -> new StartingPageController(application));
                Scene scene = application.loadScene(loader);
                Platform.runLater(() -> application.setScene(scene));
            } catch (IOException | RuntimeException e) {
                logger.error(e.getMessage());
                Platform.runLater(() -> {
                    usernameTextField.clear();
                    passwordTextField.clear();
                    infoMessage.setFill(Color.RED);
                    infoMessage.setText(e.getClass().equals(RuntimeException.class) ? msg_invalid_credentials : msg_view_not_loaded);
                    usernameTextField.requestFocus();
                });
            }
        }).start();
    }
}

