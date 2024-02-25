package com.example.mysmarthousepidev.controller;

import com.example.mysmarthousepidev.entities.Equipement;
import com.example.mysmarthousepidev.entities.Home;
import com.example.mysmarthousepidev.services.ServiceEquipement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.mysmarthousepidev.controller.AfficherHome.idhome;

public class AfficherEquipement implements Initializable {

    @FXML
    private TableColumn<Equipement, String> des;

    @FXML
    private TableColumn<Equipement, String> etat;

    @FXML
    private TableColumn<Equipement, String> home;

    @FXML
    private TableColumn<Equipement, Integer> nbr;

    @FXML
    private TableColumn<Equipement, String> nom;

    @FXML
    private TableView<Equipement> table;
    public static int idequip = 0;
    ServiceEquipement serviceEquipement = new ServiceEquipement();


    private final ObservableList<Equipement> equipData = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Equipement, Float> temp;

    @FXML
    void Ajouter(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AjouterEquipement.fxml")));
            Stage myWindow = (Stage) table.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Login");
            myWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void modifier(ActionEvent event) {
        idequip = table.getSelectionModel().getSelectedItem().getId_equipement();
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ModifierEquipement.fxml")));
            Stage myWindow = (Stage) table.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Login");
            myWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void supprimer(ActionEvent event) throws SQLException {

        int id =  table.getSelectionModel().getSelectedItem().getId_equipement();
        serviceEquipement.supprimer(id);
        resetTableData();

    }

    private void resetTableData() throws SQLException {
        List<Equipement> lisre = new ArrayList<>();
        lisre = serviceEquipement.afficherByHome(idhome);
        ObservableList<Equipement> data = FXCollections.observableArrayList(lisre);
        table.setItems(data);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        List<Equipement> listb ;
        try {
            listb = serviceEquipement.afficherByHome(idhome);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        equipData.clear();
        equipData.addAll(listb);
        table.setItems(equipData);


        nom.setCellValueFactory
                (
                        new PropertyValueFactory<>("nom")
                );
        nbr.setCellValueFactory
                (
                        new PropertyValueFactory<>("nombre")
                );
        des.setCellValueFactory
                (
                        new PropertyValueFactory<>("description")
                );
        etat.setCellValueFactory
                (
                        new PropertyValueFactory<>("etat")
                );

        temp.setCellValueFactory
                (
                        new PropertyValueFactory<>("temper")
                );
        home.setCellValueFactory(cellData -> {
            Equipement equipement = cellData.getValue();
            Home home = null; // Assurez-vous que cette méthode existe
            try {
                home = serviceEquipement.getHomeById(equipement.getId_home().getId_home());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return new SimpleStringProperty(home.getDesigantion()); // Accédez à l'attribut "nom" de l'objet Home
        });
    }
}
