package tn.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import tn.services.ServiceUtilisateurIMP;
import javafx.util.Duration;
import tn.useer.HelloApplication;

public class StatfxmlController implements Initializable {

    @FXML
    private PieChart pieChart;

    @FXML
    private AnchorPane statPane;

    /**
     * Initializes the controller class.
     */
            ServiceUtilisateurIMP su = new ServiceUtilisateurIMP();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         Map<String, Integer> countMap = new HashMap<>();
        countMap.putAll(su.countusersByRole());

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        countMap.forEach((role, count) -> {
            pieChartData.add(new PieChart.Data(role, count));
        });
        pieChart.setTitle("Statistiques des roles des utilisateurs");
        pieChart.setData(pieChartData);
        FadeTransition transition = new FadeTransition(Duration.seconds(3), pieChart);
        transition.setFromValue(0);
        transition.setToValue(1);
        transition.play();
        
        
        
    }    

    @FXML
    private void retour(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("AdminDashboardFXML.fxml"));
        Parent root = loader.load();
        statPane.getChildren().removeAll();
        statPane.getChildren().setAll(root);
    }
    
}
