package travelagency.service.controllers;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import travelagency.service.TravelAgencyServiceApplication;
import travelagency.service.service.consumption.BookingConsumable;
import travelagency.service.service.consumption.TravelAgencyViewConsumptionService;
import travelagency.service.service.consumption.TravelAgencyViewConsumptionServiceImplementation;
import travelagency.service.service.consumption.TripConsumable;
import travelagency.service.service.data.TravelAgencyViewDataServiceImplementation;

public class ViewBookingsController extends TravelAgencyController {

    static final Logger logger = LogManager.getLogger(ViewBookingsController.class);

    public static final String VIEW_NAME = "view_bookings.fxml";

    //title bar
    @FXML public Text agencyName;

    @FXML public Text home;
    @FXML public Text searchBookings;
    @FXML public Text createBooking;
    @FXML public Button logoutButton;

    //search bar
    @FXML public Group searchBookingGroup;
    @FXML public Text searchBookingTitle;
    @FXML public Text bookingIDText;
    @FXML public TextField bookingIDTextField;
    @FXML public Text customerNameText;
    @FXML public TextField customerNameTextField;
    @FXML public Text customerIDText;
    @FXML public TextField customerIDTextField;
    @FXML public Button searchBookingButton;

    //Booking details
    @FXML public Group bookingDetailsGroup;
    @FXML public Text bookingDetailsTitle;
    @FXML public Text bookingMasterDataBookingIDText;
    @FXML public Text bookingMasterDataBookingID;
    @FXML public Text bookingMasterDataCustomerIDText;
    @FXML public Text bookingMasterDataCustomerID;
    @FXML public Text bookingMasterDataCustomerNameText;
    @FXML public Text bookingMasterDataCustomerName;
    @FXML public Text bookingMasterDataDateText;
    @FXML public Text bookingMasterDataDate;
    @FXML public Text bookingMasterDataPrice;
    @FXML public Text bookingMasterDataPriceText;
    @FXML public Text bookingMasterDataCurrency;
    @FXML public Text bookingMasterDataCurrencyText;
    @FXML public Button backButton;

    //table view
    @FXML public TableView<BookingConsumable> bookingsTableView;
    @FXML public TableView<TripConsumable> tripTableView;

    /**
     * service used to display bookings and trips in their corresponding table view
     */
    private TravelAgencyViewConsumptionService service;

    /**
     * Constructor for this controller passing the <code>Application</code> object this
     * instance belongs to
     * @param application Application calling the contructor
     */
    public ViewBookingsController(TravelAgencyServiceApplication application) {
        this.application = application;
    }

    /**
     * This method is called when the landing_page.fxml file is loaded
     */
    public void initialize() {
        setTexts(application.getLanguageFile());
        this.service = new TravelAgencyViewConsumptionServiceImplementation(
            new TravelAgencyViewDataServiceImplementation(
                application.createEntityManager()
            )
        );
        this.loadBookingsTableView();
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

        bookingDetailsTitle.setText(languageProperties.getProperty("details.title", "Booking Details"));
        bookingMasterDataBookingIDText.setText(
                languageProperties.getProperty("tableView.bookingID", "Booking No.") + ':');
        bookingMasterDataCustomerIDText.setText(
                languageProperties.getProperty("tableView.customerID", "Customer No.") + ':');
        bookingMasterDataCustomerNameText.setText(
                languageProperties.getProperty("tableView.customerName", "Customer") + ':');
        bookingMasterDataDateText.setText(
                languageProperties.getProperty("tableView.date", "Date of booking") + ':');
        bookingMasterDataPriceText.setText(
                languageProperties.getProperty("tableView.totalPrice", "Total Price") + ':');
        bookingMasterDataCurrencyText.setText(
                languageProperties.getProperty("tableView.currencyKey", "Currency") + ':');
        backButton.setText(languageProperties.getProperty("details.back", "BACK"));
    }

