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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static tn.esprit.reclamationprojet.controller.ReclamationAdminController.idRd;

public class DetailsReclamationController implements Initializable {

    public static int idmodif = 0 ;
    @FXML
    private Text Reponse;
    @FXML
    private Button btnmodifer;
    @FXML
    private Button btnrepondre;
    @FXML
    private TextArea ReponseText;

    @FXML
    private Text contenue;

    @FXML
    private ImageView imv;

    @FXML
    private Text nom;

    @FXML
    private Text sujet;

    ReponseReclamationService reponseReclamationService = new ReponseReclamationService();
    ReclamationService reclamationService = new ReclamationService();

    @FXML
    void Repondre(ActionEvent event) throws SQLDataException {


        ReponseReclamation r = new ReponseReclamation();

        r.setIdRec(idRd);
        r.setContenu(Reponse.getText());
        LocalDate dd = LocalDate.now();
        Date date = java.sql.Date.valueOf(dd);
        r.setDate(date);
        reponseReclamationService.ReclamationReponse(r);
        reclamationService.Traiter(idRd);
        Notifications notificationBuilder = Notifications.create()
                .title("Success").text("Votre Reponse est envoyé").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
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
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/tn/esprit/reclamationprojet/ReclamationAdmin.fxml")));
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Reclamation reclamation = reclamationService.findReclamationById(idRd);

        contenue.setText(reclamation.getContenue());
        nom.setText(reclamationService.findUserById(reclamation.getIdUser()).getNom());
        sujet.setText(reclamation.getSujet());
        if (reclamation.getImage() != null){
            String imagePath = "/img/" + reclamation.getImage();
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            imv.setImage(image);
        }

        ReponseReclamation reponse = reponseReclamationService.findReponseByReclamation(idRd);
        if (reponse != null) {
            Reponse.setText(reponse.getContenu());
            ReponseText.setVisible(false);
            btnmodifer.setVisible(true);
            btnrepondre.setVisible(false);
        }
        else{
            btnmodifer.setVisible(false);
            ReponseText.setVisible(true);
        }




    }

    @FXML
    void modifier(ActionEvent event) {
        idmodif =reponseReclamationService.findReponseByReclamation(idRd).getId();
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/tn/esprit/reclamationprojet/ModifierDetailReclamation.fxml")));
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


