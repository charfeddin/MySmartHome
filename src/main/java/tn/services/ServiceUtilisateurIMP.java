/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.services;

import tn.entities.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.utils.MyDB;

/**
 *
 * @author ahmed_jemai
 */
public class ServiceUtilisateurIMP implements Iutilisateur<Utilisateur> {

    Connection cnx;

    public ServiceUtilisateurIMP() {

        cnx = MyDB.getInstance().getConnection();

    }

    @Override
    public void ajoutUtilisateur(Utilisateur t) {
        try {
            String req = "INSERT INTO user1 (email , roles , password ,nom , prenom , photo , cin , region , ville , adresse , isactive , is_verified ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, t.getEmail());
            ps.setString(2, t.getRoles());
            ps.setString(3, t.getPassword());
            ps.setString(4, t.getNom());
            ps.setString(5, t.getPrenom());
            ps.setString(6, t.getPhoto());
            ps.setInt(7, t.getCin());
            ps.setString(8, t.getRegion());
            ps.setString(9, t.getVille());
            ps.setString(10, t.getAdress());
            ps.setString(11, t.getIsactive());
            ps.setBoolean(12, t.getIs_verified());

            int value = ps.executeUpdate();
            if (value > 0) {
                System.out.println(" l insertion de l utilisateur :" + t.getNom() + " " + t.getPrenom() + " a ete effectuer avec sucess ");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void modifierUtilisateur(Utilisateur t, int id) {

        try {
            String req = "UPDATE user1 set email = ? , roles = ?, password = ? , nom = ? ,prenom = ?, photo = ?, cin= ?, region = ? , ville = ? , adresse = ?, isactive = ? , is_verified = ?   WHERE id =" + id;
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, t.getEmail());
            ps.setString(2, t.getRoles());
            ps.setString(3, t.getPassword());
            ps.setString(4, t.getNom());
            ps.setString(5, t.getPrenom());
            ps.setString(6, t.getPhoto());
            ps.setInt(7, t.getCin());
            ps.setString(8, t.getRegion());
            ps.setString(9, t.getVille());
            ps.setString(10, t.getAdress());
            ps.setString(11, t.getIsactive());
            ps.setBoolean(12, t.getIs_verified());

            int value_update = ps.executeUpdate();
            if (value_update > 0) {
                System.out.println(" la modification de l utilisateur :" + t.getNom() + " " + t.getPrenom() + " a ete effectuer avec sucess");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void supprimerUtilisateur(int id) {
        try {

            String req = "delete from user1 where id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            int value_supp = ps.executeUpdate();
            if (value_supp > 0) {
                System.out.println(" Suppression a ete effectuer avec sucess");
            }

        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Utilisateur> afficherUtilisateurs() {
        List<Utilisateur> list_Utilisateur = new ArrayList<>();

        try {
            String req = "select * from user1";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Utilisateur user1 = new Utilisateur();
                user1.setId(rs.getInt(1));
                user1.setEmail(rs.getString("email"));
                user1.setRoles(rs.getString("roles"));
                user1.setPassword(rs.getString("password"));
                user1.setNom(rs.getString("nom"));
                user1.setPrenom(rs.getString("prenom"));
                user1.setPhoto(rs.getString("photo"));
                user1.setCin(rs.getInt("cin"));
                user1.setRegion(rs.getString("region"));
                user1.setVille(rs.getString("ville"));
                user1.setAdress(rs.getString("adresse"));
                user1.setIsactive(rs.getString("isactive"));
                user1.setIs_verified(rs.getBoolean("is_verified"));
                list_Utilisateur.add(user1);

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return list_Utilisateur;

    }

    public void Descativeruser(int id) {
        try {
            String req = "Update user1 set isactive = 'desactive' where id =" + id;
            PreparedStatement ps = cnx.prepareStatement(req);
            int value_update = ps.executeUpdate();
            if (value_update > 0) {
                System.out.println(" la desactivation de l utilisateur   a ete effectuer avec sucess");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateurIMP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void Activeruser(int id) {
        try {
            String req = "Update user1 set isactive = 'active' where id =" + id;
            PreparedStatement ps = cnx.prepareStatement(req);
            int value_update = ps.executeUpdate();
            if (value_update > 0) {
            System.out.println(" la activation de l utilisateur   a ete effectuer avec sucess");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateurIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String FindNomuserById(int id) {
        String nom = null;
        try {
            String req = "select * from user1 where id =" + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                nom = rs.getString(4);
                System.out.println(nom);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateurIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nom;
    }

    public Map<String, Integer> countusersByRole() {
        Map<String, Integer> list = new HashMap<>();
        try {
            String req = "select COUNT(*) as count, roles from user1 GROUP BY roles";
            Statement st = cnx.prepareStatement(req);
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.put(rs.getString("roles"), rs.getInt("count"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateurIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public String getUtilisateurRole(int id) {
        String role = null;
        try {
            String req = "SELECT roles FROM user1 WHERE id=" + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                role = rs.getString("roles");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateurIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(role);
        return role;
    }

    public Utilisateur FindByEmail(String email) {
        Utilisateur user1 = new Utilisateur();
        try {
            String req = "SELECT * FROM user1 WHERE email ='" + email + "'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                user1.setId(rs.getInt("id"));
                user1.setEmail(rs.getString("email"));
                user1.setRoles(rs.getString("roles"));
                user1.setPassword(rs.getString("password"));
                user1.setNom(rs.getString("nom"));
                user1.setPrenom(rs.getString("prenom"));
                user1.setPhoto(rs.getString("photo"));
                user1.setCin(rs.getInt("cin"));
                user1.setRegion(rs.getString("region"));
                user1.setVille(rs.getString("ville"));
                user1.setAdress(rs.getString("adresse"));
                user1.setIsactive(rs.getString("isactive"));
                user1.setIs_verified(rs.getBoolean("is_verified"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateurIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user1;
    }
    
    
    
        public Utilisateur FindByEmailAndPassword(String email, String password) {
        Utilisateur user1 = new Utilisateur();

        try {
            String req = "SELECT * from user1 where login='" + email + "'AND password='" + password + "'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

             user1.setId(rs.getInt("id"));
                user1.setEmail(rs.getString("email"));
                user1.setRoles(rs.getString("roles"));
                user1.setPassword(rs.getString("password"));
                user1.setNom(rs.getString("nom"));
                user1.setPrenom(rs.getString("prenom"));
                user1.setPhoto(rs.getString("photo"));
                user1.setCin(rs.getInt("cin"));
                user1.setRegion(rs.getString("region"));
                user1.setVille(rs.getString("ville"));
                user1.setAdress(rs.getString("adresse"));
                user1.setIsactive(rs.getString("isactive"));
                user1.setIs_verified(rs.getBoolean("is_verified"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateurIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user1;

    }
        
     public String encrypt(String password) {

        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    public String decrypt(String password) {

        return new String(Base64.getMimeDecoder().decode(password));
    }
    
      public Utilisateur Authentification(String email, String password) {
        Utilisateur user1 = null;
// 

        try {
            String req = "select * from user1 where email = ? and password = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user1 = new Utilisateur(rs.getInt(1), 
                                        rs.getString(2),
                                        rs.getString(3),
                                        rs.getString(4),
                                        rs.getString(5),
                                        rs.getString(6),
                                        rs.getString(7),
                                        rs.getInt(8),
                                        rs.getString(9),
                                        rs.getString(10),
                                        rs.getString(11),
                                        rs.getString(12),
                                        rs.getBoolean(13));
                                       

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateurIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(user1);
        return user1;

    }
      
      
        public void ChangerPassword(int id, String nouveauPass) {
        String passCrypter = encrypt(nouveauPass);

        try {
            String req = "update user1 set password='" + passCrypter + "'where id='" + id + "'";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateurIMP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    

}
