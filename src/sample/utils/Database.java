package sample.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mohamed Testouri
 */
public class Database {
    public String url ="jdbc:mysql://localhost:3306/pi";
    public String login="root";
    public String pwd ="";
    public Connection connection;
    public static Database db;

    public Database() {
        try {
            connection = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connection etablie");
        } catch (SQLException ex) {
            System.out.println("Problème de connection");
            System.out.println(ex.getMessage());
        }
    }
    public Connection getConnection(){
        return connection;
    }
    public static Database getInstance(){
        if(db == null)
            db = new Database();
        return db;
    }
}
