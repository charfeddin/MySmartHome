package controllers;
import entities.commentaire;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import services.ServiceCommentaire;
import services.ServicePost;

import java.sql.SQLException;

public class Affichercommentaire {
    ServiceCommentaire serviceCommentaire= new ServiceCommentaire();


    @FXML
    private TableColumn<commentaire, String> col_contenu;

    @FXML
    private TableColumn<commentaire, Integer> col_idcommentaire;

    @FXML
    private TableView<commentaire> tv_commentaire;


    @FXML
    void initialize() {
        try {
ObservableList<commentaire> allcomment=FXCollections.observableList(serviceCommentaire.afficher());
     tv_commentaire.setItems(allcomment);
            col_idcommentaire.setCellValueFactory(new PropertyValueFactory<>("id_commentaire"));

            col_contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
            col_contenu.setCellFactory(TextFieldTableCell.forTableColumn());



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

