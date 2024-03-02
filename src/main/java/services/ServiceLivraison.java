package services;

import entities.CrudLivraison;
import entities.Livraison;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceLivraison implements CrudLivraison<Livraison> {
    public Connection conx;
    public Statement stm;


    public ServiceLivraison() {
        conx = MyDB.getInstance().getConx();
    }

    @Override
    public void ajouter(Livraison l) {
        String req =
                "INSERT INTO livraison"
                        + "(prix,adresse,idCommande)"
                        + "VALUES(?,?,?)";
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setInt(1, l.getPrix());
            ps.setString(2, l.getAdresse());
            ps.setInt(3, l.getIdCommande());
            ps.executeUpdate();
            System.out.println("Livraison Ajoutée !!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Livraison l) {
        try {
            String req = "UPDATE livraison SET prix=?, adresse=?, idCommande=? WHERE id=?";
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(4, l.getId());
            pst.setInt(1, l.getPrix());
            pst.setString(2, l.getAdresse());
            pst.setInt(3, l.getIdCommande());
            pst.executeUpdate();
            System.out.println("Livraison Modifiée !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM livraison WHERE id=?";
        try {
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Livraison suprimée !");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Livraison> Show() {
        List<Livraison> list = new ArrayList<>();

        try {
            String req = "SELECT * from livraison";
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Livraison(rs.getInt("id"), rs.getInt("prix"),
                        rs.getString("adresse"), rs.getInt("idCommande")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
}
