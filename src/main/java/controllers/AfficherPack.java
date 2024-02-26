package controllers;

import entities.Pack;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.ServicePack;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class AfficherPack {
    ServicePack servicePack = new ServicePack();

    @FXML
    private TableColumn<Pack, String> col_description;

    @FXML
    private TableColumn<Pack, String> col_dispo;

    @FXML
    private TableColumn<Pack, Float> col_prix;

    @FXML
    private TableColumn<Pack, String> col_idEquipement;

    @FXML
    private TableColumn<Pack, String> col_idCategorie;

    @FXML
    private TableColumn<Pack, Void> col_modifier;

    @FXML
    private TableColumn<Pack, Void> col_supprimer;

    @FXML
    private TableView<Pack> tv_pack;

    @FXML
    void initialize() {
        try {
            ObservableList<Pack> allPacks = FXCollections.observableList(servicePack.afficher());

            Set<String> uniqueDescriptions = new HashSet<>();
            ObservableList<Pack> uniquePacks = FXCollections.observableArrayList();
            for (Pack pack : allPacks) {
                if (uniqueDescriptions.add(pack.getDescription())) {
                    uniquePacks.add(pack);
                }
            }

            tv_pack.setItems(uniquePacks);
            col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
            col_dispo.setCellValueFactory(new PropertyValueFactory<>("dispo"));
            col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            col_idEquipement.setCellValueFactory(cellData -> new SimpleStringProperty("Equipement Details"));
            col_idEquipement.setStyle("-fx-text-fill: blue; -fx-underline: true;");
            col_idCategorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));

            col_modifier.setCellFactory(cell -> new TableCell<>() {
                private final Button btn_modifier = new Button("Modifier");

                {
                    btn_modifier.setOnAction(event -> {
                        Pack pack = getTableView().getItems().get(getIndex());
                        System.out.println("Clicked Modifier for: " + pack.getDescription());
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierPack.fxml"));
                            Parent root = loader.load();
                            ModifierPackController controller = loader.getController();
                            controller.initPack(pack);
                            tv_pack.getScene().setRoot(root);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btn_modifier);
                    }
                }
            });

            col_supprimer.setCellFactory(cell -> new TableCell<>() {
                private final Button btn_supprimer = new Button("Supprimer");

                {
                    btn_supprimer.setOnAction(event -> {
                        Pack pack = getTableView().getItems().get(getIndex());
                        System.out.println("Clicked Supprimer for: " + pack.getDescription());
                        try {
                            String description = pack.getDescription();
                            servicePack.supprimer(pack);
                            servicePack.supprimerPacksParDescription(description); // Supprime tous les packs avec la même description de la base de données
                            tv_pack.getItems().removeIf(p -> p.getDescription().equals(description));
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Succès");
                            alert.setContentText("Les packs ont été supprimés avec succès.");
                            alert.showAndWait();
                        } catch (SQLException e) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Erreur");
                            alert.setContentText("Une erreur est survenue lors de la suppression des packs : " + e.getMessage());
                            alert.showAndWait();
                        }
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btn_supprimer);
                    }
                }
            });

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void retournerAvoirAjouterPack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AjouterPack.fxml"));
            tv_pack.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void showPackDetails(MouseEvent event) {
        if (event.getClickCount() == 1) {
            Pack selectedPack = tv_pack.getSelectionModel().getSelectedItem();
            if (selectedPack != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/DetailsPack.fxml"));
                    Parent root = loader.load();
                    DetailsPackController controller = loader.getController();
                    controller.initData(selectedPack);
                    tv_pack.getScene().setRoot(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
