package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDB {

    public final String URL = "jdbc:mysql://localhost:3306/projet";
    public final String USERNAME = "root";
    public final String PWD = "";

    private static MyDB instance;
    private Connection connection;

    private MyDB() {
        try {
            // Establish the database connection
            connection = DriverManager.getConnection(URL, USERNAME, PWD);
            System.out.println("Connected to database");
        } catch (SQLException e) {
            // Handle connection errors
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }

    public static MyDB getInstance() {
        if (instance == null) {
            instance = new MyDB();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

}
