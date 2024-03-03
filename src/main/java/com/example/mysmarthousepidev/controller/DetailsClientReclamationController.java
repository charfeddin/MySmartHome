package tn.esprit.reclamationprojet.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

public class DetailsClientReclamationController implements Initializable {

    @FXML
    private Text Reponse;
    @FXML
    private Button btnmodifer;

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


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Reclamation reclamation = reclamationService.findReclamationById(ShowMesReclamationController.idE);

        contenue.setText(reclamation.getContenue());
        nom.setText(reclamationService.findUserById(reclamation.getIdUser()).getNom());
        sujet.setText(reclamation.getSujet());
        if (reclamation.getImage() != null){
            String imagePath = "/img/" + reclamation.getImage();
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            imv.setImage(image);
        }
        ReponseReclamation reponse = reponseReclamationService.findReponseByReclamation(ShowMesReclamationController.idE);
       if (reponse != null) {
           Reponse.setText(reponse.getContenu());
       }
    }

    @FXML
    void modifier(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/tn/esprit/reclamationprojet/ModefierReclamation.fxml")));
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

    @FXML
    void back(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/tn/esprit/reclamationprojet/ShowMesReclamation.fxml")));
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

    @FXML
    void pdf(ActionEvent event) throws DocumentException, FileNotFoundException {
        ReclamationService rs = new ReclamationService();
        pdfTelecharger(rs.findReclamationById(ShowMesReclamationController.idE));
    }




    public  void pdfTelecharger (Reclamation r) throws FileNotFoundException, DocumentException {

        ReclamationService rs = new ReclamationService();
        ReponseReclamationService reporte = new ReponseReclamationService();
        ReponseReclamation response = reporte.findReponseByReclamation(r.getIdrec());

        String file_name="C:\\Users\\ASUS\\OneDrive\\Bureau\\PDF\\reclamtion.pdf";
        Document document = new Document();

        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();

        Paragraph p =new Paragraph("Nom User :"+rs.findUserById(r.getIdUser()).getNom());
        Paragraph p1 =new Paragraph("Sujet :"+r.getSujet());
        Paragraph p2 =new Paragraph("Contenue :"+r.getContenue());

        document.add(p);
        document.add(p1);
        document.add(p2);
        document.close();


    }



    }


