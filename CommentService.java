package com.example.rafafx.services;

import com.example.rafafx.entities.Comment;
import com.example.rafafx.entities.Post;
import com.example.rafafx.utils.MyConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommentService implements IService<Comment>{
    private static boolean validateInput(String input) {
        Pattern pattern = Pattern.compile("[a-zA-Z ]*");
        Matcher match = pattern.matcher(input);
        return match.matches();

    }
    public static List<String> inappropriateWords = Arrays.asList("badword1", "badword2", "badword3");
    public static Connection cnx= MyConnection.getInstance().getCnx() ;
    public static String filterInput(String input) {
        String filteredInput = input;
        for (String word : inappropriateWords) {
            String regex = "(?i)\\b" + Pattern.quote(word) + "\\b";
            filteredInput = filteredInput.replaceAll(regex, "*****");
        }
        return filteredInput;
    }


    public static List<Comment> getCommentsForPost(int postId) {
        List<Comment> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM comment WHERE idPost = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, postId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setContent(rs.getString("content"));

                //Post post = PostService.getPostById(rs.getInt("idPost"));
                //comment.setPost(post);
                list.add(comment);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }


    @Override
    public void ajouter(Comment p) {
        try {
            String req = "INSERT INTO `comment` ( `content`,`idPost`) VALUES (?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,p.getContent());
            ps.setInt(2,p.getPost().getId());





            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `comment` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("comment deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(Comment p) {
        try {
            String req = "UPDATE `comment` SET `content` = '" + p.getContent() + "' WHERE `comment`.`id` = " + p.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("comment updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Comment> getAll() {
        return null;
    }

    @Override
    public Comment getTbyId(int id) {
        return null;
    }
}
