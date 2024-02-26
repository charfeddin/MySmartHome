package controllers;

import entities.Pack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import services.ServicePack;

import java.io.IOException;
import java.sql.SQLException;

public class SupprimerPackController {

    private ServicePack servicePack = new ServicePack();

    @FXML
    private TextField tf_id;

    @FXML
    void SupprimerPack(ActionEvent event) {
        try {
            int id = Integer.parseInt(tf_id.getText());
            Pack pack = new Pack();
            pack.setId(id);
            servicePack.supprimer(pack);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setContentText("Le pack a été supprimé avec succès.");
            alert.showAndWait();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez saisir un ID valide.");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Une erreur est survenue lors de la suppression du pack : " + e.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
    void retournerAvoirAjouterPack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AjouterPack.fxml"));
            tf_id.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
