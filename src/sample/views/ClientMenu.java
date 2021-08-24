package sample.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenu implements Initializable {
    @FXML
    private AnchorPane loadAnchorPane;
    @FXML
    private Button restaurantButton;
    @FXML
    private Button reservationButton;
    @FXML
    private Button adminButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadAnchorPane.getChildren().add((Node)FXMLLoader.load(getClass().getResource("ClientRestaurant.fxml")));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        adminButton.setOnAction(e -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminMenu.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setTitle("Admin");
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        restaurantButton.setOnAction(e->{
            try {
                loadAnchorPane.getChildren().clear();
                loadAnchorPane.getChildren().add((Node)FXMLLoader.load(getClass().getResource("ClientRestaurant.fxml")));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        reservationButton.setOnAction(e->{
            try {
                loadAnchorPane.getChildren().clear();
                loadAnchorPane.getChildren().add((Node)FXMLLoader.load(getClass().getResource("ClientReservation.fxml")));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }
}
