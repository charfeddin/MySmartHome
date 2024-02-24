package com.example.mysmarthousepidev.test;

import com.example.mysmarthousepidev.entities.Equipement;
import com.example.mysmarthousepidev.entities.Home;
import com.example.mysmarthousepidev.services.ServiceEquipement;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        ServiceEquipement s = new ServiceEquipement();


        Home h1 = new Home(1,1,"bon","disponible",5);
        Home h2 = new Home(1,1,"bon","disponible",5);
        Home h3 = new Home(1,1,"bon","disponible",5);
        Equipement e1= new Equipement(1,"tv","bon","bon",22,h1,30);
        Equipement e2= new Equipement(1,"tv","bon","bon",22,h2,30);
        Equipement e3= new Equipement(1,"tv","bon","bon",22,h3,30);

            s.ajouter(e1);


        try {
            System.out.println(s.afficher());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            s.modifier(e3);
        } catch (SQLException e) {
        }


    }
}
