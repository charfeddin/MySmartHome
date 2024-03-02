package gui;

import entities.Livraison;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import services.ServiceLivraison;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class listLivraisonController implements Initializable {

    @FXML
    private AnchorPane listLivraisonPane;

    @FXML
    private VBox vBox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int column=0;
        int row=0;
        try {
            ServiceLivraison sl = new ServiceLivraison();
            List<Livraison> livs = sl.Show();

            for(int i=0;i<livs.size();i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("itemLivraison.fxml"));

                try {
                    AnchorPane anchorPane = fxmlLoader.load();
                    HBox hBox = (HBox) anchorPane.getChildren().get(0);
                    itemLivraisonController itemController = fxmlLoader.getController();
                    itemController.setData(livs.get(i));
                    vBox.getChildren().add(hBox);

                } catch (IOException ex) {
                    Logger.getLogger(itemLivraisonController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openStat(ActionEvent event) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("statistiques.fxml"));
        listLivraisonPane.getChildren().removeAll();
        listLivraisonPane.getChildren().setAll(fxml);
    }
}
