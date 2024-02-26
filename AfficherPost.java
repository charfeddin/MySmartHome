package controllers;

import com.jfoenix.controls.JFXButton;
import entities.post;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import services.ServicePost;

import javax.security.auth.callback.Callback;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;








public class AfficherPost {

    ServicePost servicePost = new ServicePost();


    @FXML
    private JFXButton btn_home1;

    @FXML
    private JFXButton btn_home11;

    @FXML
    private JFXButton btn_home111;

    @FXML
    private JFXButton btn_home1111;

    @FXML
    private JFXButton btn_home11111;

    @FXML
    private Button btn_retour;

    @FXML
    private TableColumn<post, String> col_commentaire;

    @FXML
    private TableColumn<post, String> col_datepub;

    @FXML
    private TableColumn<post, Integer> col_idcommentaire;

    @FXML
    private TableColumn<post, Integer> col_idpost;

    @FXML
    private TableColumn<post, String> col_titre;

    @FXML
    private Pane most_inn;

    @FXML
    private Pane pane1;

    @FXML
    private AnchorPane side_anker;

    @FXML
    private TableView<post> tv_post;

    @FXML
    private TextField tf_titre;
    @FXML
    private TextField tf_commentaire;




    @FXML
    void retournerAvoirajouterPost(ActionEvent event) {

    }


    @FXML
    void initialize() {
        try {
            ObservableList<post> ObservableList = FXCollections.observableList(servicePost.afficher());
            tv_post.setItems(ObservableList);
            col_commentaire.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
            col_datepub.setCellValueFactory(new PropertyValueFactory<>("date_pub"));
            col_idcommentaire.setCellValueFactory(new PropertyValueFactory<>("id_commentaire"));

            col_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    @FXML

    public void retournerAvoirajouterPost(javafx.event.ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ajouterPost.fxml"));
            tv_post.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    public void ModifierPost(javafx.event.ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ModifierPost.fxml"));
            tf_titre.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SupprimerPostControllers(javafx.event.ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/supprimerPost.fxml"));
            tf_titre.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

