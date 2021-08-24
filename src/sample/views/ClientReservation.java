package sample.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.controllers.ReservationService;
import sample.entities.Reservation;
import sample.entities.Restaurant;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientReservation implements Initializable {
    @FXML
    private TableView<Reservation> reservationTable;
    @FXML
    private TableColumn<Reservation, String> idColumn;
    @FXML
    private TableColumn<Reservation, String> userColumn;
    @FXML
    private TableColumn<Reservation, String> restaurantColumn;
    @FXML
    private TableColumn<Reservation, String> placeColumn;

    ReservationService reservationService = new ReservationService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showReservation();
    }

    public void showReservation() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        userColumn.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        restaurantColumn.setCellValueFactory(new PropertyValueFactory<>("idRestaurant"));
        placeColumn.setCellValueFactory(new PropertyValueFactory<>("places"));
        reservationTable.setItems(reservationService.showReservationByUser(10));
    }
}
