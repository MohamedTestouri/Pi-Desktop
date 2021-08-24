package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.entities.Reservation;
import sample.entities.Restaurant;
import sample.utils.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RestaurantService {
    Database database = new Database();

    public void addRestaurant(Restaurant restaurant) {
        String query = "INSERT INTO restaurant (nom, specialite, places) VALUES (?,?,?)";
        try {
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
            preparedStatement.setString(1, restaurant.getNom());
            preparedStatement.setString(2, restaurant.getSpecialite());
            preparedStatement.setInt(3, restaurant.getPlaces());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RestaurantService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateRestaurant(Restaurant restaurant) {
        String query = "UPDATE restaurant SET nom=? , specialite=? , places=? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
            preparedStatement.setString(1, restaurant.getNom());
            preparedStatement.setString(2, restaurant.getSpecialite());
            preparedStatement.setInt(3, restaurant.getPlaces());
            preparedStatement.setInt(4, restaurant.getId());
            preparedStatement.executeUpdate();
            System.out.println("Update Done");
        } catch (SQLException ex) {
            Logger.getLogger(RestaurantService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateRestaurantPlaces(int idRestaurant, int places) {
      places =  showRestaurantById(idRestaurant).get(0).getPlaces() - places;
        String query = "UPDATE restaurant SET places=? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, places);
            preparedStatement.setInt(2, idRestaurant);
            preparedStatement.executeUpdate();
            System.out.println("Update Done");
        } catch (SQLException ex) {
            Logger.getLogger(RestaurantService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteRestaurant(int id) {
        String query = "DELETE FROM restaurant WHERE id = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Delete Done");
        } catch (SQLException ex) {
            Logger.getLogger(RestaurantService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<Restaurant> showRestaurantById(int id) {
        String query = "SELECT * FROM restaurant WHERE id=?";
        ObservableList<Restaurant> restaurantArrayList = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String specialite = resultSet.getString("specialite");
                int places = resultSet.getInt("places");
                Restaurant restaurant = new Restaurant(id, nom, specialite, places);
                restaurantArrayList.add(restaurant);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RestaurantService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return restaurantArrayList;
    }

    public ObservableList<Restaurant> showAllRestaurant() {
        String query = "SELECT * FROM restaurant";
        ObservableList<Restaurant> restaurantArrayList = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String specialite = resultSet.getString("specialite");
                int places = resultSet.getInt("places");
                Restaurant restaurant = new Restaurant(id, nom, specialite, places);
                restaurantArrayList.add(restaurant);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RestaurantService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return restaurantArrayList;
    }

}
