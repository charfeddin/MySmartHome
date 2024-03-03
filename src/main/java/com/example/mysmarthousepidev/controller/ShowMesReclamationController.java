/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.reclamationprojet.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import tn.esprit.reclamationprojet.entities.Reclamation;
import tn.esprit.reclamationprojet.services.ReclamationService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;



public class ShowMesReclamationController implements Initializable {

    @FXML
    private ListView<Reclamation> listView;

    ObservableList<Reclamation> data;

    public static int idE ;

    ReclamationService rs = new ReclamationService();


    @FXML
    private Label label;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


            ObservableList<Reclamation> observableList = FXCollections.observableList(rs.getMesclamation(1));
            data = (ObservableList<Reclamation>) observableList;
            listView.setItems(data);
            listView.setCellFactory((ListView<Reclamation> param) -> new ListViewReclamation());

        // TODO
    }




    @FXML
    private void details(ActionEvent event) throws SQLException {

        ObservableList<Reclamation> e = listView.getSelectionModel().getSelectedItems();
        for (Reclamation r : e) {
            idE = r.getIdrec();
        }

        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/tn/esprit/reclamationprojet/DetailsClientReclamation.fxml")));
            Stage myWindow = (Stage) listView.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("page name");
            //myWindow.setFullScreen(true);
            myWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void Supprimer(ActionEvent event) throws SQLDataException {

        ObservableList<Reclamation> e = listView.getSelectionModel().getSelectedItems();
        for (Reclamation r : e) {
            idE = r.getIdrec();
        }
        System.out.println("Reclamation number"+idE);
        rs.deleteReclamation(idE);

        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/tn/esprit/reclamationprojet/ShowMesReclamation.fxml")));
            Stage myWindow = (Stage) listView.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("page name");
            //myWindow.setFullScreen(true);
            myWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

        








    }

    