    /**
     * this table loads all bookings into the bookingTableView using
     * the filters from the corresponding text fields.
     */
    public void loadBookingsTableView() {
        bookingDetailsGroup.setVisible(false);
        tripTableView.setVisible(false);
        bookingsTableView.getColumns().clear();
        Properties languageProperties = LanguagePropertiesLoader.loadProperties(
            TravelAgencyServiceApplication.LANGUAGE_DIRECTORY + "view_bookings/", application.getLanguageFile()
        );
        TableColumn<BookingConsumable, Integer> bookingIDCol = createTableColumn(languageProperties, "bookingID", 200);
        TableColumn<BookingConsumable, Integer> customerIDCol = createTableColumn(languageProperties, "customerID", 200);
        TableColumn<BookingConsumable, String> customerNameCol = createTableColumn(languageProperties, "customerName", 200);
        TableColumn<BookingConsumable, LocalDate> bookingDateCol = createTableColumn(languageProperties, "date", 200);
        TableColumn<BookingConsumable, Double> totalPriceCol = createTableColumn(languageProperties, "totalPrice", 200);
        TableColumn<BookingConsumable, String> currencyCol = createTableColumn(languageProperties, "currencyKey", 100);

        new Thread(() -> {
            List<BookingConsumable> bookingsList = loadBookingList();
            ObservableList<BookingConsumable> bookingsObsList = FXCollections.observableList(bookingsList);
            Platform.runLater(() -> {
                bookingsTableView.getColumns().add(bookingIDCol);
                bookingsTableView.getColumns().add(customerIDCol);
                bookingsTableView.getColumns().add(customerNameCol);
                bookingsTableView.getColumns().add(bookingDateCol);
                bookingsTableView.getColumns().add(totalPriceCol);
                bookingsTableView.getColumns().add(currencyCol);

                bookingsTableView.getItems().clear();
                bookingsTableView.setItems(bookingsObsList);
                bookingsTableView.setPlaceholder(null);
                searchBookingGroup.setVisible(true);
                bookingsTableView.setVisible(true);
            });
        }).start();
    }

    /**
     * this table loads all trips into the tripTableView using
     * the booking from the selected booking
     */
    public void loadTripsTableView() {
        searchBookingGroup.setVisible(false);
        bookingsTableView.setVisible(false);
        tripTableView.getColumns().clear();
        Properties languageProperties = LanguagePropertiesLoader.loadProperties(
                TravelAgencyServiceApplication.LANGUAGE_DIRECTORY + "view_bookings/", application.getLanguageFile()
        );
        TableColumn<TripConsumable, Integer> tripIDCol = createTableColumn(languageProperties, "tripID", 200);
        TableColumn<TripConsumable, Integer> noHotelsCol = createTableColumn(languageProperties, "numberOfHotels", 200);
        TableColumn<TripConsumable, Integer> noFlightsCol = createTableColumn(languageProperties, "numberOfFlights", 200);
        TableColumn<TripConsumable, Double> totalPriceCol = createTableColumn(languageProperties, "totalPrice", 200);

        new Thread(() -> {
            List<TripConsumable> tripList = loadTripList();
            ObservableList<TripConsumable> tripObsList = FXCollections.observableList(tripList);
            Platform.runLater(() -> {
                tripTableView.getColumns().add(tripIDCol);
                tripTableView.getColumns().add(noHotelsCol);
                tripTableView.getColumns().add(noFlightsCol);
                tripTableView.getColumns().add(totalPriceCol);

                tripTableView.getItems().clear();
                tripTableView.setItems(tripObsList);
                tripTableView.setPlaceholder(null);
                bookingDetailsGroup.setVisible(true);
                tripTableView.setVisible(true);
            });
        }).start();
    }

    /**
     * This method creates a new table column with the provided id, width, and title
     * loaded from the language properties
     * @param languageProperties language properties determining the column's heading
     * @param id id of the column. This attribute will later be retrieved using a corresponding getter
     * @param width column width
     * @return <code>TableColumn</code> object with the attributes specified
     * @param <T> underlying table structure
     * @param <V> data type of column
     */
    private <T, V> TableColumn<T, V> createTableColumn(Properties languageProperties, String id, int width) {
        TableColumn<T, V> column = new TableColumn<>(languageProperties.getProperty("tableView." + id, id));
        column.setCellValueFactory(new PropertyValueFactory<>(id));
        column.setPrefWidth(width);
        return column;
    }

