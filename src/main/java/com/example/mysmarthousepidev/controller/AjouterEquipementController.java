package com.example.mysmarthousepidev.controller;

import com.example.mysmarthousepidev.entities.Equipement;
import com.example.mysmarthousepidev.services.ServiceEquipement;
import com.example.mysmarthousepidev.services.ServiceHome;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.mysmarthousepidev.controller.AfficherHome.idhome;

public class AjouterEquipementController  implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        etat.getItems().addAll("Active", "Desactive");
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([1-9][0-9]*)?")) {
                return change;
            }
            return null;
        };
        nbr.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));
    }
    @FXML
    private TextField des;

    @FXML
    private ChoiceBox<String> etat;

    @FXML
    private AnchorPane iv_photo;

    @FXML
    private TextField nbr;
    @FXML
    private TextField nom;
    @FXML
    private TextField temp;

    ServiceHome serviceHome = new ServiceHome();
    ServiceEquipement serviceEquipement = new ServiceEquipement();
   @FXML
    void ajt(ActionEvent event) throws SQLException {

        if (etat.getValue().equals("") || temp.getText().equals("") || nom.getText().equals("")||des.getText().equals("")) {

            Alert alert = new Alert(Alert.AlertType.ERROR, "Vérifeier les champs", ButtonType.OK);
            alert.showAndWait();
        }

        else {
            Equipement e = new Equipement();
            e.setId_home(serviceHome.getHomeById(idhome));
            e.setEtat(etat.getValue());
            e.setDescription(des.getText());
            e.setTemper(Float.parseFloat(temp.getText()));
            e.setNombre(Integer.parseInt(nbr.getText()));
            e.setNom(nom.getText());
            serviceEquipement.ajouter(e);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Ajout Avec success", ButtonType.OK);
            alert.showAndWait();
            Parent root;
            try {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AfficherEquipement.fxml")));
                Stage myWindow = (Stage) nom.getScene().getWindow();
                Scene sc = new Scene(root);
                myWindow.setScene(sc);
                myWindow.setTitle("Afficher Equipement");
                myWindow.show();
            } catch (IOException ex) {
                Logger.getLogger(AjouterHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }


        }

    }
}
