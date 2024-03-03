package controllers;

import entities.Pack;
import entities.notif;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.stage.Stage;
import services.ServicePack;
import services.notifService;
import utils.MyDB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ajouterPackController {
    ServicePack servicePack = new ServicePack();
    private Connection con;
    public ajouterPackController() {
        con = MyDB.getInstance().getConnection(); // Initialize the Connection object
    }

    @FXML
    private TextField tf_description;

    @FXML
    private TextField tf_despo;

    @FXML
    private TextField tf_prix;

    @FXML
    private ListView<String> lv_idEquipement;
    @FXML
    private ComboBox<String> cb_idCategories;
    @FXML
    notifService notifService = new notifService();
    @FXML
    void initialize() {
        try {
            // Fetch the names of equipments from the database
            List<String> nomEquipements = servicePack.fetchNomEquipementsFromDatabase();
            // Add the names to the ListView
            lv_idEquipement.getItems().addAll(nomEquipements);
            // Allow multiple selection
            lv_idEquipement.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            // Fetch the names of categories from the database
            List<String> nomCategories = servicePack.fetchNomCategoriesFromDatabase();
            // Add the names to the ComboBox
            cb_idCategories.getItems().addAll(nomCategories);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    @FXML
    void AfficherPack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherPack.fxml"));
            tf_description.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void SupprimerPack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/supprimerPack.fxml"));
            tf_description.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ModifierPack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ModifierPack.fxml"));
            tf_description.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void AjouterPack(ActionEvent event) {



        notif n = new notif(-1," Ghassen Marouani has added a new pack ");
        try {
            notifService.ajouter(n);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            // Récupérer le nom de la catégorie sélectionnée dans le ComboBox
            String nomCategorie = cb_idCategories.getValue();
            // Récupérer l'ID de la catégorie à partir de son nom
            int idCategorie = servicePack.fetchIdCategorietByName(nomCategorie);

            // Récupérer les noms des équipements sélectionnés dans la ListView
            ObservableList<String> selectedEquipments = lv_idEquipement.getSelectionModel().getSelectedItems();

            // Utiliser cette valeur pour obtenir les ID des équipements
            List<Integer> selectedIds = new ArrayList<>();
            for (String nomEquipement : selectedEquipments) {
                int idEquipement = servicePack.fetchIdEquipementByName(nomEquipement);
                selectedIds.add(idEquipement);
            }

            // Ajouter le pack pour chaque ID d'équipement sélectionné
            for (int idEquipement : selectedIds) {
                servicePack.ajouter(new Pack(0, idEquipement, idCategorie, tf_description.getText(), Float.parseFloat(tf_prix.getText()), tf_despo.getText()));
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Packs ajoutés avec succès");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            throw new RuntimeException(e);
        }
    }


    private List<Integer> fetchIdEquipementFromDatabase() throws SQLException {
        List<Integer> idEquipement = new ArrayList<>();
        String query = "SELECT id_equipement FROM equipement"; // Adjust query according to your database schema
        try (PreparedStatement preparedStatement = con.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                idEquipement.add(resultSet.getInt("id_equipement"));
            }
        }
        return idEquipement;
    }


    @FXML
    private void goToAjoutCategorie(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AjouterCategorie.fxml"));
            tf_description.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



        @FXML
        void goToNotif(ActionEvent event) {
            try {
                // Load notif.fxml
                Parent root = FXMLLoader.load(getClass().getResource("/notif.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


