package travelagency.service.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Group;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
import travelagency.service.service.consumption.TravelAgencyViewConsumptionServiceImplementation;
import travelagency.service.service.data.TravelAgencyViewDataServiceImplementation;

import java.util.List;
import java.util.Properties;

public class ViewTripController extends TravelAgencyController {

    static final Logger logger = LogManager.getLogger(ViewTripController.class);

    private Integer tripID;

    public static final String VIEW_NAME = "view_trip.fxml";

    //task bar
    @FXML public Text agencyName;

    @FXML public Text home;
    @FXML public Text searchBookings;
    @FXML public Text createBooking;
    @FXML public Button logoutButton;

    @FXML public ScrollPane scrollPane;
    @FXML public Group flightInformation;

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
        service = new TravelAgencyViewConsumptionServiceImplementation(
            new TravelAgencyViewDataServiceImplementation(
                application.createEntityManager()
            )
        );
        loadFlightBookings();
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
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

    private void loadFlightBookings() {
        if(tripID == null)
            return;
        List<FlightBookingConsumable> flightBookings = service.getFlightBookings(tripID);
        for(int i = 0; i < flightBookings.size(); i++) {
            Group flightGroup = createFlightGroup(flightBookings.get(i));
            flightInformation.getChildren().add(flightGroup);
            flightGroup.setLayoutY(i * 250);
        }
        scrollPane.requestFocus();
        scrollPane.requestLayout();
    }

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

    @FXML
    private void back_button_onClick() {
        //code
    }

    @FXML
    private void frame_3_onClick() {
        //code
    }

    @FXML
    private void _home_onClick() {
        //code
    }

    @FXML
    private void _new_trip_onClick() {
        //code
    }

    @FXML
    private void _bookings_onClick() {
        //code
    }

    @FXML
    private void frame_3_ek3_onClick() {
        //code
    }

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

    public void _logout_onClick() {
        service = null;
        application.setEntityManagerFactory(null);
        application.setRoot(LandingPageController.VIEW_NAME, new LandingPageController(application));
    }
}


