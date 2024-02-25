package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class adminDashboardController implements Initializable {

    @FXML
    private Button btnUser;

    @FXML
    private Button logOut;

    @FXML
    private AnchorPane view_pages;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void switchForm(ActionEvent event) throws IOException {
        if(event.getSource()== btnUser){
            Parent fxml= FXMLLoader.load(getClass().getResource("listUsers.fxml"));
            view_pages.getChildren().removeAll();
            view_pages.getChildren().setAll(fxml);
        }
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        if(event.getSource()== logOut){
            Parent page2 = FXMLLoader.load(getClass().getResource("login.fxml"));

            Scene scene2 = new Scene(page2);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(scene2);
            app_stage.show();
        }
    }
}
