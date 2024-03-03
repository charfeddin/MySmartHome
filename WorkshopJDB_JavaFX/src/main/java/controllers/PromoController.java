package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.Random;

public class PromoController {

    @FXML
    private TextField promoField;

    @FXML
    void generatePromoCode(ActionEvent event) {
        // Tableau contenant les codes de promotion possibles
        String[] promoCodes = {"STUVWX", "YZABCD", "EFGHIJ", "KLMNOP", "UVWXYZ"};

        // Générer un index aléatoire pour choisir un code de promotion dans le tableau
        Random random = new Random();
        int randomIndex = random.nextInt(promoCodes.length);

        // Afficher le code de promotion généré dans le champ de texte
        promoField.setText(promoCodes[randomIndex]);
    }
}
