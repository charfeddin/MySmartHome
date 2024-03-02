package gui;

import entities.Livraison;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import services.ServiceLivraison;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StatistiquesController implements Initializable {

    @FXML
    private LineChart<String, Integer> lineChartLivraison;

    @FXML
    private AnchorPane statPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        statistique();
    }

    public void statistique() {
        ServiceLivraison cs = new ServiceLivraison();

        List<Livraison> livrs = cs.Show();



        // Créer les axes pour le graphique
        final NumberAxis yAxis = new NumberAxis();
        final CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Adresse");
        yAxis.setLabel("Frais");

        // Créer la série de données à afficher
        XYChart.Series series = new XYChart.Series();
        series.setName("Statistiques des frais selon les adresses");
        for (Livraison liv : livrs) {
            series.getData().add(new XYChart.Data<>(liv.getAdresse(), liv.getPrix()));
        }

        // Créer le graphique et ajouter la série de données
        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Statistiques des Frais de Livraison");
        lineChart.getData().add(series);

        // Afficher le graphique dans votre scène
        lineChartLivraison.setCreateSymbols(false);
        lineChartLivraison.getData().add(series);

    }
}
