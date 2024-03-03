package controllers;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import entities.Pack;
import entities.notif;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ServicePack;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.*;

import org.apache.commons.mail.*;
import org.apache.logging.log4j.Logger;
import services.notifService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
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
    private static final String APPLICATION_NAME = "Test Mailer";
    @FXML
    private static final String USER_ID = "me";
    @FXML

    JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    @FXML
    private ObservableList<Pack> packsList = FXCollections.observableArrayList();

    @FXML
    private TextField com_promo;
    @FXML
    services.notifService notifService = new notifService();






    @FXML
    void initialize() {
        try {
            ObservableList<Pack> allPacks = FXCollections.observableList(servicePack.afficher());
            packsList.addAll(servicePack.afficher());


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

                        notif n = new notif(-1," Ghassen Marouani has modified a  pack ");
                        try {
                            notifService.ajouter(n);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }


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
                        notif n = new notif(-1," Ghassen Marouani has deleted a  pack ");
                        try {
                            notifService.ajouter(n);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }

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
    //promotion
    /*@FXML
    void applyPromotion(ActionEvent event) {
        String promoCode = com_promo.getText();
        float discountRate = 0.0f;

        // Vérifiez le code promotionnel et déterminez le taux de réduction approprié
        switch (promoCode) {
            case "STUVWX":
                discountRate = 0.1f; // 10% de réduction
                break;
            case "YZABCD":
                discountRate = 0.2f; // 20% de réduction
                break;
            case "EFGHIJ":
                discountRate = 0.3f; // 30% de réduction
                break;
            case "KLMNOP":
                discountRate = 0.4f; // 40% de réduction
                break;
            case "UVWXYZ":
                discountRate = 0.5f; // 50% de réduction
                break;
            default:
                // Code promotionnel invalide, affichez un message d'erreur
                Alert invalidAlert = new Alert(Alert.AlertType.ERROR);
                invalidAlert.setTitle("Code promotionnel invalide");
                invalidAlert.setHeaderText(null);
                invalidAlert.setContentText("Le code promotionnel saisi est invalide.");
                invalidAlert.showAndWait();
                return; // Quittez la méthode si le code est invalide
        }

        // Appliquer la promotion à tous les packs
        ObservableList<Pack> packs = tv_pack.getItems();
        for (Pack pack : packs) {
            float currentPrice = pack.getPrix();
            float newPrice = currentPrice * (1 - discountRate); // Calculer le nouveau prix avec réduction
            pack.setPrix(newPrice);

            try {
                servicePack.modifier(pack); // Mettre à jour le pack dans la base de données avec le nouveau prix
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Rafraîchir la TableView pour afficher les prix mis à jour
        tv_pack.refresh();

        // Afficher un message de confirmation
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Promotion appliquée");
        alert.setHeaderText(null);
        alert.setContentText("La promotion de " +discountRate*100 + "% a été appliquée avec succès." );
        alert.showAndWait();
    }

     */
    @FXML
    void applyPromotion(ActionEvent event) {
        String promoCode = com_promo.getText();
        float discountRate = 0.0f;

        // Vérifiez le code promotionnel et déterminez le taux de réduction approprié
        switch (promoCode) {
            case "STUVWX":
                discountRate = 0.1f; // 10% de réduction
                break;
            case "YZABCD":
                discountRate = 0.2f; // 20% de réduction
                break;
            case "EFGHIJ":
                discountRate = 0.3f; // 30% de réduction
                break;
            case "KLMNOP":
                discountRate = 0.4f; // 40% de réduction
                break;
            case "UVWXYZ":
                discountRate = 0.5f; // 50% de réduction
                break;
            default:
                // Code promotionnel invalide, affichez un message d'erreur
                Alert invalidAlert = new Alert(Alert.AlertType.ERROR);
                invalidAlert.setTitle("Code promotionnel invalide");
                invalidAlert.setHeaderText(null);
                invalidAlert.setContentText("Le code promotionnel saisi est invalide.");
                invalidAlert.showAndWait();
                return; // Quittez la méthode si le code est invalide
        }

        // Appliquer la promotion à tous les packs dans l'affichage
        ObservableList<Pack> packs = tv_pack.getItems();
        for (Pack pack : packs) {
            float currentPrice = pack.getPrix();
            float newPrice = currentPrice * (1 - discountRate); // Calculer le nouveau prix avec réduction
            pack.setPrix(newPrice);
        }

        // Rafraîchir la TableView pour afficher les prix mis à jour
        tv_pack.refresh();

        // Afficher un message de confirmation
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Promotion appliquée");
        alert.setHeaderText(null);
        alert.setContentText("La promotion de " + discountRate * 100 + "% a été appliquée avec succès.");
        alert.showAndWait();
    }


    @FXML
    void generatePdf(ActionEvent event) {
        ObservableList<Pack> packs = tv_pack.getItems();

        // Générer le PDF avec les détails des packs
        PdfPack pdfPack = new PdfPack();
        pdfPack.generatePdf(new ArrayList<>(packs));

        // Afficher un message de succès
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("PDF généré");
        alert.setContentText("Le PDF des packs a été généré avec succès !");
        alert.showAndWait();
    }
    @FXML
    void envoyerEmail(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir le fichier PDF");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Fichiers PDF", "*.pdf")
        );
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                GMailer gMailer = new GMailer();
                gMailer.sendMailWithAttachment("Promotions sur nos packs", """
               DEAR CUSTOMER,
               Voici nos dernières promotions sur nos packs !
               Cordialement,
               Votre équipe.
                """, selectedFile.getAbsolutePath());

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("E-mail envoyé");
                alert.setHeaderText(null);
                alert.setContentText("L'e-mail a été envoyé avec succès !");
                alert.showAndWait();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Une erreur s'est produite lors de l'envoi de l'e-mail : " + e.getMessage());
                alert.showAndWait();
            }
        }
    }

    @FXML
    void genererExcel(ActionEvent event) {
        ExcelPack excelPack = new ExcelPack();
        excelPack.generateExcel(new ArrayList<>(packsList));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("Le fichier Excel des packs a été généré avec succès !");
        alert.showAndWait();
    }
    @FXML
    void generatePromotionCode(ActionEvent event) {
        // Tableau contenant les codes de promotion possibles
        String[] promoCodes = {"STUVWX", "YZABCD", "EFGHIJ", "KLMNOP", "UVWXYZ"};

        // Générer un index aléatoire pour choisir un code de promotion dans le tableau
        Random random = new Random();
        int randomIndex = random.nextInt(promoCodes.length);

        // Récupérer le code de promotion généré
        String generatedPromoCode = promoCodes[randomIndex];

        // Afficher le code de promotion généré dans le champ de texte com_promo
        com_promo.setText(generatedPromoCode);

        // Afficher un message d'alerte indiquant le coupon qui a été ajouté avec succès
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Coupon généré");
        alert.setHeaderText(null);
        alert.setContentText("Le coupon " + generatedPromoCode + " a été ajouté avec succès.");
        alert.showAndWait();
    }







}