    private List<BookingConsumable> loadBookingList() {
        List<BookingConsumable> bookingsList;
        Integer bookingID = bookingIDTextField.getText().isEmpty() ?
                null : Integer.parseInt(bookingIDTextField.getText());
        Integer customerID = customerIDTextField.getText().isEmpty() ?
                null : Integer.parseInt(customerIDTextField.getText());
        String customerName = customerNameTextField.getText().isEmpty() ?
                null : customerNameTextField.getText();
        if(bookingID != null) {
            if(customerID != null && customerName != null)
                bookingsList = service.getBooking(bookingID, customerID, customerName);
            else if(customerID != null)
                bookingsList = service.getBooking(bookingID, customerID);
            else if(customerName != null)
                bookingsList = service.getBooking(bookingID, customerName);
            else
                bookingsList = service.getBooking(bookingID);
        } else {
            if(customerID != null && customerName != null)
                bookingsList = service.getBookings(customerID, customerName);
            else if(customerID != null)
                bookingsList = service.getBookings(customerID);
            else if(customerName != null)
                bookingsList = service.getBookings(customerName);
            else
                bookingsList = service.getBookings();
        }
        return bookingsList;
    }

    private List<TripConsumable> loadTripList() {
        Integer bookingID = bookingIDTextField.getText().isEmpty() ?
            null : Integer.parseInt(bookingIDTextField.getText());
        if(bookingID != null)
            return service.getTrips(bookingID);
        else
            return new LinkedList<>();
    }

    public void setBookingID(int bookingID) {
        bookingIDTextField.setText(String.valueOf(bookingID));
    }

    public void setCustomerID(int customerID) {
        customerIDTextField.setText(String.valueOf(customerID));
    }

    public void setCustomerName(String customerName) {
        customerNameTextField.setText(customerName);
    }

    public void _home_onClick(MouseEvent mouseEvent) {
        mouseEvent.consume();
        FXMLLoader loader = TravelAgencyServiceApplication.getFXMLLoader(StartingPageController.VIEW_NAME);
        try {
            loader.setControllerFactory(c -> new StartingPageController(application));
            Scene scene = application.loadScene(loader);
            application.setScene(scene);
        } catch (LoadException e) {
            logger.error(e.getMessage());
        }
    }

    public void _searchBookings_onClick() {
        bookingIDTextField.clear();
        customerIDTextField.clear();
        customerNameTextField.clear();
        this.loadBookingsTableView();
    }

    public void _logout_onClick(ActionEvent actionEvent) {
        actionEvent.consume();
        service = null;
        application.setEntityManagerFactory(null);
        application.setRoot(LandingPageController.VIEW_NAME, new LandingPageController(application));
    }

    public void _bookingsTableView_onClick(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount() < 2)
            return;
        TableView<BookingConsumable> tableView = (TableView<BookingConsumable>) mouseEvent.getSource();
        BookingConsumable bookingConsumable = tableView.getSelectionModel().getSelectedItem();
        bookingMasterDataBookingID.setText(String.valueOf(bookingConsumable.getBookingID()));
        bookingMasterDataCustomerID.setText(String.valueOf(bookingConsumable.getCustomerID()));
        bookingMasterDataCustomerName.setText(bookingConsumable.getCustomerName());
        bookingMasterDataDate.setText(bookingConsumable.getDate().toString());
        bookingMasterDataPrice.setText(String.valueOf(bookingConsumable.getTotalPrice()));
        bookingMasterDataCurrency.setText(bookingConsumable.getCurrencyKey());
        bookingIDTextField.setText(bookingConsumable.getBookingID().toString());
        loadTripsTableView();
    }

    public void _tripTableView_onClick(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount() < 2)
            return;
        TableView<TripConsumable> tableView = (TableView<TripConsumable>) mouseEvent.getSource();
        TripConsumable tripConsumable = tableView.getSelectionModel().getSelectedItem();
        FXMLLoader loader = TravelAgencyServiceApplication.getFXMLLoader(ViewTripController.VIEW_NAME);
        try {
            ViewTripController controller = new ViewTripController(application);
            controller.setTripID(tripConsumable.getTripID());
            loader.setControllerFactory(c -> controller);
            Scene scene = application.loadScene(loader);
            application.setScene(scene);
        } catch (LoadException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Action Listener for search button and text fields for booking id, customer id, and customer name
     */
    public void _search_bookings_onClick() {
        this.loadBookingsTableView();
    }
}