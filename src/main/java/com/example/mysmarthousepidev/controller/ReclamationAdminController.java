/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.reclamationprojet.controller;


import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import org.w3c.dom.Entity;
import tn.esprit.reclamationprojet.entities.Reclamation;
import tn.esprit.reclamationprojet.entities.User;
import tn.esprit.reclamationprojet.services.ReclamationService;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author Rzouga
 */
public class ReclamationAdminController implements Initializable {

    @FXML
    private TableView<Reclamation> table;
    @FXML
    private TableColumn<Reclamation, String> sujet;
    @FXML
    private TableColumn<Reclamation, String> contenue;
    @FXML
    private TableColumn<Reclamation,String> etat;
    @FXML
    private TableColumn<Reclamation, Date> date;
    @FXML
    private TableColumn<Reclamation, String> user;

    @FXML
    private TextField rech;

    private ObservableList<Reclamation> reserData = FXCollections.observableArrayList();
    
    ReclamationService rs = new ReclamationService();
        public static int idRd;
    @FXML
    private Button back115;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                   List<Reclamation> listReser= new ArrayList<Reclamation>();
                   listReser = rs.Allreclamation();
                   reserData.clear();
                   reserData.addAll(listReser);
                   table.setItems(reserData);
                   sujet.setCellValueFactory
                        (
                                new PropertyValueFactory<>("sujet")
                        );
                   contenue.setCellValueFactory
                        (
                                new PropertyValueFactory<>("contenue")
                        );
                   etat.setCellValueFactory
                        (
                                new PropertyValueFactory<>("etat")
                        );
                   date.setCellValueFactory
                        (
                                new PropertyValueFactory<>("dateenv")
                        );
        user.setCellValueFactory(cellData -> {
            Reclamation reclamation = cellData.getValue();
            User user = null; // Assurez-vous que cette méthode existe
            user = rs.findUserById(reclamation.getIdUser());
            return new SimpleStringProperty(user.getNom()); // Accédez à l'attribut "nom" de l'objet Home
        });
                   
    }    

    @FXML
    private void Traiter(ActionEvent event) throws SQLDataException, Exception {

      //  entities.mail.envoi(email.getText(), "Nouveau Password", " Votre Noveaux Password est :"+newMdp);

        Reclamation userSelec = table.getSelectionModel().getSelectedItem();
               idRd = userSelec.getIdrec();
               Parent root;
                        try {
                            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/tn/esprit/reclamationprojet/DetailsReclamation.fxml")));
                            Stage myWindow = (Stage) table.getScene().getWindow();
                            Scene sc = new Scene(root);
                            myWindow.setScene(sc);
                            myWindow.setTitle("Details");
                            //myWindow.setFullScreen(true);
                            myWindow.show();
                        } catch (IOException ex) {
                            Logger.getLogger(Reclamation.class.getName()).log(Level.SEVERE, null, ex);
                        }
 

    }

    @FXML
    private void back115(ActionEvent event)throws IOException{
        
        try {
          Parent  root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("")));
            Stage myWindow = (Stage) back115.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("back ");
            //myWindow.setFullScreen(true);
            myWindow.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void rechercher(ActionEvent event) {
        sujet.setCellValueFactory
                (
                        new PropertyValueFactory<>("sujet")
                );
        contenue.setCellValueFactory
                (
                        new PropertyValueFactory<>("contenue")
                );
        etat.setCellValueFactory
                (
                        new PropertyValueFactory<>("etat")
                );
        date.setCellValueFactory
                (
                        new PropertyValueFactory<>("dateenv")
                );



        List<Reclamation> list = rs.Allreclamation();;

        //tableview.setItems(observablelist);

        FilteredList<Reclamation> filtredData= new FilteredList<>(reserData, b ->true);
        rech.textProperty().addListener((observable,oldValue,newValue) -> {
            Predicate<? super Reclamation> reclamation;
            filtredData.setPredicate((Reclamation user) -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                if( user.getSujet().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                else if (user.getContenue().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                else
                    return false;
            } );
        });
        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Reclamation> sortedData = new SortedList<>(filtredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);

    }





}
