package services;

import entities.Pack;
import utils.MyDB;
import services.IService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicePack implements IService<Pack> {

    private Connection con;

    public ServicePack(){
        con = MyDB.getInstance().getConnection();
    }


    @Override
    public void ajouter(Pack pack) throws SQLException {
        String req = "INSERT INTO pack (id_equipement, id_categorie, description, prix, dispo) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, pack.getId_equipement());
        pre.setInt(2, pack.getId_categorie()); // Utilisez directement l'ID de la catégorie
        pre.setString(3, pack.getDescription());
        pre.setFloat(4, pack.getPrix());
        pre.setString(5, pack.getDispo());

        pre.executeUpdate();
    }




    @Override
    public void modifier(Pack pack) throws SQLException {
        String req = "UPDATE pack SET id_categorie=?, description=?, prix=?, dispo=? WHERE id_pack=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, pack.getId_categorie()); // Utilisez directement l'ID de la catégorie
        pre.setString(2, pack.getDescription());
        pre.setFloat(3, pack.getPrix());
        pre.setString(4, pack.getDispo());
        pre.setInt(5, pack.getId());

        pre.executeUpdate();
    }


    @Override
    public void supprimer(Pack pack) throws SQLException {
        String req = "DELETE FROM pack WHERE id_pack=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, pack.getId());

        pre.executeUpdate();
    }

    @Override
    public List<Pack> afficher() throws SQLException {
        List<Pack> packs = new ArrayList<>();
        String req = "SELECT p.*, po.nom_equipement AS nom_equipement, c.nom_categorie AS nom_categorie " +
                "FROM pack p " +
                "JOIN equipement po ON p.id_equipement = po.id_equipement " +
                "JOIN categorie c ON p.id_categorie = c.id_categorie";
        try (PreparedStatement pre = con.prepareStatement(req); ResultSet res = pre.executeQuery()) {
            while (res.next()) {
                Pack pack = new Pack();
                pack.setId(res.getInt("id_pack"));
                pack.setDescription(res.getString("description"));
                pack.setPrix(res.getFloat("prix"));
                pack.setDispo(res.getString("dispo"));
                pack.setNom_equipement(res.getString("nom_equipement"));
                pack.setNom_categorie(res.getString("nom_categorie")); // Ajout du nom de catégorie
                packs.add(pack);
            }
        }
        return packs;
    }

    public List<String> fetchNomEquipementsFromDatabase() throws SQLException {
        List<String> nomEquipements = new ArrayList<>();
        String req = "SELECT nom_equipement FROM equipement";
        try (PreparedStatement pre = con.prepareStatement(req); ResultSet res = pre.executeQuery()) {
            while (res.next()) {
                nomEquipements.add(res.getString("nom_equipement"));
            }
        }
        return nomEquipements;
    }

    public int fetchIdEquipementByName(String nomEquipement) throws SQLException {
        String req = "SELECT id_equipement FROM equipement WHERE nom_equipement=?";
        try (PreparedStatement pre = con.prepareStatement(req)) {
            pre.setString(1, nomEquipement);
            try (ResultSet res = pre.executeQuery()) {
                if (res.next()) {
                    return res.getInt("id_equipement");
                }
            }
        }
        throw new SQLException("Equipment not found: " + nomEquipement);
    }

    public List<String> fetchEquipmentsByDescription(String description) throws SQLException {
        List<String> equipements = new ArrayList<>();
        String req = "SELECT nom_equipement FROM equipement WHERE id_equipement IN (SELECT id_equipement FROM pack WHERE description = ?)";
        try (PreparedStatement pre = con.prepareStatement(req)) {
            pre.setString(1, description);
            try (ResultSet res = pre.executeQuery()) {
                while (res.next()) {
                    equipements.add(res.getString("nom_equipement"));
                }
            }
        }
        return equipements;
    }

    public int fetchIdCategorietByName(String nomCategorie) throws SQLException {
        String req = "SELECT id_categorie FROM categorie WHERE nom_categorie=?";
        try (PreparedStatement pre = con.prepareStatement(req)) {
            pre.setString(1, nomCategorie);
            try (ResultSet res = pre.executeQuery()) {
                if (res.next()) {
                    return res.getInt("id_categorie");
                }
            }
        }
        throw new SQLException("categorie not found: " + nomCategorie);
    }


    public List<String> fetchNomCategoriesFromDatabase() throws SQLException {
        List<String> nomCategories = new ArrayList<>();
        String req = "SELECT nom_categorie FROM categorie";
        try (PreparedStatement pre = con.prepareStatement(req); ResultSet res = pre.executeQuery()) {
            while (res.next()) {
                nomCategories.add(res.getString("nom_categorie"));
            }
        }
        return nomCategories;
    }

    public int fetchIdEquipementById(int packId) throws SQLException {
        String req = "SELECT id_equipement FROM pack WHERE id_pack=?";
        try (PreparedStatement pre = con.prepareStatement(req)) {
            pre.setInt(1, packId);
            try (ResultSet res = pre.executeQuery()) {
                if (res.next()) {
                    return res.getInt("id_equipement");
                }
            }
        }
        throw new SQLException("Equipment ID not found for pack ID: " + packId);
    }

    public void supprimerPacksParDescription(String description) throws SQLException {
        String req = "DELETE FROM pack WHERE description=?";
        try (PreparedStatement pre = con.prepareStatement(req)) {
            pre.setString(1, description);
            pre.executeUpdate();
        }
    }





}

