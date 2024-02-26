package services;

import entities.post;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicePost implements IService <post> {
    private Connection con;

    public ServicePost() {
        con = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(post post) throws SQLException {
        String req = "INSERT INTO post (id_commentaire, titre, commentaire, date_pub) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ste = con.prepareStatement(req)) {
            ste.setInt(1, post.getId_commentaire());
            ste.setString(2, post.getTitre());
            ste.setString(3, post.getCommentaire()); // C'est probablement ici que l'erreur se produit
            ste.setString(4, post.getDate_pub());
            ste.executeUpdate();
        }
    }




    @Override
    public void modifier(post post) throws SQLException {
        String req = "update post set titre=? , commentaire=? , date_pub=?  where id_post =?";
        try (PreparedStatement poststmt = con.prepareStatement(req)) {
            poststmt.setString(1, post.getTitre());
            poststmt.setString(2, post.getCommentaire());
            poststmt.setString(3, post.getDate_pub());
            poststmt.setInt(4, post.getId_post());

            poststmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error Updating post: " + e.getMessage());
        }
    }


    @Override
    public void supprimer(post post) throws SQLException {
        String Req = "DELETE FROM post WHERE id_post = ?";
        try (PreparedStatement poststmt = con.prepareStatement(Req)) {
            poststmt.setInt(1, post.getId_post());
            poststmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting post: " + e.getMessage());
        }
    }

    @Override
    public List<post> afficher() throws SQLException {


        List<post> pers = new ArrayList<>();

        String req = "select * from post";
        PreparedStatement prep = con.prepareStatement(req);
        ResultSet res = prep.executeQuery();
        while (res.next()) {
            post p = new post();
            p.setId_post(res.getInt(1));
            /*p.setId_user(res.getInt(2));*/
            p.setTitre(res.getString(3));
            p.setCommentaire(res.getString(4));
            p.setDate_pub(res.getString(5));
            pers.add(p);
        }


        return pers;
    }
    public List<String> fetchcontenuFromDatabase() throws SQLException {
        List<String> contenu = new ArrayList<>();
        String req = "SELECT contenu FROM commentaire";
        try (PreparedStatement pre = con.prepareStatement(req); ResultSet res = pre.executeQuery()) {
            while (res.next()) {
                contenu.add(res.getString("contenu"));
            }
        }
        return contenu;
    }
    public int fetchIdCommentairetByName(String contenu) throws SQLException {
        String req = "SELECT id_commentaire FROM commentaire WHERE contenu=?";
        try (PreparedStatement pre = con.prepareStatement(req)) {
            pre.setString(1, contenu);
            try (ResultSet res = pre.executeQuery()) {
                if (res.next()) {
                    return res.getInt("id_commentaire");
                }
            }
        }
        throw new SQLException("commentaire notfound: " + contenu);

    }
}

