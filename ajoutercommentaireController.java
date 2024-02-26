package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import entities.commentaire;
import services.ServiceCommentaire;

import java.io.IOException;
import java.sql.SQLException;

public class ajoutercommentaireController {

    @FXML
    private JFXButton btnAfficher;

    @FXML
    private TextField txtNomCommentaire;

    private ServiceCommentaire serviceCommentaire;

    public ajoutercommentaireController() {
        this.serviceCommentaire = new ServiceCommentaire();
    }

    @FXML
    void ajoutercommentaire(ActionEvent event) {
        String contenu = txtNomCommentaire.getText();
        if (!contenu.isEmpty()) {
            commentaire commentaire = new commentaire();
            commentaire.setContenu(contenu);
            try {
                serviceCommentaire.ajouter(commentaire);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("success");
                alert.setContentText("commenatire  ajouté");
                alert.showAndWait();
                System.out.println("commentaire ajoutée avec succès !");
            } catch (SQLException e) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
                throw new RuntimeException(e);




            }
        } else {
            System.err.println("Veuillez saisir un commentaire !");
        }
    }
    @FXML
    void affichercommentaire(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/affichercommentaire.fxml"));
            ((Node)(event.getSource())).getScene().setRoot(root);

        }catch (IOException e){
e.printStackTrace();        }
    }
}





