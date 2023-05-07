package travelagency.service.controllers;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Group;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import travelagency.service.TravelAgencyServiceApplication;
import travelagency.service.service.consumption.FlightBookingConsumable;
import travelagency.service.service.consumption.TravelAgencyViewConsumptionService;

import java.util.List;
import java.util.Properties;

/**
 * Controller to view 'view_trip.fxml'
 * @author I551381
 * @version 1.0
 */
public class ViewTripController extends TravelAgencyController {

    /**
     * Logger for errors and additional information
     */
    static final Logger logger = LogManager.getLogger(ViewTripController.class);

    /**
     * Name of the corresponding <code>.fxml</code> file
     */
    public static final String VIEW_NAME = "view_trip.fxml";

    /**
     * trip ID of the trip whose details shall be displayed in this instance
     */
    private Integer tripID;

    //fxml elements
    //task bar
    @FXML public Text agencyName;

    @FXML public Text home;
    @FXML public Text searchBookings;
    @FXML public Text createBooking;
    @FXML public Button logoutButton;
    @FXML public ScrollPane scrollPane;

    //flight information
    @FXML public Group flightInformationGroup;
    @FXML public Text flightInformationText;

    //hotel information
    @FXML public Group hotelInformationGroup;
    @FXML public Text hotelInformationText;

    /**
     * service used to display trip details
     */
    private TravelAgencyViewConsumptionService service;

    /**
     * Constructor for this controller passing the <code>Application</code> object this
     * instance belongs to
     * @param application Application calling the contructor
     */
    public ViewTripController(TravelAgencyServiceApplication application) {
        this.application = application;
    }

    /**
     * This method is called when the view_trip.fxml file is loaded
     */
    public void initialize() {
        setTexts(application.getLanguageFile());
        service = application.createViewConsumptionService();
        loadFlightBookings();
    }

    /**
     * This private method sets all texts to the corresponding translation in the language file provided.
     * @param languageFile language file name
     */
    private void setTexts(String languageFile) {
        Properties languageProperties = LanguagePropertiesLoader.loadProperties(
                TravelAgencyServiceApplication.LANGUAGE_DIRECTORY + "view_trip/", languageFile
        );
        agencyName.setText(languageProperties.getProperty("menu.agencyName", "Agency Reis"));
        home.setText(languageProperties.getProperty("menu.home", "Home"));
        createBooking.setText(languageProperties.getProperty("menu.createBooking", "New Booking"));
        searchBookings.setText(languageProperties.getProperty("menu.showBookings", "Show Bookings"));
        logoutButton.setText(languageProperties.getProperty("menu.logout", "LOG OUT"));

        flightInformationText.setText(languageProperties.getProperty("flight.information", "Flight Information"));

        hotelInformationText.setText(languageProperties.getProperty("hotel.information", "Hotel Information"));
    }

    /**
     * Set id of the trip to be displayed
     * @param tripID id of the trip
     */
    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

    /**
     * This private method uses the <code>service</code> attribute to load
     * a <code>List</code> object of <code>FlightBookingConsumable</code> objects.
     */
    private void loadFlightBookings() {
        if(tripID == null)
            return;
        List<FlightBookingConsumable> flightBookings = service.getFlightBookings(tripID);
        for(int i = 0; i < flightBookings.size(); i++) {
            Group flightGroup = createFlightGroup(flightBookings.get(i));
            flightInformationGroup.getChildren().add(flightGroup);
            flightGroup.setLayoutY(i * 250);
        }
    }

