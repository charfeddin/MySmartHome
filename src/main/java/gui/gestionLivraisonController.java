package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class gestionLivraisonController implements Initializable {


    @FXML
    private Button btnAddLivraison;

    @FXML
    private Button btnListLivraison;

    @FXML
    private AnchorPane gestionLivraisonPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @FXML
    void goToPages(ActionEvent event) throws IOException {
        if(event.getSource()== btnAddLivraison){
            Parent fxml= FXMLLoader.load(getClass().getResource("addLivraison.fxml"));
            gestionLivraisonPane.getChildren().removeAll();
            gestionLivraisonPane.getChildren().setAll(fxml);
        }else if(event.getSource()==btnListLivraison){
            Parent fxml= FXMLLoader.load(getClass().getResource("listLivraison.fxml"));
            gestionLivraisonPane.getChildren().removeAll();
            gestionLivraisonPane.getChildren().setAll(fxml);
        }
    }


}
