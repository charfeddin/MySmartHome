package gui;

import entities.Commande;
import entities.Livraison;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import services.ServiceCommande;
import services.ServiceLivraison;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class updateLivraisonController implements Initializable {

    @FXML
    private Button btnClearLivraison;

    @FXML
    private Button btnUpdateLivraison;

    @FXML
    private TextField txtUpdateAdresseLivraison;

    @FXML
    private ComboBox<String> txtUpdateCommandeLivraison;

    @FXML
    private TextField txtUpdatePrixLivraison;

    @FXML
    private AnchorPane updateLivraisonPane;

    private int id;


    ServiceCommande cs = new ServiceCommande();
    List<Commande> cmds = cs.Show();
    private int cmdId=-1;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Map<String, Integer> valuesMap = new HashMap<>();
        for(Commande c : cmds){
            txtUpdateCommandeLivraison.getItems().add(c.getDescription());
            valuesMap.put(c.getDescription(),c.getId());
        }

        txtUpdateCommandeLivraison.setOnAction(event ->{
            String SelectedOption = null;
            SelectedOption = txtUpdateCommandeLivraison.getValue();
            int SelectedValue = 0;
            SelectedValue = valuesMap.get(SelectedOption);
            cmdId = SelectedValue;
        });


        ServiceLivraison sl = new ServiceLivraison();
        List<Livraison> livs = sl.Show();

        for(int i=0;i<livs.size();i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("itemLivraison.fxml"));

            try {
                AnchorPane anchorPane = fxmlLoader.load();
                HBox hBox = (HBox) anchorPane.getChildren().get(0);
                itemLivraisonController itemController = fxmlLoader.getController();
                txtUpdatePrixLivraison.setText(String.valueOf(itemController.getData(livs.get(i)).getPrix()));
                txtUpdateAdresseLivraison.setText(itemController.getData(livs.get(i)).getAdresse());
                this.id=itemController.getData(livs.get(i)).getId();
            } catch (IOException ex) {
                Logger.getLogger(itemLivraisonController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    void UpdateLivraison(ActionEvent event) {
        if (txtUpdatePrixLivraison.getText().isEmpty() || txtUpdateAdresseLivraison.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information manquante");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez remplir tous les détails concernant votre Livraison.");
            Optional<ButtonType> option = alert.showAndWait();

        } else {
            modifLivraison();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Modifié avec succès");
            alert.setHeaderText(null);
            alert.setContentText("Votre Livraison a été modifié avec succès.");
            Optional<ButtonType> option = alert.showAndWait();
        }
    }

    @FXML
    void clearFieldsLivraison(ActionEvent event) {
        txtUpdatePrixLivraison.clear();
        txtUpdateAdresseLivraison.clear();
        txtUpdateCommandeLivraison.getEditor().clear();
    }

    void modifLivraison(){
        int prix = Integer.parseInt(txtUpdatePrixLivraison.getText());
        String description = txtUpdateAdresseLivraison.getText();
        int idCmd = cmdId;


        Livraison l = new Livraison(
                id,
                prix, description, idCmd);
        ServiceLivraison sl = new ServiceLivraison();
        sl.modifier(l);
    }
}
