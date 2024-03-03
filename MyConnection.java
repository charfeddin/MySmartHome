package com.example.rafafx.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    public String url="jdbc:mysql://localhost:3306/Rafa";
    public String login="root";
    public String pwd="";
    private Connection cnx;
    private static MyConnection instance;


    public MyConnection() {
        try {
            cnx= DriverManager.getConnection(url, login, pwd);
            System.out.println("Connected !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    public Connection getCnx() {
        return this.cnx;
    }

    public static MyConnection getInstance() {
        if(instance == null)
            instance = new MyConnection();
        return instance;
    }
}
