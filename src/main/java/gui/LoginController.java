package gui;

import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import services.ServiceUser;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button connexionlogin;

    @FXML
    private TextField emaillogin;

    @FXML
    private Label ereurs;

    @FXML
    private PasswordField mdplogin;

    @FXML
    private Button signinlogin;
    ServiceUser userService = new ServiceUser();

    @FXML
    void openRegister(ActionEvent event)throws IOException{
        Parent page2 = FXMLLoader.load(getClass().getResource("registerChoice.fxml"));

        Scene scene2 = new Scene(page2);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene2);
        app_stage.show();
    }

    @FXML
    void connexionloginonbuttonclick(ActionEvent event) throws IOException {
        if (!emaillogin.getText().isEmpty() && !mdplogin.getText().isEmpty()) {
            handleLogin(event);
        } else {
            ereurs.setTextFill(Color.RED);
            ereurs.setText("Entrer correctement votre pseudo et mot de passe");
        }
    }
    private void handleLogin(ActionEvent event) throws IOException{
        try {
            if (!userService.utilisateurLoggedIn(emaillogin.getText(), mdplogin.getText())) {
                ereurs.setTextFill(Color.RED);
                ereurs.setText("email ou mot de passe incorrect");
            } else {
                String loginResult = userService.login(new User(emaillogin.getText(),mdplogin.getText()));
                if (loginResult.equals("Admin")) {
                    Stage stage = (Stage) connexionlogin.getScene().getWindow();
                    stage.close();
                    Parent page2 = FXMLLoader.load(getClass().getResource("admin.fxml"));
                    Scene scene2 = new Scene(page2);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.setScene(scene2);
                    app_stage.show();
                } else if (loginResult.equals("Client")) {
                    Stage stage = (Stage) connexionlogin.getScene().getWindow();
                    stage.close();
                    Parent page2 = FXMLLoader.load(getClass().getResource("client.fxml"));
                    Scene scene2 = new Scene(page2);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.setScene(scene2);
                    app_stage.show();
                } else if (loginResult.equals("Livreur")) {
                    Stage stage = (Stage) connexionlogin.getScene().getWindow();
                    stage.close();
                    Parent page2 = FXMLLoader.load(getClass().getResource("livreur.fxml"));
                    Scene scene2 = new Scene(page2);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.setScene(scene2);
                    app_stage.show();
                } else {
                    new Alert(Alert.AlertType.ERROR, loginResult, ButtonType.CLOSE).show();
                }

            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion: " + e.getMessage());
        }
    }

    @FXML
    void signinloginonbuttonclick(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
            emaillogin.getScene().setRoot(root);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}