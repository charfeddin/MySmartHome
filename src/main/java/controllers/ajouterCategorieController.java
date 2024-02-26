package controllers;

import entities.Categorie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.ServiceCategorie;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public class ajouterCategorieController {

    @FXML
    private TextField txtNomCategorie;
    @FXML
    private ImageView img_categorie;

    private ServiceCategorie serviceCategorie;

    @FXML
    private Button btnAjouter;

    public ajouterCategorieController() {
        this.serviceCategorie = new ServiceCategorie();
    }

    @FXML
    void ajouterCategorie(ActionEvent event) {
        String nomCategorie = txtNomCategorie.getText();
        if (!nomCategorie.isEmpty()) {
            Categorie categorie = new Categorie();
            categorie.setNom_categorie(nomCategorie);
            try {
                serviceCategorie.ajouter(categorie);
                System.out.println("Catégorie ajoutée avec succès !");
            } catch (SQLException e) {
                System.err.println("Erreur lors de l'ajout de la catégorie : " + e.getMessage());
            }
        } else {
            System.err.println("Veuillez saisir un nom de catégorie !");
        }
    }

    @FXML
    private void goToAjoutPack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AjouterPack.fxml"));
            ((Node)(event.getSource())).getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void afficherCategories(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherCategorie.fxml"));
            ((Node)(event.getSource())).getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
