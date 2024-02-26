package controllers;


import entities.post;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ServicePost;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ajouterPostControllers {

    ServicePost ServicePost = new ServicePost();

    @FXML
    private TextField tf_commentaire;

    @FXML
    private ComboBox<String> cb_idCommentaire;

    @FXML
    private TextField tf_date;

    @FXML
    private TextField tf_titre;

    @FXML
    void initialize() {
        try {
            List<String> contenu = ServicePost.fetchcontenuFromDatabase();
            // Add the names to the ComboBox
            cb_idCommentaire.getItems().addAll(contenu);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void afficherPost(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherPost.fxml"));
            tf_titre.getScene().setRoot(root);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void SupprimerPost(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/supprimerPost.fxml"));
            tf_titre.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ModifierPost(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ModifierPost.fxml"));
            tf_titre.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ajouterPost(ActionEvent event) {

        try {
            // Récupérer le nom de la catégorie sélectionnée dans le ComboBox
            String contenu = cb_idCommentaire.getValue();
            // Récupérer l'ID de la catégorie à partir de son nom
            int idCommentaire = ServicePost.fetchIdCommentairetByName(contenu);
// Modifiez cette ligne dans votre méthode ajouterPost de la classe ajouterPostControllers
            ServicePost.ajouter(new post(idCommentaire, tf_titre.getText(), tf_commentaire.getText(), tf_date.getText()));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("success");
            alert.setContentText("post ajoute");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            throw new RuntimeException(e);
        }


    }


    @FXML
    private void goToAjoutercommentaire(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ajoutercommentaire.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

