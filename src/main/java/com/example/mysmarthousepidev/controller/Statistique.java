package tn.esprit.reclamationprojet.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import tn.esprit.reclamationprojet.entities.Reclamation;
import tn.esprit.reclamationprojet.services.ReclamationService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Statistique implements Initializable {


    ReclamationService rs = new ReclamationService();
    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private NumberAxis yNombre;
    @FXML
    private CategoryAxis Xsujet;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            System.out.println("ssssss");
            setDataToBarChart();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void setDataToBarChart() throws SQLException {


        // Créer le graphique à barres

        // Créer les données à afficher dans le graphique à barres
        ObservableList<XYChart.Data<String, Number>> data = FXCollections.observableArrayList();
        try {
            data.add(new XYChart.Data<>("Commercial", rs.getNbrReclamation("Commercial")));
            data.add(new XYChart.Data<>("Instation", rs.getNbrReclamation("Instation")));
            data.add(new XYChart.Data<>("Other", rs.getNbrReclamation("Other")));
            data.add(new XYChart.Data<>("pack", rs.getNbrReclamation("pack")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        // Créer la série de données à partir des données
        XYChart.Series<String, Number> series = new XYChart.Series<>(data);
        series.setName("Nombre de Réclamations");

        // Ajouter la série de données au graphique à barres
        barChart.getData().add(series);
    }



}