    /**
     * dynamically creates a java fx <code>Group</code> element neatly displaying
     * the <code>FlightBookingConsumable</code> object provided as a parameter
     * @param flight <code>FlightBookingConsumable</code> object to be displayed
     * @return <code>Group</code> element displaying necessary information from
     *         the <code>FlightBookingConsumable</code> object.
     */
    private Group createFlightGroup(FlightBookingConsumable flight) {
        Group flightBookingGroup = new Group();

        Rectangle background = new Rectangle(650, 50, Paint.valueOf("#D9D9D9"));
        flightBookingGroup.getChildren().add(background);

        Text date = new Text(0, 33, flight.departureDate());
        date.setFont(Font.font("Noto Serif Tamil", 28));
        date.setTextAlignment(TextAlignment.LEFT);
        date.setWrappingWidth(300);
        flightBookingGroup.getChildren().add(date);

        Group departure = createAirportGroup(
                flight.departureTime(),
                flight.departure(),
                flight.departureTimeZone(),
                flight.flightCode());
        flightBookingGroup.getChildren().add(departure);
        departure.setLayoutY(100);

        Group arrival = createAirportGroup(
                flight.arrivalTime(),
                flight.arrival(),
                flight.arrivalTimeZone(),
                flight.flightCode());
        flightBookingGroup.getChildren().add(arrival);
        arrival.setLayoutY(200);

        return flightBookingGroup;
    }

    /**
     * dynamically creates a java fx <code>Group</code> element with the
     * parameters provided
     * @param timeAsString time to be displayed
     * @param airportCode airport code to be displayed
     * @param timeZoneCode time zone to the time that is displayed
     * @param flightCodeText flight code to be displayed
     * @return <code>Group</code> element of the parameters provided
     */
    private Group createAirportGroup(
        String timeAsString, String airportCode, String timeZoneCode, String flightCodeText
    ) {
        Group airportGroup = new Group();

        //ImageView icon = new ImageView(new Image());
        //airportGroup.getChildren().add(icon);

        Text time = new Text(timeAsString);
        airportGroup.getChildren().add(time);
        time.setFont(Font.font("Noto Serif Tamil", 28));
        time.setTextAlignment(TextAlignment.LEFT);
        time.setLayoutX(140);
        time.setLayoutY(0);
        time.setWrappingWidth(100);

        Text airport = new Text(airportCode);
        airportGroup.getChildren().add(airport);
        airport.setFont(Font.font("Noto Serif Tamil", 28));
        airport.setTextAlignment(TextAlignment.LEFT);
        airport.setLayoutX(time.getLayoutX() + time.getWrappingWidth() + 20);
        airport.setLayoutY(time.getLayoutY());
        airport.setWrappingWidth(100);

        Text timeZone = new Text(timeZoneCode);
        airportGroup.getChildren().add(timeZone);
        timeZone.setFont(Font.font("Noto Serif Tamil", 20));
        timeZone.setTextAlignment(TextAlignment.LEFT);
        timeZone.setLayoutX(time.getLayoutX());
        timeZone.setLayoutY(time.getLayoutY() + 30);
        timeZone.setWrappingWidth(100);

        Text flightCode = new Text(flightCodeText);
        airportGroup.getChildren().add(flightCode);
        flightCode.setFont(Font.font("Noto Serif Tamil", 20));
        flightCode.setTextAlignment(TextAlignment.LEFT);
        flightCode.setLayoutX(airport.getLayoutX());
        flightCode.setLayoutY(timeZone.getLayoutY());
        flightCode.setWrappingWidth(100);

        return airportGroup;
    }

    /**
     * listener for home button in taskbar
     */
    @FXML
    private void _home_onClick() {
        //code
    }

    /**
     * listener for search bookings button in taskbar
     */
    @FXML
    public void _searchBookings_onClick() {
        FXMLLoader loader = TravelAgencyServiceApplication.getFXMLLoader(ViewBookingsController.VIEW_NAME);
        try {
            ViewBookingsController controller = new ViewBookingsController(application);
            loader.setControllerFactory(c -> new ViewBookingsController(application));
            Scene scene = application.loadScene(loader);
            application.setScene(scene);
            controller._searchBookings_onClick();
        } catch (LoadException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * listener for logout button in taskbar
     */
    public void _logout_onClick() {
        service = null;
        application.setEntityManagerFactory(null);
        application.setRoot(LandingPageController.VIEW_NAME, new LandingPageController(application));
    }
}


