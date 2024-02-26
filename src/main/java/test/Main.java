package test;
import services.ServicePack;

import entities.Pack;

import utils.MyDB;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        MyDB conn1 = MyDB.getInstance();

        Pack pack1 = new Pack(1, 0,0, "Description 1", 10.5f, "Available");
        Pack pack2 = new Pack(2, 1, 1,"Description 2", 20.7f, "Not available");
        Pack pack3 = new Pack(3, 2,2, "Description 3", 15.3f, "Available");

        ServicePack servicePack = new ServicePack();
        try {
            servicePack.ajouter(pack1);
            servicePack.ajouter(pack2);
            servicePack.ajouter(pack3);
        } catch (SQLException e) {
            System.out.println("Error adding packs: " + e.getMessage());
        }

        try {
            System.out.println(servicePack.afficher());
        } catch (SQLException e) {
            throw new RuntimeException("Error displaying packs: " + e.getMessage());
        }

        Pack updatedPack = new Pack(31, 1,4 ,"Updated Description", 25.0f, "Available");
        try {
            servicePack.modifier(updatedPack);
        } catch (SQLException e) {
            System.out.println("Error updating pack: " + e.getMessage());
        }
    }
}
