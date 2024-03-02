package services;

import entities.Commande;
import entities.CrudCommande;
import entities.Livraison;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceCommande implements CrudCommande<Commande> {
    public Connection conx;
    public Statement stm;


    public ServiceCommande() {
        conx = MyDB.getInstance().getConx();
    }

    @Override
    public void ajouter(Commande c) {
        String req =
                "INSERT INTO commande"
                        + "(statut,date,description,idProduit,idUser)"
                        + "VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setString(1, c.getStatut());
            ps.setDate(2, new java.sql.Date(c.getDate().getTime()));
            ps.setString(3, c.getDescription());
            ps.setInt(4, c.getIdProduit());
            ps.setInt(5, c.getIdUser());
            ps.executeUpdate();
            System.out.println("Commande Ajoutée !!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Commande c) {
        try {
            String req = "UPDATE commande SET statut=?, date=?, description=?, idProduit=?, idUser=? WHERE id=?";
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(6, c.getId());
            pst.setString(1, c.getStatut());
            pst.setDate(2, new java.sql.Date(c.getDate().getTime()));
            pst.setString(3, c.getDescription());
            pst.setInt(4, c.getIdProduit());
            pst.setInt(5, c.getIdUser());
            pst.executeUpdate();
            System.out.println("Commande Modifiée !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM commande WHERE id=?";
        try {
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Commande suprimée !");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Commande> Show() {
        List<Commande> list = new ArrayList<>();

        try {
            String req = "SELECT * from commande";
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Commande(rs.getInt("id"), rs.getString("statut"),
                        rs.getDate("date"), rs.getString("description"),
                        rs.getInt("idProduit"), rs.getInt("idUser")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    public Commande getById(int id){
        Commande cmd=null;
        try{
            String sql = "SELECT * from commande WHERE id=?";
            PreparedStatement pst = conx.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                cmd = new Commande(rs.getInt("id"),rs.getString("statut"),
                        rs.getDate("date"),rs.getString("description"),
                        rs.getInt("idProduit"),rs.getInt("idUser"));
            }
            return cmd;
        } catch(SQLException e){
            System.err.print(e.getMessage());
        }
        return null;
    }
}
