package travelagency.service.controllers;
import javafx.scene.Group;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import travelagency.service.TravelAgencyServiceApplication;
import travelagency.service.service.consumption.FlightBookingConsumable;
import travelagency.service.service.consumption.TravelAgencyViewConsumptionService;
import travelagency.service.service.consumption.TravelAgencyViewConsumptionServiceImplementation;
import travelagency.service.service.data.TravelAgencyViewDataServiceImplementation;

import java.util.List;

public class ViewTripController extends TravelAgencyController {

    private Integer tripID;

    private TravelAgencyViewConsumptionService service;

    public static final String VIEW_NAME = "view_trip.fxml";

    @FXML public ScrollPane scrollPane;
    @FXML public Group flightInformation;

    public ViewTripController(TravelAgencyServiceApplication application) {
        this.application = application;
    }

    public void initialize() {
        service = new TravelAgencyViewConsumptionServiceImplementation(
            new TravelAgencyViewDataServiceImplementation(
                application.createEntityManager()
            )
        );
        loadFlightBookings();
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


}


