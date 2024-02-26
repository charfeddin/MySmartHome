package controllers;

import entities.Pack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import services.ServicePack;
import utils.MyDB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModifierPackController {

    private ServicePack servicePack = new ServicePack();
    private Connection con;

    public ModifierPackController() {
        con = MyDB.getInstance().getConnection(); // Initialiser l'objet Connection
    }

    @FXML
    private TextField tf_id; // Champ de texte pour l'ID du pack à modifier

    @FXML
    private TextField tf_newDescription;

    @FXML
    private TextField tf_newPrice;

    @FXML
    private TextField tf_newAvailability;

    @FXML
    private ComboBox<String> cb_idCategorie;
    @FXML
    private Pack selectedPack; // Déclaration de la variable selectedPack


    @FXML
    void initialize() {
        try {
            // Récupérer les noms des équipements depuis la base de données
            List<String> nomsCategorie = fetchIdCategorietByName();
            // Ajouter les noms à la ComboBox
            cb_idCategorie.getItems().addAll(nomsCategorie);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Méthode pour initialiser les données du pack sélectionné
    @FXML
    public void initPack(Pack pack) {
        selectedPack = pack;
        // Pré-remplir les champs avec les données du pack sélectionné

        tf_id.setText(String.valueOf(pack.getId()));
        tf_newDescription.setText(pack.getDescription());
        tf_newPrice.setText(String.valueOf(pack.getPrix()));
        tf_newAvailability.setText(pack.getDispo());
    }

    @FXML
    void ModifierPack(ActionEvent event) {
        try {
            int id = Integer.parseInt(tf_id.getText()); // Récupérer l'ID entré par l'utilisateur
            String newDescription = tf_newDescription.getText();
            float newPrice = Float.parseFloat(tf_newPrice.getText());
            String newAvailability = tf_newAvailability.getText();

            // Récupérer l'ID de la catégorie sélectionnée dans la ComboBox
            String nomCategorie = cb_idCategorie.getValue();
            int idCategorie = servicePack.fetchIdCategorietByName(nomCategorie); // Récupérer l'ID de la catégorie

            // Récupérer l'ID de l'équipement existant dans la base de données pour ce pack
            int idEquipement = servicePack.fetchIdEquipementById(id); // Remplacez cette ligne par la méthode appropriée pour récupérer l'ID de l'équipement

            // Utiliser l'ID de la catégorie pour modifier le pack
            servicePack.modifier(new Pack(id, idEquipement, idCategorie, newDescription, newPrice, newAvailability));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setContentText("Le pack a été modifié avec succès.");
            alert.showAndWait();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez saisir des valeurs valides.");
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Une erreur est survenue lors de la modification du pack : " + e.getMessage());
            alert.showAndWait();
        }
    }


    @FXML
    void retournerAvoirAjouterPack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherPack.fxml"));
            tf_id.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private List<String> fetchNomsEquipementsFromDatabase() throws SQLException {
        List<String> nomsEquipements = new ArrayList<>();
        String query = "SELECT nom_equipement FROM equipement"; // Ajustez la requête en fonction de votre schéma de base de données
        try (PreparedStatement preparedStatement = con.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                nomsEquipements.add(resultSet.getString("nom_equipement"));
            }
        }
        return nomsEquipements;
    }
    private List<String> fetchIdCategorietByName() throws SQLException {
        List<String> nomsCategorie = new ArrayList<>();
        String query = "SELECT nom_categorie FROM categorie"; // Ajustez la requête en fonction de  schéma de base de données
        try (PreparedStatement preparedStatement = con.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                nomsCategorie.add(resultSet.getString("nom_categorie"));
            }
        }
        return nomsCategorie;
    }
}
