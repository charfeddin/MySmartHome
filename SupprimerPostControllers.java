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

public class SupprimerPostControllers {

    private ServicePost servicePost = new ServicePost();

    @FXML
    private TextField tf_id;

    @FXML
    void SupprimerPost(ActionEvent event) {
        try {
            int id = Integer.parseInt(tf_id.getText());
            post post = new post();
            post.setId_post(id);
            servicePost.supprimer(post);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setContentText("Le  supprimé avec succès.");
            alert.showAndWait();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez saisir un ID valide.");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Une erreur est survenue lors de la suppression du post : " + e.getMessage());
            alert.showAndWait();
        }
    }


}