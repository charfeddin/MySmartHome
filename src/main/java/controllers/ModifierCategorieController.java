package controllers;

import entities.Categorie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ServiceCategorie;

import java.io.IOException;
import java.sql.SQLException;

public class ModifierCategorieController {

    @FXML
    private TextField txtNomCategorie;

    private Categorie categorie;
    private ServiceCategorie serviceCategorie = new ServiceCategorie();

    public void initData(Categorie categorie) {
        this.categorie = categorie;
        txtNomCategorie.setText(categorie.getNom_categorie());
    }

    @FXML
    void modifierCategorie() {
        String nouveauNom = txtNomCategorie.getText().trim();
        if (nouveauNom.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Le nom de la catégorie ne peut pas être vide.");
            return;
        }
        categorie.setNom_categorie(nouveauNom);
        try {
            serviceCategorie.modifier(categorie);
            showAlert(Alert.AlertType.INFORMATION, "Modification réussie", "La catégorie a été modifiée avec succès.");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur de modification", "Une erreur est survenue lors de la modification de la catégorie : " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    void retournerAAfficherCategorie(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherCategorie.fxml"));
            txtNomCategorie.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}

