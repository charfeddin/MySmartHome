package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterChoiceController implements Initializable {

    @FXML
    private Button btnClose;

    @FXML
    private Button registerC;

    @FXML
    private Button registerDeliverer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void backPrec(ActionEvent event) throws IOException{
        Parent page2 = FXMLLoader.load(getClass().getResource("login.fxml"));

        Scene scene2 = new Scene(page2);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene2);
        app_stage.show();
    }

    @FXML
    void closW(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    void goRegisterClient(ActionEvent event) throws IOException{
        Parent page2 = FXMLLoader.load(getClass().getResource("registerClient.fxml"));

        Scene scene2 = new Scene(page2);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene2);
        app_stage.show();
    }

    @FXML
    void goRegisterDeliverer(ActionEvent event) throws IOException {
        Parent page2 = FXMLLoader.load(getClass().getResource("registerLivreur.fxml"));

        Scene scene2 = new Scene(page2);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene2);
        app_stage.show();
    }
}
