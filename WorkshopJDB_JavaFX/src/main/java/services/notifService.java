package services;

import entities.notif;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class notifService implements inotifService<notif> {
    private Connection con;

    public notifService() {
        con = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(notif notif) throws SQLException {
        String query = "INSERT INTO notif (description) VALUES (?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, notif.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error adding notif: " + e.getMessage());
        }
    }

    @Override
    public ObservableList<notif> afficher() throws SQLException {
        ObservableList<notif> notifList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM notif";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                notif notif = new notif(id, description);
                notifList.add(notif);
            }
        }
        return notifList;
    }

    public List<notif> getAllNotifications() throws SQLException {
        List<notif> notifications = new ArrayList<>();
        String query = "SELECT * FROM notif";
        try (PreparedStatement preparedStatement = con.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                notif notification = new notif();
                notification.setId(resultSet.getInt("id"));
                notification.setDescription(resultSet.getString("description"));
                notifications.add(notification);
            }
        }
        return notifications;
    }
}
