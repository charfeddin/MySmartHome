package gui;

import entities.Commande;
import entities.Livraison;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.ServiceCommande;
import services.ServiceLivraison;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class itemLivraisonController implements Initializable {

    @FXML
    private Button btnModifierLivraison;

    @FXML
    private Button btnSupprimerLivraison;

    @FXML
    private AnchorPane itemLivraisonPane;

    @FXML
    private Label labelAdresseLivraison;

    @FXML
    private Label labelCmdLivraison;

    @FXML
    private Label labelPrixLivraison;


    Livraison liv;
    public void setData (Livraison livraison){
        this.liv = livraison ;

        labelPrixLivraison.setText(String.valueOf(livraison.getPrix()));
        labelAdresseLivraison.setText(livraison.getAdresse());
        labelCmdLivraison.setText(String.valueOf(livraison.getIdCommande()));

    }


    public Livraison getData (Livraison livraison){
        this.liv = livraison ;
        return this.liv;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void open_UpdateLivraison(ActionEvent event) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("updateLivraison.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Update Livraison");
        stage.setScene(new Scene(fxml));
        stage.showAndWait();
    }

    @FXML
    void supprimerLivraison(ActionEvent event) throws SQLException {
        ServiceLivraison sl = new ServiceLivraison();

        // Afficher une boîte de dialogue de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer cette livraison ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Récupérer l'ID de la livraison sélectionnée
            int id = this.liv.getId();

            // Supprimer la livraison de la base de données
            sl.supprimer(id);
        }
    }
}
