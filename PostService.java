package com.example.rafafx.services;

import com.example.rafafx.entities.Post;
import com.example.rafafx.utils.MyConnection;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PostService implements IService<Post>{
    public Connection cnx= MyConnection.getInstance().getCnx() ;
    @Override
    public void ajouter(Post p) {
        try {
            String req = "INSERT INTO `post` ( `titre`,`description`,`date`,`image`) VALUES (?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,p.getTitre());
            ps.setString(2,p.getDescription());
            ps.setString(3, String.valueOf(Date.valueOf(p.getDate())));

            ps.setString(4, p.getImage());




            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `post` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("post deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(Post p) {
        try {
            String req = "UPDATE `post` SET `titre` = '" + p.getTitre() + "', `description` = '" + p.getDescription() + "',`image`='"+ p.getImage() + "' WHERE `post`.`id` = " + p.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Post updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Post> getAll() {
        List<Post> list = new ArrayList<>();
        try {
            String req = "Select * from post";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                System.out.println(rs.getString("image"));
                java.sql.Date dateSQL = rs.getDate("date");
                LocalDate date = null;
                if (dateSQL != null) {
                    date = dateSQL.toLocalDate();
                }
                Post p = new Post( rs.getInt("id"),rs.getString("titre"), rs.getString("description"),rs.getString("image"),date);
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Post getTbyId(int id) {
        try {
            String req = "Select * from post where id = "+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setTitre(rs.getString("titre"));
                post.setDescription(rs.getString("description"));
                post.setImage(rs.getString("image"));
                post.setDate(rs.getDate("date").toLocalDate());

                return post;




            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }



}
