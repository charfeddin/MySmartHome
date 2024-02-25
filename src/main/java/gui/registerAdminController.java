package gui;

import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ServiceUser;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class registerAdminController implements Initializable {


    @FXML
    private Button RegisterClient;

    @FXML
    private TextField cinClient;

    @FXML
    private TextField emailClient;

    @FXML
    private PasswordField mdpClient;

    @FXML
    private TextField nomClient;

    @FXML
    private TextField numClient;

    @FXML
    private TextField prenomClient;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void sisncrire(ActionEvent event) throws IOException {
        ServiceUser userS = new ServiceUser();
        String Role = "Admin";
        String email = emailClient.getText();

        // Contrôle de saisie pour le champ email
        if (email == null || email.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "L'email n'est pas valide.");
            alert.showAndWait();
            return;
        }

        // Contrôle de saisie pour le champ numéro de téléphone
        if (numClient.getText() == null || numClient.getText().isEmpty() || !numClient.getText().matches("\\d+")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Le numéro de téléphone n'est pas valide.");
            alert.showAndWait();
            return;
        }

        // Contrôle de saisie pour le champ date de naissance
        if (nomClient.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez saisir le nom.");
            alert.showAndWait();
            return;
        }


        if (userS.emailExists(email)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Cet email existe déjà dans la base de données.");
            alert.showAndWait();
            return;
        }

        int cin = Integer.parseInt(cinClient.getText());
        String nom = nomClient.getText();
        String prenom = prenomClient.getText();
        int num = Integer.parseInt(numClient.getText());
        String mdp = mdpClient.getText();
        userS.ajouter(new User(cin,nom,prenom,num,email,mdp,Role));

        Parent page2 = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene2 = new Scene(page2);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene2);
        app_stage.show();
    }
}
