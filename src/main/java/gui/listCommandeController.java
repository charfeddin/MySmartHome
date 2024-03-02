package gui;

import entities.Commande;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import services.ServiceCommande;


public class listCommandeController implements Initializable {

    @FXML
    private AnchorPane listCommandePane;

    @FXML
    private VBox vBox;

    @FXML
    private TextField searchbar_id;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int column=0;
        int row=0;
        try {
            ServiceCommande cs = new ServiceCommande();
            List<Commande> cmds = cs.Show();

            for(int i=0;i<cmds.size();i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("itemCommande.fxml"));

                try {
                    AnchorPane anchorPane = fxmlLoader.load();
                    HBox hBox = (HBox) anchorPane.getChildren().get(0);
                    itemCommandeController itemController = fxmlLoader.getController();
                    itemController.setData(cmds.get(i));
                    vBox.getChildren().add(hBox);

                } catch (IOException ex) {
                    Logger.getLogger(itemCommandeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void SearchForCommande(ActionEvent event) {
        ServiceCommande sc = new ServiceCommande();
        try {
            vBox.getChildren().clear(); // Clear previous content

            String searchText = searchbar_id.getText(); // Assuming id_search is the TextField where the user enters the search text

            ObservableList<Commande> observableList = FXCollections.observableList(sc.Show());

            // Filter the list based on the search text
            List<Commande> filteredList = observableList.stream()
                    .filter(e -> e.getDescription().toLowerCase().contains(searchText.toLowerCase()))
                    .collect(Collectors.toList());

            // Load and display filtered data
            for (Commande f : filteredList) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("itemCommande.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                HBox cardBox = new HBox(anchorPane);
                itemCommandeController cardController = fxmlLoader.getController();
                cardController.setData(f);
                vBox.getChildren().add(cardBox);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
