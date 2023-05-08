package travelagency.service.controllers;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Group;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import travelagency.service.TravelAgencyServiceApplication;
import travelagency.service.service.consumption.FlightBookingConsumable;
import travelagency.service.service.consumption.HotelBookingConsumable;
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

    //Booking details
    @FXML public Group bookingDetailsGroup;
    @FXML public Text bookingDetailsTitle;
    @FXML public Text bookingMasterDataBookingIDText;
    @FXML public Text tripMasterDataTripID;
    @FXML public Text bookingMasterDataCustomerIDText;
    @FXML public Text tripMasterDataCustomerID;
    @FXML public Text bookingMasterDataCustomerNameText;
    @FXML public Text tripMasterDataCustomerName;
    @FXML public Text bookingMasterDataDateText;
    @FXML public Text tripMasterDataDate;
    @FXML public Text tripMasterDataPrice;
    @FXML public Text bookingMasterDataPriceText;
    @FXML public Text tripMasterDataCurrency;
    @FXML public Text bookingMasterDataCurrencyText;
    @FXML public Button backButton;

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
        Properties languageProperties = LanguagePropertiesLoader.loadProperties(
                TravelAgencyServiceApplication.LANGUAGE_DIRECTORY + "view_trip/", application.getLanguageFile()
        );
        loadHotelBookings(languageProperties);
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

        bookingMasterDataBookingIDText.setText(
                languageProperties.getProperty("details.tripID", "Trip No.") + ':');

        flightInformationText.setText(languageProperties.getProperty("flight.information", "Flight Information"));

        hotelInformationText.setText(languageProperties.getProperty("hotel.information", "Hotel Information"));

        Properties languagePropertiesBookings = LanguagePropertiesLoader.loadProperties(
                TravelAgencyServiceApplication.LANGUAGE_DIRECTORY + "view_bookings/", languageFile
        );
        bookingDetailsTitle.setText(languagePropertiesBookings.getProperty("details.title", "Booking Details"));
        bookingMasterDataCustomerIDText.setText(
                languagePropertiesBookings.getProperty("tableView.customerID", "Customer No.") + ':');
        bookingMasterDataCustomerNameText.setText(
                languagePropertiesBookings.getProperty("tableView.customerName", "Customer") + ':');
        bookingMasterDataDateText.setText(
                languagePropertiesBookings.getProperty("tableView.date", "Date of booking") + ':');
        bookingMasterDataPriceText.setText(
                languagePropertiesBookings.getProperty("tableView.totalPrice", "Total Price") + ':');
        bookingMasterDataCurrencyText.setText(
                languagePropertiesBookings.getProperty("tableView.currencyKey", "Currency") + ':');
        backButton.setText(languagePropertiesBookings.getProperty("details.back", "BACK"));
    }

    /**
     * Set id of the trip to be displayed
     * @param tripID id of the trip
     */
    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

    public void setBookingDetails(String tripID, String customerID, String customerName, String date, String price, String currency) {
        this.tripMasterDataTripID.setText(tripID);
        this.tripMasterDataCustomerID.setText(customerID);
        this.tripMasterDataCustomerName.setText(customerName);
        this.tripMasterDataDate.setText(date);
        this.tripMasterDataPrice.setText(price);
        this.tripMasterDataCurrency.setText(currency);
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
            flightGroup.setLayoutY(i * 250 + 50);
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

        Rectangle background = new Rectangle(600, 50, Paint.valueOf("#D9D9D9"));
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
     * This private method uses the <code>service</code> attribute to load
     * a <code>List</code> object of <code>HotelBookingConsumable</code> objects.
     */
    private void loadHotelBookings(Properties languageProps) {
        if(tripID == null)
            return;
        List<HotelBookingConsumable> hotelBookings = service.getHotelBookings(tripID);
        for(int i = 0; i < hotelBookings.size(); i++) {
            Group hotelGroup = createHotelGroup(hotelBookings.get(i), languageProps);
            hotelInformationGroup.getChildren().add(hotelGroup);
            hotelGroup.setLayoutY(i * 350 + 50);
        }
    }

    /**
     * dynamically creates a java fx <code>Group</code> element neatly displaying
     * the <code>HotelBookingConsumable</code> object provided as a parameter
     * @param hotel <code>HotelBookingConsumable</code> object to be displayed
     * @return <code>Group</code> element displaying necessary information from
     *         the <code>HotelBookingConsumable</code> object.
     */
    private Group createHotelGroup(HotelBookingConsumable hotel, Properties languageProps) {
        Group hotelBookingGroup = new Group();

        Rectangle background = new Rectangle(600, 50, Paint.valueOf("#D9D9D9"));
        hotelBookingGroup.getChildren().add(background);

        Text date = new Text(0, 33, hotel.hotelName());
        date.setFont(Font.font("Noto Serif Tamil", 28));
        date.setTextAlignment(TextAlignment.LEFT);
        date.setWrappingWidth(600);
        hotelBookingGroup.getChildren().add(date);

        Text address = new Text(hotel.address());
        address.setFont(Font.font("Noto Serif Tamil", 28));
        address.setTextAlignment(TextAlignment.LEFT);
        address.setWrappingWidth(300);
        hotelBookingGroup.getChildren().add(address);
        address.setLayoutY(100);
        address.setLayoutX(50);

        Text guests = new Text(
            languageProps.getProperty("hotel.guests", "Number of Guests") + " : "
                    + hotel.numberOfGuests().toString());
        guests.setFont(Font.font("Noto Serif Tamil", 28));
        guests.setTextAlignment(TextAlignment.LEFT);
        guests.setWrappingWidth(600);
        hotelBookingGroup.getChildren().add(guests);
        guests.setLayoutY(address.getLayoutY() + 120);
        guests.setLayoutX(address.getLayoutX());

        Text nights = new Text(
            languageProps.getProperty("hotel.nights", "Number of Guests") + " : "
                    + hotel.numberOfNights().toString());
        nights.setFont(Font.font("Noto Serif Tamil", 28));
        nights.setTextAlignment(TextAlignment.LEFT);
        nights.setWrappingWidth(guests.getWrappingWidth());
        hotelBookingGroup.getChildren().add(nights);
        nights.setLayoutY(guests.getLayoutY() + 50);
        nights.setLayoutX(guests.getLayoutX());

        return hotelBookingGroup;
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
            loader.setControllerFactory(c -> controller);
            Scene scene = application.loadScene(loader);
            application.setScene(scene);
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


