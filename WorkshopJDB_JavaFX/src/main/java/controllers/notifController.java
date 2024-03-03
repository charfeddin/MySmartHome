package controllers;

import entities.notif;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import services.notifService;

import java.sql.SQLException;
import java.util.List;

public class notifController {
    private notifService notifService = new notifService();

    @FXML
    private TableView<notif> tv_pack1;

    @FXML
    private TableColumn<notif, String> col_description1;

    @FXML
    public void initialize() {
        try {
            List<notif> notifList = notifService.getAllNotifications();
            ObservableList<notif> observableNotifList = FXCollections.observableArrayList(notifList);

            tv_pack1.setItems(observableNotifList);
            col_description1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
