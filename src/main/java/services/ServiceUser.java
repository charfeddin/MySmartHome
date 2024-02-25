package services;

import entities.IUser;
import entities.User;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceUser implements IUser<User> {

    Connection cnx = MyDB.getInstance().getCnx();

    @Override
    public void ajouter(User user) {
        try {
            String reqs = "INSERT INTO user(cin, nom, prenom, numero, email, mdp, role) " +
                    "VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(reqs);
            ps.setInt(1, user.getCin());
            ps.setString(2, user.getNom());
            ps.setString(3, user.getPrenom());
            ps.setInt(4, user.getNumero());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getMdp());
            ps.setString(7, user.getRole());
            ps.executeUpdate();
            System.out.println("Utilisateur ajoutée !");

        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM user WHERE id_user = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("User deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(User user) {
        try {
            String req = "UPDATE user SET cin = '" + user.getCin() + "', nom = '" + user.getNom() + "', prenom = '"
                    + user.getPrenom() + "', numero = '" + user.getNumero() + "', email = '" + user.getEmail()
                    + "', mdp = '" + user.getMdp() + "', role = '" + user.getRole() + "' WHERE id_user = "
                    + user.getId_user();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("User updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM user";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                User user = new User(rs.getInt("id_user"),rs.getInt("cin"), rs.getString("nom"),
                        rs.getString("prenom"), rs.getInt("numero"), rs.getString("email"), rs.getString("mdp"),
                        rs.getString("role"));
                list.add(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public String login(User t) {
        String message="";
        String role = "";
        try {
            if (!t.getEmail().equals("") && !t.getMdp().equals("")) {
                String req = "SELECT * from user";
                PreparedStatement pst = cnx.prepareStatement(req);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    if (rs.getString("email").equals(t.getEmail())) {
                            int idUser = rs.getInt("id_user");
                            int cin = rs.getInt("cin");
                            String nom = rs.getString("nom");
                            String prenom = rs.getString("prenom");
                            int num = rs.getInt("numero");
                            String email = rs.getString("email");
                            role = rs.getString("role");
                            System.out.println(" Salut :" + nom);
                    } else {
                        System.err.println("Verifier vos donneés !");
                    }
                }
                // Add a failure message if the loop did not find a match
                if (role.equals("")) {
                    message = "Verifier vos donnes";
                }
            } else {
                System.out.println("champs vide");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        // Return the role variable if successful, otherwise return the message variable
        return role.equals("") ? message : role;
    }

    @Override
    public User getOneById(int id) {
        User user = null;
        try {
            String req = "SELECT * FROM user WHERE id_user = " + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            if (rs.next()) {
                user = new User(rs.getInt("id_user"),rs.getInt("cin"), rs.getString("nom"), rs.getString("prenom"),
                        rs.getInt("numero"), rs.getString("email"), rs.getString("mdp"), rs.getString("role"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return user;
    }

    public User getOneByEmail(String email) {
        User user = null;
        try {
            String req = "SELECT * FROM user WHERE id_user = " + email;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            if (rs.next()) {
                user = new User(rs.getInt("id_user"),rs.getInt("cin"), rs.getString("nom"), rs.getString("prenom"),
                        rs.getInt("numero"), rs.getString("email"), rs.getString("mdp"), rs.getString("role"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return user;
    }
    public List<User> afficherParRole(String role){
        List<User> list = new ArrayList<>() ; // Initialize the ObservableList
        String req = "SELECT * FROM `utilisateurs` WHERE `role`=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, role);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt("id_user"),rs.getInt("cin"), rs.getString("nom"), rs.getString("prenom"),
                        rs.getInt("numero"), rs.getString("email"), rs.getString("mdp"), rs.getString("role"));
                list.add(user);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }
    public boolean utilisateurLoggedIn(String email, String mdp) throws SQLException {
        String req = "SELECT * FROM `user` WHERE email=? AND mdp=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, email);
        ps.setString(2, mdp);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public boolean emailExists(String email) {
        try {
            Connection conn = MyDB.getInstance().getCnx();
            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) AS count FROM user WHERE email=?");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int count = rs.getInt("count");
            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
