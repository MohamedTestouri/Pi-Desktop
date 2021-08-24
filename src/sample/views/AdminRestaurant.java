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
import sample.controllers.RestaurantService;
import sample.entities.Restaurant;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminRestaurant implements Initializable {
    @FXML
    TextField specialiteTextField;
    @FXML
    TextField nomTextField;
    @FXML
    TextField placeTextField;
    @FXML
    Button addButton;
    @FXML
    Button editButton;
    @FXML
    Button deleteButton;
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

    RestaurantService restaurantService = new RestaurantService();
Restaurant restaurant = new Restaurant();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showRestaurant();
        restaurantTable.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                restaurant = restaurantTable.getSelectionModel().getSelectedItem();
                System.out.println(restaurant.getId());
                nomTextField.setText(restaurant.getNom());
                specialiteTextField.setText(restaurant.getSpecialite());
                placeTextField.setText(restaurant.getPlaces()+"");
            }
        });
        addButton.setOnAction(e -> {
            addRestaurant();
        });
        editButton.setOnAction(e-> {
            editRestaurant(restaurant);
        });
        deleteButton.setOnAction(e->{
            restaurantService.deleteRestaurant(restaurant.getId());
            refresh();
        });
    }

    public void showRestaurant() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        specialiteColumn.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        placeColumn.setCellValueFactory(new PropertyValueFactory<>("places"));
        restaurantTable.setItems(restaurantService.showAllRestaurant());
    }

    public void addRestaurant() {
        Restaurant restaurant = new Restaurant(nomTextField.getText(), specialiteTextField.getText(), Integer.parseInt(placeTextField.getText()));
        restaurantService.addRestaurant(restaurant);
        refresh();
    }

    public void editRestaurant(Restaurant restaurant) {
        restaurant.setNom(nomTextField.getText());
        restaurant.setSpecialite(specialiteTextField.getText());
        restaurant.setPlaces(Integer.parseInt(placeTextField.getText()));
        System.out.println(restaurant.getPlaces());
        restaurantService.updateRestaurant(restaurant);
        refresh();
    }
    public void refresh(){
        showRestaurant();
        nomTextField.clear();
        specialiteTextField.clear();
        placeTextField.clear();
    }
}
