package tn.esprit.reclamationprojet.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import tn.esprit.reclamationprojet.entities.Reclamation;
import tn.esprit.reclamationprojet.entities.ReponseReclamation;
import tn.esprit.reclamationprojet.services.ReclamationService;
import tn.esprit.reclamationprojet.services.ReponseReclamationService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static tn.esprit.reclamationprojet.controller.ReclamationAdminController.idRd;

public class ModifierDetailsReclamationController implements Initializable {

    @FXML
    private TextArea ReponseText;

    @FXML
    private Button btnrepondre;

    @FXML
    private Text contenue;

    @FXML
    private ImageView imv;

    @FXML
    private Text nom;

    @FXML
    private Text sujet;

    ReclamationService reclamationService = new ReclamationService();
    ReponseReclamationService responseReclamationService = new ReponseReclamationService(); //

    @FXML
    void Repondre(ActionEvent event) throws SQLDataException {

        if (ReponseText.getText().equals("")){

            Notifications notificationBuilder = Notifications.create()
                    .title("ERROR").text("Vérifier vos données").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                    .position(Pos.CENTER_LEFT)
                    .onAction(new EventHandler<ActionEvent>(){
         public void handle(ActionEvent event)
                        {
                            System.out.println("clicked ON ");
                        }});
            notificationBuilder.darkStyle();
            notificationBuilder.show();

        }


        else {

        try {

            ReponseReclamation reponseReclamation =responseReclamationService.findReponseByReclamation(idRd);

            reponseReclamation.setContenu(ReponseText.getText());
            System.out.println("hello"+reponseReclamation);
            responseReclamationService.modifieReclamation(reponseReclamation);
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
        Notifications notificationBuilder = Notifications.create()
                    .title("Success").text("Modification Avec success").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                    .position(Pos.CENTER_LEFT)
                    .onAction(new EventHandler<ActionEvent>(){
                        public void handle(ActionEvent event)
                        {
                            System.out.println("clicked ON ");
                        }});
            notificationBuilder.darkStyle();
            notificationBuilder.show();
            Parent root;
            try {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/tn/esprit/reclamationprojet/DetailsReclamation.fxml")));
                Stage myWindow = (Stage) sujet.getScene().getWindow();
                Scene sc = new Scene(root);
                myWindow.setScene(sc);
                myWindow.setTitle("page name");
                //myWindow.setFullScreen(true);
                myWindow.show();
            } catch (IOException ex) {
                Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Reclamation reclamation = reclamationService.findReclamationById(idRd);

        contenue.setText(reclamation.getContenue());
        nom.setText(reclamationService.findUserById(reclamation.getIdUser()).getNom());
        sujet.setText(reclamation.getSujet());
        if (reclamation.getImage() != null) {
            String imagePath = "/img/" + reclamation.getImage();
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            imv.setImage(image);
        }

        ReponseReclamation reponseReclamation = responseReclamationService.findReponseByReclamation(idRd);
        ReponseText.setText(reponseReclamation.getContenu());
    }

}
