package travelagency.service.controllers;

import java.util.Properties;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import travelagency.service.TravelAgencyServiceApplication;

/**
 * Controller to view 'starting_page.fxml'
 * @author I551381
 * @version 1.0
 */
public class StartingPageController extends TravelAgencyController {

    /**
     * Logger for errors and additional information
     */
    static final Logger logger = LogManager.getLogger(StartingPageController.class);

    /**
     * Name of the corresponding <code>.fxml</code> file
     */
    public static final String VIEW_NAME = "starting_page.fxml";

    //fxml elements
    @FXML public Text agencyName;

    @FXML public Text home;
    @FXML public Text searchBookings;
    @FXML public Text createBooking;
    @FXML public Button logoutButton;

    @FXML public Text searchBookingTitle;
    @FXML public Text bookingIDText;
    @FXML public TextField bookingIDTextField;
    @FXML public Text customerNameText;
    @FXML public TextField customerNameTextField;
    @FXML public Text customerIDText;
    @FXML public TextField customerIDTextField;
    @FXML public Button searchBookingButton;
    @FXML public Text createBookingTitle;
    @FXML public Button createBookingButton;

    /**
     * Constructor for this controller passing the <code>Application</code> object this
     * instance belongs to
     * @param application Application calling the contructor
     */
    public StartingPageController(TravelAgencyServiceApplication application) {
        this.application = application;
    }


    /**
     * This method is called when the landing_page.fxml file is loaded
     */
    public void initialize() {
        setTexts(application.getLanguageFile());
        setListeners();
    }

    /**
     * This private method sets all texts to the corresponding translation in the language file provided.
     * @param languageFile language file name
     */
    private void setTexts(String languageFile) {
        Properties languageProperties = LanguagePropertiesLoader.loadProperties(
            TravelAgencyServiceApplication.LANGUAGE_DIRECTORY + "starting_page/", languageFile
        );
        agencyName.setText(languageProperties.getProperty("menu.agencyName", "Agency Reis"));
        home.setText(languageProperties.getProperty("menu.home", "Home"));
        createBooking.setText(languageProperties.getProperty("menu.createBooking", "New Booking"));
        searchBookings.setText(languageProperties.getProperty("menu.showBookings", "Show Bookings"));
        logoutButton.setText(languageProperties.getProperty("menu.logout", "LOG OUT"));

        searchBookingTitle.setText(languageProperties.getProperty("searchBooking.title", "Search for an existing booking"));
        bookingIDText.setText(languageProperties.getProperty("searchBooking.bookingNumber", "Booking No."));
        bookingIDTextField.setPromptText(languageProperties.getProperty("searchBooking.bookingNumber", "Booking No."));
        customerIDText.setText(languageProperties.getProperty("searchBooking.customerNumber", "Customer No."));
        customerIDTextField.setPromptText(languageProperties.getProperty("searchBooking.customerNumber", "Customer No."));
        customerNameText.setText(languageProperties.getProperty("searchBooking.customerName", "Customer Name"));
        customerNameTextField.setPromptText(languageProperties.getProperty("searchBooking.customerName", "Customer Name"));
        searchBookingButton.setText(languageProperties.getProperty("searchBooking.searchButton", "SEARCH"));

        createBookingTitle.setText(languageProperties.getProperty("createBooking.title"));
        createBookingButton.setText(languageProperties.getProperty("createBooking.createButton"));
    }

    /**
     * add text formatters to allow digits only
     */
    private void setListeners() {
        bookingIDTextField.setTextFormatter(createDigitFormatter());
        customerIDTextField.setTextFormatter(createDigitFormatter());
    }

    /**
     * Creates a formatter which only allows digits as inputs
     * @return a digit only <code>TextFormatter</code> object
     */
    private TextFormatter<String> createDigitFormatter() {
        return new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d*")) {
                return change;
            } else {
                return null;
            }
        });
    }

    /**
     * listener for home button in taskbar
     */
    @FXML
    private void _home_onClick() {
        application.setRoot(VIEW_NAME, new StartingPageController(application));
    }

    /**
     * listener for search bookings button in taskbar
     */
    @FXML
    private void _searchBookings_onClick() {
        application.setRoot(ViewBookingsController.VIEW_NAME, new ViewBookingsController(application));
    }

    /**
     * listener for logout button in taskbar
     */
    @FXML
    private void _logout_onClick() {
        application.setEntityManagerFactory(null);
        application.setRoot(LandingPageController.VIEW_NAME, new LandingPageController(application));
    }

    /**
     * listener for search bookings with selected filters from the corresponding text fields
     */
    @FXML
    private void _search_bookings_onClick() {
        FXMLLoader loader = TravelAgencyServiceApplication.getFXMLLoader(ViewBookingsController.VIEW_NAME);
        try {
            ViewBookingsController controller = new ViewBookingsController(application);
            loader.setControllerFactory(c -> controller);
            Scene scene = application.loadScene(loader);
            if(! bookingIDTextField.getText().isEmpty())
                controller.setBookingID(Integer.parseInt(bookingIDTextField.getText()));
            if(! customerIDTextField.getText().isEmpty())
                controller.setCustomerID(Integer.parseInt(customerIDTextField.getText()));
            if(! customerNameTextField.getText().isEmpty())
                controller.setCustomerName(customerNameText.getText());
            application.setScene(scene);
        } catch (LoadException e) {
            logger.error(e.getMessage());
        }
    }
}


