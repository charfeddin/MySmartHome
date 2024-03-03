/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package tn.balkis;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tn.useer.HelloApplication;


public class MainFX extends Application {

    @Override
    public void start(Stage primaryStage) {
         try{
              Parent root = FXMLLoader.load(HelloApplication.class.getResource("/tn/useer/hello-view.fxml"));
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
