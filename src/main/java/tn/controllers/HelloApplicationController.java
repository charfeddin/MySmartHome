package tn.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.useer.HelloApplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloApplicationController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    private Button btnCommande;

    @FXML
    private Button btnUsers;

    @FXML
    private Button btnLivraison;

    @FXML
    private AnchorPane view_pages;

    @FXML
    void switchForm(ActionEvent event) throws IOException {
        if(event.getSource()== btnUsers){
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("AdminDashboardFXML.fxml"));
            Parent root = loader.load();
            view_pages.getChildren().removeAll();
            view_pages.getChildren().setAll(root);
        }
    }

    @FXML
    private void logout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("loginfxml.fxml"));
            Parent root = loader.load();
            Stage secondaryStage = new Stage();
            FXMLLoader loader2 = new FXMLLoader(HelloApplication.class.getResource("LoginFXML.fxml"));
            Parent root2 = loader2.load();
            LoginfxmlController loginController = loader2.getController();
            loginController.getStage().close();
            secondaryStage.setScene(new Scene(root));
            secondaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
