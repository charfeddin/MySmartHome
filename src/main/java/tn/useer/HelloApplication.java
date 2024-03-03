package tn.useer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

        @Override
        public void start(Stage primaryStage) throws IOException {
            Parent root = FXMLLoader.load(HelloApplication.class.getResource("startfxml.fxml"));
            primaryStage.setTitle("campyy");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }

        /**
         * @param args the command line arguments
         */
        public static void main(String[] args) {
            launch(args);
        }
}