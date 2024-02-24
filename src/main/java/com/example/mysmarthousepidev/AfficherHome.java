package com.example.mysmarthousepidev;

import com.example.mysmarthousepidev.entities.Home;
import com.example.mysmarthousepidev.services.ServiceHome;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;

public class AfficherHome implements Initializable{

    @FXML
    private TableColumn<Home, String> dispo;

    @FXML
    private TableColumn<Home, String> etat;

    @FXML
    private TableColumn<Home, Integer> qt;

    @FXML
    private TableView<Home> table;

    ServiceHome serviceHome = new ServiceHome() ;

    private ObservableList<Home> HomeData = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Home> listb ;
        listb = serviceHome.afficher();
        HomeData.clear();
        HomeData.addAll(listb);
        table.setItems(HomeData);


        dispo.setCellValueFactory
                (
                        new PropertyValueFactory<>("disponibilite")
                );
        etat.setCellValueFactory
                (
                        new PropertyValueFactory<>("etat")
                );
        qt.setCellValueFactory
                (
                        new PropertyValueFactory<>("quantite")
                );

    }


}
