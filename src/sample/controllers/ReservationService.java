package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.entities.Reservation;
import sample.utils.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservationService {
    Database database = new Database();

    public void addReservation(Reservation reservation) {
        RestaurantService restaurantService = new RestaurantService();
        String queryReservation = "INSERT INTO reservation ( idRestaurant, idUser, places) VALUES (?,?,?)";
        try {
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(queryReservation);
            preparedStatement.setInt(1, reservation.getIdRestaurant());
            preparedStatement.setInt(2, reservation.getIdUser());
            preparedStatement.setInt(3, reservation.getPlaces());
            preparedStatement.executeUpdate();
            restaurantService.updateRestaurantPlaces(reservation.getIdRestaurant(), reservation.getPlaces());
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ObservableList<Reservation> showReservationByUser(int idUser) {
        String query = "SELECT * FROM reservation WHERE idUser=?";
        ObservableList<Reservation> reservationArrayList = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idRestaurant = resultSet.getInt("idRestaurant");
                int places = resultSet.getInt("places");
                Reservation reservation = new Reservation(id, idRestaurant, idUser, places);
                reservationArrayList.add(reservation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reservationArrayList;
    }

    public ObservableList<Reservation> showReservation() {
        String query = "SELECT * FROM reservation";
        ObservableList<Reservation> reservationArrayList = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idUser = resultSet.getInt("idUser");
                int idRestaurant = resultSet.getInt("idRestaurant");
                int places = resultSet.getInt("places");
                Reservation reservation = new Reservation(id, idRestaurant, idUser, places);
                reservationArrayList.add(reservation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reservationArrayList;
    }
}
