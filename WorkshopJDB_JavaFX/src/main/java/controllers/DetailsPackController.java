package controllers;

import entities.Pack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import services.ServicePack;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DetailsPackController {
    @FXML
    private Label lblDescription;

    @FXML
    private Label lblPrice;

    @FXML
    private ListView<String> lvEquipments;

    private Pack pack;
    @FXML
    private ServicePack servicePack;

    public void initData (Pack pack) {
        this.pack = pack;
        servicePack = new ServicePack();

        // Afficher les détails du pack
        lblDescription.setText(pack.getDescription());
        lblPrice.setText(String.valueOf(pack.getPrix()));

        // Afficher les équipements correspondants
        try {
            List<String> equipements = servicePack.fetchEquipmentsByDescription(pack.getDescription());
            lvEquipments.getItems().addAll(equipements);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void retournerAvoirAjouterPack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherPack.fxml"));
            lblDescription.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}