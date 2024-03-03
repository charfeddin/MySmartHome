package controllers;

import entities.Categorie;
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
import javafx.scene.control.cell.TextFieldTableCell;
import services.ServiceCategorie;

import java.io.IOException;
import java.sql.SQLException;

public class AfficherCategorie {

    ServiceCategorie serviceCategorie = new ServiceCategorie();

    @FXML
    private TableColumn<Categorie, String> col_nomCategorie;

    @FXML
    private TableColumn<Categorie, Void> col_modifier;

    @FXML
    private TableColumn<Categorie, Void> col_supprimer;

    @FXML
    private TableView<Categorie> tv_categorie;

    @FXML
    void initialize() {
        try {
            ObservableList<Categorie> allCategories = FXCollections.observableList(serviceCategorie.afficher());

            tv_categorie.setItems(allCategories);
            col_nomCategorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
            col_nomCategorie.setCellFactory(TextFieldTableCell.forTableColumn());

            col_nomCategorie.setOnEditCommit(event -> {
                Categorie categorie = event.getRowValue();
                categorie.setNom_categorie(event.getNewValue());
                try {
                    serviceCategorie.modifier(categorie);
                    // Afficher un message de succès
                    showAlert(Alert.AlertType.INFORMATION, "Modification réussie", "La catégorie a été modifiée avec succès.");
                } catch (SQLException e) {
                    // Gérer les erreurs de mise à jour
                    showAlert(Alert.AlertType.ERROR, "Erreur de modification", "Une erreur est survenue lors de la modification de la catégorie : " + e.getMessage());
                    // Rétablir la valeur originale dans la TableView en cas d'erreur
                    tv_categorie.refresh();
                }
            });

            col_modifier.setCellFactory(cell -> new TableCell<>() {
                private final Button btn_modifier = new Button("Modifier");

                {
                    btn_modifier.setOnAction(event -> {
                        Categorie categorie = getTableView().getItems().get(getIndex());
                        System.out.println("Clicked Modifier for: " + categorie.getNom_categorie());
                        // Mettez ici la logique pour la modification
                        // Ouvrir une nouvelle fenêtre pour modifier la catégorie
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierCategorie.fxml"));
                            Parent root = loader.load();
                            ModifierCategorieController controller = loader.getController();
                            controller.initData(categorie); // Passer la catégorie sélectionnée au contrôleur de modification
                            tv_categorie.getScene().setRoot(root);
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
                        Categorie categorie = getTableView().getItems().get(getIndex());
                        System.out.println("Clicked Supprimer for: " + categorie.getNom_categorie());
                        // Mettez ici la logique pour la suppression
                        try {
                            serviceCategorie.supprimer(categorie);
                            getTableView().getItems().remove(categorie);
                            // Afficher une boîte de dialogue pour confirmer la suppression
                            showAlert(Alert.AlertType.INFORMATION, "Suppression réussie", "La catégorie a été supprimée avec succès.");
                        } catch (SQLException e) {
                            // Gérer les erreurs de suppression
                            showAlert(Alert.AlertType.ERROR, "Erreur de suppression", "Une erreur est survenue lors de la suppression de la catégorie : " + e.getMessage());
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
    void retournerAvoirAjouterCategorie(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AjouterCategorie.fxml"));
            tv_categorie.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
