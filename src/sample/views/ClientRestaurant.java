package sample.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import sample.controllers.ReservationService;
import sample.controllers.RestaurantService;
import sample.entities.Reservation;
import sample.entities.Restaurant;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientRestaurant implements Initializable {
    @FXML
    private TableView<Restaurant> restaurantTable;
    @FXML
    private TableColumn<Restaurant, String> idColumn;
    @FXML
    private TableColumn<Restaurant, String> nomColumn;
    @FXML
    private TableColumn<Restaurant, String> specialiteColumn;
    @FXML
    private TableColumn<Restaurant, String> placeColumn;
    @FXML
    private TextField placeTextField;
    @FXML
    private Button reserverButton;

    RestaurantService restaurantService = new RestaurantService();
    ReservationService reservationService = new ReservationService();
    Restaurant restaurant = new Restaurant();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showRestaurant();
        reserverButton.setDisable(true);
        placeTextField.setDisable(true);
        restaurantTable.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                restaurant = restaurantTable.getSelectionModel().getSelectedItem();
                if (restaurant.getPlaces() > 0) {
                    placeTextField.setDisable(false);
                    reserverButton.setDisable(false);
                }
            }
        });
        reserverButton.setOnAction(e->{
            reserver(restaurant.getId());
        });
    }

    private void reserver(int id) {
        Reservation reservation = new Reservation(id, 10, Integer.parseInt(placeTextField.getText()));
        reservationService.addReservation(reservation);
        refresh();
    }

    public void showRestaurant() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        specialiteColumn.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        placeColumn.setCellValueFactory(new PropertyValueFactory<>("places"));
        restaurantTable.setItems(restaurantService.showAllRestaurant());
    }

    public void refresh() {
        showRestaurant();
        placeTextField.clear();
        placeTextField.setDisable(true);
        reserverButton.setDisable(true);
    }

}
