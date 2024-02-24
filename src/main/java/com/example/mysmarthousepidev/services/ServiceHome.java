package com.example.mysmarthousepidev.services;

import com.example.mysmarthousepidev.entities.Home;
import com.example.mysmarthousepidev.utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceHome implements IService <Home>{
    private Connection con;

    public ServiceHome(){
        con = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Home home) throws SQLException {
        String req = "insert into home (id_home,id_post,etat,disponibilite,quantite)"+
                "values (?,?,?,?,?)";
        Statement ste = con.createStatement();
        ste.executeUpdate(req);
    }

    @Override
    public void modifier(Home home) throws SQLException {
        String req = "update home set  description=? , id_post=? , etat=?, disponibilite=?, quantite=? where id=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(4,home.getId_home());
        pre.setInt(4,home.getId_post());
        pre.setString(1,home.getEtat());
        pre.setString(2,home.getDisponibilite());
        pre.setInt(3,home.getQuantite());
        pre.executeUpdate();


    }

    @Override
    public void supprimer(Home home) {

    }


    @Override
    public List<Home> afficher()
    {
        List<Home> homes =  new ArrayList<>();
        String sql="select * from home";
        PreparedStatement ste ;
        try
        {
            ste=con.prepareStatement(sql);

            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Home p = new Home();
                p.setId_post(rs.getInt("id_post"));
                p.setDisponibilite(rs.getString("disponibilite"));
                p.setEtat(rs.getString("etat"));
                p.setQuantite(rs.getInt("quantite"));
                homes.add(p);
                //System.out.println("Afficher avec succés !");
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(homes+"hhhh");
        return homes;
    }
}



