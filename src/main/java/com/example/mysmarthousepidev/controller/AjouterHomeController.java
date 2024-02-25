package com.example.mysmarthousepidev.controller;

import com.example.mysmarthousepidev.entities.Home;
import com.example.mysmarthousepidev.services.ServiceHome;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AjouterHomeController implements Initializable {

    @FXML
    private TextField adresse;

    @FXML
    private TextField des;

    @FXML
    private ChoiceBox<String> dis;


    int c;
    int file;
    File pDir;
    File pfile;
    String lien;
    ServiceHome serviceHome = new ServiceHome();
    @FXML
    private ImageView img;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dis.getItems().addAll("Oui", "Non");
        c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
        lien = "home" + c + ".jpg";
        pDir = new File("src/main/resources/img/"+lien);
    }


    @FXML
    void ajt(ActionEvent event) throws SQLException {

        if (dis.getValue() == null  || des.getText().equals("") || adresse.getText().equals("")|| file == 0 ) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "vérifier votre champs", ButtonType.OK);
            alert.showAndWait();
        }
        else {
            Home h = new Home();
            h.setAdreess(adresse.getText());
            h.setDesigantion(des.getText());
            h.setDisponibilite(dis.getValue());
            h.setImage(lien);
            copier(pfile,pDir);
            serviceHome.ajouter(h);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Ajout Avec success", ButtonType.OK);
            alert.showAndWait();
            Parent root;
            try {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/mysmarthousepidev/AfficherHome.fxml")));
                Stage myWindow = (Stage) adresse.getScene().getWindow();
                Scene sc = new Scene(root);
                myWindow.setScene(sc);
                myWindow.setTitle("Login");
                myWindow.show();
            } catch (IOException ex) {
                Logger.getLogger(AjouterHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }



    }

    @FXML
    void uploadImage(ActionEvent event) throws MalformedURLException {
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
