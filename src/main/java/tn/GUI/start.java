package tn.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tn.useer.HelloApplication;

import java.io.IOException;

public class start extends Application {

    @Override
    public void start(Stage primaryStage) {
        try{


            Parent root = FXMLLoader.load(HelloApplication.class.getResource("../../../resources/tn/useer/startfxml.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            primaryStage.show();


        }catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
