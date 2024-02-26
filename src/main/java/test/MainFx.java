package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainFx extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //loading page
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/AjouterPack.fxml"));
        //loading composant graphic
        Parent root=fxmlLoader.load();
        //decalrer scene
        Scene scene = new Scene(root);
        //attacher scene a stage
        stage.setScene(scene);
        stage.setTitle("Gerer Pack");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
