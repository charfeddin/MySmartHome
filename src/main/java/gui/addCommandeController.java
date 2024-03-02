package gui;

import entities.Commande;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import services.ServiceCommande;
import utils.MyDB;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class addCommandeController implements Initializable {

    @FXML
    private AnchorPane addCommandePane;

    @FXML
    private Button btnAddCommande;

    @FXML
    private Button btnClearCommande;

    @FXML
    private TextArea txtDescriptionCommande;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void AjoutCommande(ActionEvent event) {
        //check if not empty
        if(event.getSource() == btnAddCommande){
            if (txtDescriptionCommande.getText().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information manquante");
                alert.setHeaderText(null);
                alert.setContentText("Vous devez remplir tous les détails concernant votre Commande.");
                Optional<ButtonType> option = alert.showAndWait();

            } else {
                ajouterCommande();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ajouté avec succès");
                alert.setHeaderText(null);
                alert.setContentText("Votre Commande a été ajoutée avec succès.");
                Optional<ButtonType> option = alert.showAndWait();

                clearFieldsCommande();
            }
        }
        if(event.getSource() == btnClearCommande){
            clearFieldsCommande();
        }
    }

    @FXML
    void clearFieldsCommande() {
        txtDescriptionCommande.clear();
    }

    private void ajouterCommande() {

        // From Formulaire
        String statut = "En Attente";
        Date dateCmd = null;
        try {
            LocalDate currentDate = LocalDate.now();
            ZonedDateTime zonedDateTime = currentDate.atStartOfDay(ZoneId.systemDefault());
            Instant instant = zonedDateTime.toInstant();
            dateCmd = Date.from(instant);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String description = txtDescriptionCommande.getText();
        int idProd = 1;
        int idUser = 1;


        MyDB db = MyDB.getInstance();
        Commande cab = new Commande(
                statut, dateCmd, description, idProd, idUser);
        ServiceCommande as = new ServiceCommande();
        as.ajouter(cab);
    }
}
