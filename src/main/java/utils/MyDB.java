package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDB {
    private Connection cnx;
    private static MyDB instance;
    private final String USER = "root";
    private final String PWD = "";
    private final String URL = "jdbc:mysql://localhost:3306/projet";

    private MyDB() {
        try {
            cnx = DriverManager.getConnection(URL, USER, PWD);
            System.out.println("Connected !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static MyDB getInstance() {
        if(instance == null)
            instance = new MyDB();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }

}
