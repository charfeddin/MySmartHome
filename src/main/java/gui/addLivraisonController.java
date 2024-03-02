package gui;

import entities.Commande;
import entities.Livraison;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import services.ServiceCommande;
import services.ServiceLivraison;
import utils.MyDB;

import java.net.URL;
import java.util.*;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class addLivraisonController implements Initializable {

    @FXML
    private AnchorPane addLivraisonPane;

    @FXML
    private Button btnAddLivraison;

    @FXML
    private Button btnClearLivraison;

    @FXML
    private TextField txtAdresseLivraison;

    @FXML
    private ComboBox<String> txtCommandeLivraison;

    @FXML
    private TextField txtPrixLivraison;



    ServiceCommande se = new ServiceCommande();
    List<Commande> cmds = se.Show();
    private int idCmd=-1;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Map<String, Integer> valuesMap = new HashMap<>();
        for(Commande c : cmds){
            txtCommandeLivraison.getItems().add(c.getDescription());
            valuesMap.put(c.getDescription(),c.getId());
        }

        txtCommandeLivraison.setOnAction(event ->{
            String SelectedOption = null;
            SelectedOption = txtCommandeLivraison.getValue();
            int SelectedValue = 0;
            SelectedValue = valuesMap.get(SelectedOption);
            idCmd = SelectedValue;
        });
    }

    @FXML
    void AjoutLivraison(ActionEvent event) {
        //check if not empty
        if(event.getSource() == btnAddLivraison){
            if (idCmd==-1 || txtPrixLivraison.getText().isEmpty() || txtAdresseLivraison.getText().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information manquante");
                alert.setHeaderText(null);
                alert.setContentText("Vous devez remplir tous les détails concernant votre Livraison.");
                Optional<ButtonType> option = alert.showAndWait();
            } else {
                ajouterLivraison();
                send_SMS();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ajouté avec succès");
                alert.setHeaderText(null);
                alert.setContentText("Votre Livraison a été ajoutée avec succès.");
                Optional<ButtonType> option = alert.showAndWait();
                clearFieldsLivraison();
            }
        }
        if(event.getSource() == btnClearLivraison){
            clearFieldsLivraison();
        }
    }

    @FXML
    void clearFieldsLivraison() {
        txtAdresseLivraison.clear();
        txtPrixLivraison.clear();
        txtCommandeLivraison.getEditor().clear();
    }

    private void ajouterLivraison() {

        // From Formulaire
        int prix = Integer.parseInt(txtPrixLivraison.getText());
        String adresse = txtAdresseLivraison.getText();
        int idCommande = idCmd;


        MyDB db = MyDB.getInstance();
        Livraison liv = new Livraison(
                prix, adresse, idCommande);
        ServiceLivraison as = new ServiceLivraison();
        as.ajouter(liv);
    }

    void send_SMS (){
        // Initialisation de la bibliothèque Twilio avec les informations de votre compte
        String ACCOUNT_SID = "ACba5eec72ac4c5d40af157a3f78a51d40";
        String AUTH_TOKEN = "8d42fb4b67d02b5041004638e04f61ed";

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        String recipientNumber = "+21653510645";
        String message = "Bonjour Mr ,\n"
                + "Nous sommes ravis de vous informer qu'une nouvelle livraison a été ajouté.\n "
                + "Veuillez contactez l'administration pour plus de details.\n "
                + "Merci de votre fidélité et à bientôt chez MySmartHome.\n"
                + "Cordialement,\n"
                + "MySmartHome.";

        Message twilioMessage = Message.creator(
                new PhoneNumber(recipientNumber),
                new PhoneNumber("+14437752969"),message).create();

        System.out.println("SMS envoyé : " + twilioMessage.getSid());
    }
}
