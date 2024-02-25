package com.example.mysmarthousepidev.controller;

import com.example.mysmarthousepidev.entities.Home;
import com.example.mysmarthousepidev.services.ServiceHome;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AfficherHome implements Initializable{

    @FXML
    private TableColumn<Home, String> Designation;

    @FXML
    private TableColumn<Home, String> adress;

    @FXML
    private TableColumn<Home, String> disponibilite;



    @FXML
    private TableView<Home> table;

    ServiceHome serviceHome = new ServiceHome() ;

    public static int idhome = 0;

    private final ObservableList<Home> HomeData = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Home> listb ;
        listb = serviceHome.afficher();
        HomeData.clear();
        HomeData.addAll(listb);
        table.setItems(HomeData);


        disponibilite.setCellValueFactory
                (
                        new PropertyValueFactory<>("disponibilite")
                );
        Designation.setCellValueFactory
                (
                        new PropertyValueFactory<>("desigantion")
                );
        adress.setCellValueFactory
                (
                        new PropertyValueFactory<>("adreess")
                );

    }

    @FXML
    void modifier(ActionEvent event) {
       idhome= table.getSelectionModel().getSelectedItem().getId_home();
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ModifierHome.fxml")));
            Stage myWindow = (Stage) table.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Login");
            //myWindow.setFullScreen(true);
            myWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void supprimer(ActionEvent event) {

        int id =  table.getSelectionModel().getSelectedItem().getId_home();
        serviceHome.supprimer(id);
        resetTableData();

    }

    private void resetTableData() {
        List<Home> lisre = new ArrayList<>();
        lisre = serviceHome.afficher();
        ObservableList<Home> data = FXCollections.observableArrayList(lisre);
        table.setItems(data);
    }
    @FXML
    void Ajouter(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AjouterHome.fxml")));
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
    void equipement(ActionEvent event) {
        idhome= table.getSelectionModel().getSelectedItem().getId_home();
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AfficherEquipement.fxml")));
            Stage myWindow = (Stage) table.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Login");
            //myWindow.setFullScreen(true);
            myWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
