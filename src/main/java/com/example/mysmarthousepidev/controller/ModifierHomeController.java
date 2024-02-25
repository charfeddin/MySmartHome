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

import static com.example.mysmarthousepidev.controller.AfficherHome.idhome;

public class ModifierHomeController  implements Initializable {
    @FXML
    private TextField adresse;

    @FXML
    private TextField des;

    @FXML
    private ChoiceBox<String> dis;
    ServiceHome hs = new ServiceHome();


    @FXML
    void ajt(ActionEvent event) throws SQLException {

        Home home = hs.getHomeById(idhome);

        if (dis.getValue() == null || des.getText().equals("") || adresse.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "vérifier votre champs", ButtonType.OK);
            alert.showAndWait();
        }else{

            home.setId_home(idhome);
            home.setDesigantion(des.getText());
            home.setDisponibilite(dis.getValue());
            home.setAdreess(adresse.getText());
            if (pfile !=null) {
                copier(pfile, pDir);
                home.setImage(lien);
            }
            else{
                home.setImage(home.getImage());
            }

            hs.modifier(home);
        }

        try {
            Parent root;
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

    @FXML
    private ImageView img;

    int c;
    int file;
    File pDir;
    File pfile;

    String lien;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("f&&&&&fff"+idhome);
        dis.getItems().addAll("Oui", "Non");
        Home h = hs.getHomeById(idhome);
        adresse.setText(h.getAdreess());
        dis.setValue(h.getDisponibilite());
        des.setText(h.getDesigantion());
        String imagePath = "/img/" + h.getImage();
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        img.setImage(image);
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

}
