package controllers;

import entities.post;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import services.ServicePost;

import java.io.IOException;
import java.sql.SQLException;

public class ModifierPostController {

    private ServicePost servicePost = new ServicePost();

    @FXML
    private TextField tf_id; // Champ de texte pour l'ID du post à modifier

    @FXML
    private TextField tf_titre;

    @FXML
    private TextField tf_commentaire;

    @FXML
    private TextField tf_date;

    @FXML
    private post selectedpost; // Déclaration de la variable selectedPost


    private int id_post; // Declare the id_post variable here

    @FXML
    void modifierPost(ActionEvent event) {
        try {
            int id_post = Integer.parseInt(tf_id.getText()); // Récupérer l'ID entré par l'utilisateur
            String newtitle = tf_titre.getText();
            String newcomment = tf_commentaire.getText();
            String newdate = tf_date.getText();

            servicePost.modifier(new post(id_post, newtitle, newcomment, newdate));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setContentText("Le post a été modifié avec succès.");
            alert.showAndWait();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("erreur.");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Une erreur  : " + e.getMessage());
            alert.showAndWait();
        }
    }

}