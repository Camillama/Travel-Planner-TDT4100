package myProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class BookingController {

    @FXML
    private AnchorPane canvas, popUp;
    @FXML
    private TextField travelFrom, travelTo;
    @FXML
    private Button searchButton;
    @FXML
    private ListView<String> relevantBookings;
    @FXML
    private CheckBox planeCheck, carCheck, boatCheck;
    @FXML
    private MenuItem alphabeticallyCheck, capacityCheck;
    @FXML
    private TextArea bookingConfirmation;
    @FXML
    private Text tripPrice;

    Hub hub = new Hub();

    @FXML
    public void initialize() {
        hub.initialize("trips.txt");
        popUp.setVisible(false);
    }

    @FXML
    public void pressSearchButton() {
        setPopUpVisibilityFalse();
        List<String> vehicleTypes = new ArrayList<>();

        try {
            if (planeCheck.selectedProperty().getValue())
                vehicleTypes.add("Plane");
            if (carCheck.selectedProperty().getValue())
                vehicleTypes.add("Car");
            if (boatCheck.selectedProperty().getValue())
                vehicleTypes.add("Boat");
            if (!planeCheck.selectedProperty().getValue() && !boatCheck.selectedProperty().getValue()
                    && !carCheck.selectedProperty().getValue()) {
                vehicleTypes.add("Plane");
                vehicleTypes.add("Car");
                vehicleTypes.add("Boat");
            }

            List<String> availableTrips = hub.getRelevantVehicles(travelFrom.getText(), travelTo.getText(),
                    vehicleTypes);

            if (!(availableTrips == null)) {
                relevantBookings.getItems().setAll(availableTrips);
            }

        } catch (IllegalArgumentException exception) {
            showAlert(exception.getLocalizedMessage(), "Invalid input");
        } catch (IllegalStateException exception) {
            showAlert(exception.getLocalizedMessage(), "Invalid input");
        }

    }

    private void showAlert(String message, String headerText) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(headerText);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void pressBookButton() {
        String selectedTripString = relevantBookings.getSelectionModel().getSelectedItem();

        try {
            String[] parameters = selectedTripString.split(" \\|\\| ");
            String departure = parameters[0].split(" - ")[0];
            String destination = parameters[0].split(" - ")[1];
            String vehicleType = parameters[1].split(": ")[1];
            int capacity = Integer.parseInt(parameters[2].split(": ")[1]);

            Vehicle bookThisVehicle = new Vehicle(departure, destination, vehicleType, capacity);

            hub.bookVehicle(bookThisVehicle);
            tripPrice.setText("" + bookThisVehicle.getPrice());

            pressSearchButton();

            popUp.setVisible(true);
            popUp.setDisable(false);

        } catch (NullPointerException exception) {
            showAlert("Please select a trip", "Booking invalid");
        } catch (ArrayIndexOutOfBoundsException exception) {
            showAlert("Cannot book this trip", "Booking invalid");
        }

    }

    @FXML
    public void sortByAlphabet() {
        List<String> sortedList = new ArrayList<>();
        sortedList.addAll(relevantBookings.getItems());
        Collections.sort(sortedList);
        relevantBookings.getItems().setAll(sortedList);
    }

    @FXML
    public void sortByCapacity() {
        List<String> sortedList = new ArrayList<>();

        List<Vehicle> listOfVehicles = new ArrayList<>();

        for (String vehicleString : relevantBookings.getItems()) {
            String[] parameters = vehicleString.split(" \\|\\| ");
            String departure = parameters[0].split(" - ")[0];
            String destination = parameters[0].split(" - ")[1];
            String vehicleType = parameters[1].split(": ")[1];
            int capacity = Integer.parseInt(parameters[2].split(": ")[1]);

            Vehicle vehicle = new Vehicle(departure, destination, vehicleType, capacity);
            listOfVehicles.add(vehicle);
        }

        Collections.sort(listOfVehicles);
        Collections.reverse(listOfVehicles);

        for (Vehicle vehicle : listOfVehicles) {
            String vehicleAsString = vehicle.getDeparture() + " - " + vehicle.getDestination() + " || Vehicle Type: "
                    + vehicle.getVehicleType() + " || Capacity: " + vehicle.getCapacity();
            sortedList.add(vehicleAsString);
        }

        relevantBookings.getItems().setAll(sortedList);
    }

    @FXML
    public void setPopUpVisibilityFalse() {
        popUp.setVisible(false);
    }
}
