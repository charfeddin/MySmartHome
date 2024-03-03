package services;

import entities.Categorie;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceCategorie implements IService<Categorie> {

    private Connection con;

    public ServiceCategorie() {
        con = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Categorie categorie) throws SQLException {
        String req = "INSERT INTO categorie (nom_categorie) VALUES (?)";
        try (PreparedStatement pre = con.prepareStatement(req)) {
            pre.setString(1, categorie.getNom_categorie());
            pre.executeUpdate();
        }
    }

    @Override
    public void modifier(Categorie categorie) throws SQLException {
        String req = "UPDATE categorie SET nom_categorie=? WHERE id_categorie=?";
        try (PreparedStatement pre = con.prepareStatement(req)) {
            pre.setString(1, categorie.getNom_categorie());
            pre.setInt(2, categorie.getId_categorie());
            pre.executeUpdate();
        }
    }

    @Override
    public void supprimer(Categorie categorie) throws SQLException {
        String req = "DELETE FROM categorie WHERE id_categorie=?";
        try (PreparedStatement pre = con.prepareStatement(req)) {
            pre.setInt(1, categorie.getId_categorie());
            pre.executeUpdate();
        }
    }

    @Override
    public List<Categorie> afficher() throws SQLException {
        List<Categorie> categories = new ArrayList<>();
        String req = "SELECT * FROM categorie";
        try (PreparedStatement pre = con.prepareStatement(req); ResultSet rs = pre.executeQuery()) {
            while (rs.next()) {
                Categorie categorie = new Categorie();
                categorie.setId_categorie(rs.getInt("id_categorie"));
                categorie.setNom_categorie(rs.getString("nom_categorie"));
                categories.add(categorie);
            }
        }
        return categories;
    }
}
