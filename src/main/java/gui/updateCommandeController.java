package gui;

import entities.Commande;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import services.ServiceCommande;

import java.io.IOException;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class updateCommandeController implements Initializable {

    @FXML
    private Button btnClearCommande;

    @FXML
    private Button btnUpdateCommande;

    @FXML
    private DatePicker txtUpdateDateCmd;

    @FXML
    private TextArea txtUpdateDescriptionCmd;

    @FXML
    private ComboBox<String> txtUpdateStatutCmd;

    @FXML
    private AnchorPane updateCommandePane;

    private int id;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        txtUpdateDateCmd.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) < 0 );
            }
        });

        ObservableList<String> options = FXCollections.observableArrayList(
                "En Attente","En Cours","Expédiée");
        txtUpdateStatutCmd.setItems(options);

        ServiceCommande cs = new ServiceCommande();
        List<Commande> cmds = cs.Show();

        for(int i=0;i<cmds.size();i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("itemCommande.fxml"));

            try {
                AnchorPane anchorPane = fxmlLoader.load();
                HBox hBox = (HBox) anchorPane.getChildren().get(0);
                itemCommandeController itemController = fxmlLoader.getController();
                txtUpdateDescriptionCmd.setText(itemController.getData(cmds.get(i)).getDescription());
                this.id=itemController.getData(cmds.get(i)).getId();
            } catch (IOException ex) {
                Logger.getLogger(itemCommandeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    void UpdateCommande(ActionEvent event) {
        if (txtUpdateDescriptionCmd.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information manquante");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez remplir tous les détails concernant votre Commande.");
            Optional<ButtonType> option = alert.showAndWait();

        } else {
            modifCommande();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Modifié avec succès");
            alert.setHeaderText(null);
            alert.setContentText("Votre Commande a été modifié avec succès.");
            Optional<ButtonType> option = alert.showAndWait();
        }
    }

    @FXML
    void clearFieldsCommande(ActionEvent event) {
        txtUpdateStatutCmd.getEditor().clear();
        txtUpdateDescriptionCmd.clear();
        txtUpdateDateCmd.getEditor().clear();
    }

    void modifCommande(){
        String statut = txtUpdateStatutCmd.getValue();
        Date date = null;
        try {
            LocalDate localDate = txtUpdateDateCmd.getValue();
            if (localDate != null) {
                Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                date = Date.from(instant);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        String description = txtUpdateDescriptionCmd.getText();
        int idProduit = 1;
        int idUser = 1;


        Commande c = new Commande(
                id,
                statut, date, description, idProduit, idUser);
        ServiceCommande sc = new ServiceCommande();
        sc.modifier(c);
    }
}
