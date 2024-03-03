/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tn.controllers;

import tn.entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tn.services.ServiceUtilisateurIMP;
import tn.useer.HelloApplication;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import tn.controllers.sendMail;


public class ModifierPasswordClientController implements Initializable {

    @FXML
    private TextField tf_code;
    @FXML
    private TextField nouveaumotdepasseTexte;
    @FXML
    private TextField confiramtiondumotdupasseTexte;
    @FXML
    private Button validerButton;

    /**
     * Initializes the controller class.
     */
    
    
        
        ServiceUtilisateurIMP su = new ServiceUtilisateurIMP();
    private int randomnumber = (int) (Math.random() * 9999);

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    @FXML
    private void ValiderChangement(ActionEvent event) {
        
        if (tf_code.getText().equals(randomnumber + "")) {
            if (nouveaumotdepasseTexte.getText().equals(confiramtiondumotdupasseTexte.getText())) {
                su.ChangerPassword(Utilisateur.user_connecter.getId(), nouveaumotdepasseTexte.getText());

                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setTitle("Sucess");
                al.setContentText(" votre mot de passe a ete bien rnitialiser ");
                al.setHeaderText(null);
                al.showAndWait();
                goToLogin(event);
                
                
            } else {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Alert");
                al.setContentText("Verifier la confirmiter de votre nouveau mot de passe ");
                al.setHeaderText(null);
                al.show();

            }

        } else {

            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Alert");
            al.setContentText("Votre code est incorrecte");
            al.setHeaderText(null);
            al.show();
        }
    }
    
    private void goToLogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("loginfxml.fxml"));
            Parent root = loader.load();
            tf_code.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
    }

    @FXML
    private void Retoure(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("loginfxml.fxml"));
            Parent root = loader.load();
            tf_code.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage()); 
    }
  }

  
    
}
