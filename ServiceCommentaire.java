package services;


import entities.commentaire;
import entities.post;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;




public class ServiceCommentaire implements IService<commentaire> {

    public static List<commentaire> afficher;
    private Connection con;

    public ServiceCommentaire() {
        con = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(commentaire commentaire) throws SQLException {
        String req = "INSERT INTO commentaire (id_commentaire, contenu) VALUES (?,?)";
        try (PreparedStatement ste = con.prepareStatement(req)) {
            ste.setInt(1, commentaire.getId_commentaire());
            ste.setString(2, commentaire.getContenu());

            ste.executeUpdate();
        }
    }

    @Override
    public void modifier(commentaire commentaire) throws SQLException {

    }

    @Override
    public void supprimer(commentaire commentaire) throws SQLException {

    }

    @Override
    public List<commentaire> afficher() throws SQLException {
        List<commentaire> commentaires = new ArrayList<>();
        String query = "SELECT id_commentaire, contenu FROM commentaire"; // Update query as per your database schema

        try (Connection connection = MyDB.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                commentaire commentaire = new commentaire();
                commentaire.setId_commentaire(resultSet.getInt("id_commentaire"));
                commentaire.setContenu(resultSet.getString("contenu"));
                commentaires.add(commentaire);
            }
        }

        return commentaires;
    }
}


