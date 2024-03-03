/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.reclamationprojet.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.controlsfx.control.Notifications;
import tn.esprit.reclamationprojet.entities.BadWords;
import tn.esprit.reclamationprojet.entities.Reclamation;
import tn.esprit.reclamationprojet.services.ReclamationService;
import tn.esprit.reclamationprojet.services.ReponseReclamationService;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ModefierReclamationController implements Initializable {

    @FXML
    private TextArea contenue;

    @FXML
    private ImageView img;

    @FXML
    private TextField sujet;

    @FXML
    private ChoiceBox<String> type;
    int c;
    int file;
    File pDir;
    File pfile;
    String lien;
    
    ReclamationService rs = new ReclamationService();
    ReponseReclamationService reponseReclamationService = new ReponseReclamationService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Reclamation r =rs.findReclamationById(ShowMesReclamationController.idE);
        type.getItems().addAll("Commercial", "Instation","Other","pack");
        c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
        lien = "reclamation" + c + ".jpg";
        pDir = new File("src/main/resources/img/"+lien);
        sujet.setText(r.getSujet());
        contenue.setText(r.getContenue());
        type.setValue(r.getType());
        if(r.getImage() != null){
            String imagePath = "/img/" + r.getImage();
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            img.setImage(image);
        }

    }

    @FXML
    private void envoyer(ActionEvent event) throws SQLDataException {
        
              Reclamation r = new Reclamation();
                BadWords.loadConfigs();

       if (contenue.getText().equals("") || sujet.getText().equals("") || type.getValue().equals(""))
        {
        Notifications.create().title("warnnig").text("Vérfier Vos Coordonne").position(Pos.BOTTOM_RIGHT).showError();

        }
                        else
        
                        {
                            
                            System.out.println("Controller.AjouterReclamationController.envoyer()"+BadWords.filterText(contenue.getText()));
        if (BadWords.filterText(contenue.getText())) {
            
       Notifications notificationBuilder = Notifications.create()
               .title("Alert").text("cette application n'autorise pas ces termes").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
                        }
                                 else{

        r.setContenue(contenue.getText());
        r.setSujet(sujet.getText());
        r.setEtat(rs.findReclamationById(ShowMesReclamationController.idE).getEtat());
        r.setIdrec(ShowMesReclamationController.idE);
        r.setType(type.getValue());
            if (pfile != null){
                r.setImage(lien);
                copier(pfile,pDir);
            }
        rs.modifieReclamation(r);
        
                                  Parent root;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/tn/esprit/reclamationprojet/ShowMesReclamation.fxml"));
                            Stage myWindow = (Stage) sujet.getScene().getWindow();
                            Scene sc = new Scene(root);
                            myWindow.setScene(sc);
                            myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
                            myWindow.show();
                        } catch (IOException ex) {
                            Logger.getLogger(ModefierReclamationController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        }
    
        
    }
    }

    @FXML
    void Upload(ActionEvent event) throws MalformedURLException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image: ");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp")
        );
        Window stage = null;
        pfile = fileChooser.showOpenDialog(stage);

        /* - draw image */
        if (pfile != null) {
            file=1;
            Image image = new Image(pfile.toURI().toURL().toExternalForm());
            img.setImage(image);
        }

    }


    public static void copier(File source, File dest) {
        try (InputStream sourceFile = new java.io.FileInputStream(source);
             OutputStream destinationFile = new FileOutputStream(dest)) {
            // Lecture par segment de 0.5Mo
            byte[] buffer = new byte[512 * 1024];
            int nbLecture;
            while ((nbLecture = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, nbLecture);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
