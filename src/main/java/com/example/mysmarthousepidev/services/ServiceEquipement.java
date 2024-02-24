package com.example.mysmarthousepidev.services;



import com.example.mysmarthousepidev.entities.Equipement;
import com.example.mysmarthousepidev.entities.Home;
import com.example.mysmarthousepidev.utils.MyDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceEquipement implements IService<Equipement> {
    private final Connection con;

    public ServiceEquipement(){
        con = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Equipement equipement) throws SQLException {
            String req = "insert into equipement (nom,description,etat,temper,id_home,nombre) values (? ,?,?,?,?,?)";

        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1,equipement.getNom());
        pre.setString(2,equipement.getDescription());
        pre.setString(3,equipement.getEtat());
        pre.setFloat(4,equipement.getTemper());
        pre.setInt(5,equipement.getId_home().getId_home());
        pre.setInt(6,equipement.getNombre());
        pre.executeUpdate(); // Corrected line
    }

    @Override
    public void modifier(Equipement equipement) throws SQLException {
        String req = "update equipement set nom=? , description=? , etat=? , temper=?, id_home=?, nombre=? where id=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(4,equipement.getId_equipement());
        pre.setString(1,equipement.getNom());
        pre.setString(2,equipement.getDescription());
        pre.setString(3,equipement.getEtat());
        pre.setFloat(4,equipement.getTemper());
        pre.setInt(4,equipement.getId_home().getId_home());
        pre.setInt(4,equipement.getNombre());
        pre.executeUpdate();


    }

    @Override
    public void supprimer(Equipement equipement) {

    }

    @Override
    public List<Equipement> afficher() throws SQLException {
        List<Equipement> equip = new ArrayList<>();

        String req = "select * from equipement";
        PreparedStatement pre = con.prepareStatement(req);
        ResultSet res= pre.executeQuery();
        while (res.next()){
            Equipement e = new Equipement();
            e.setId_equipement(res.getInt(1));
            e.setNom(res.getString("nom"));
            e.setDescription(res.getString(3));
            e.setEtat(res.getString("etat"));
            e.setTemper(res.getInt("temper"));
            //e.setId_home(res.("id_home"));
            Home home = getHomeById(res.getInt("id_home"));
            e.setId_home(home);
            e.setNombre(res.getInt("nombre"));
            equip.add(e);

        }


        return equip;
    }
    public Home getHomeById(int home_id) throws SQLException {
        Home home = null;
        String req = "SELECT * FROM home WHERE id_home = ?";
        PreparedStatement pstmt = con.prepareStatement(req);
        pstmt.setInt(1, home_id);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            home = new Home();
            home.setId_home(rs.getInt("id_home"));
            // Définissez d'autres attributs de votre objet Equipe si nécessaire
        }

        return home;
    }
}



