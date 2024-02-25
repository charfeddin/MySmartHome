package com.example.mysmarthousepidev.services;

import com.example.mysmarthousepidev.entities.Home;
import com.example.mysmarthousepidev.utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceHome implements IService <Home>{
    private Connection con;

    public ServiceHome(){
        con = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Home home) throws SQLException {
        String req = "insert into home (adresse,disponibilite,designation,image)"+
                "values (?,?,?,?)";
        Statement ste = con.createStatement();
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1,home.getAdreess());
        pre.setString(2,home.getDisponibilite());
        pre.setString(3,home.getDesigantion());
        pre.setString(4,home.getImage());
        pre.executeUpdate(); // Corrected line

    }

    @Override
    public void modifier(Home home) throws SQLException {
        String req = "update home set  adresse=? , designation=?, disponibilite=?, image=? where id_home=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(5,home.getId_home());
        pre.setString(4,home.getImage());
        pre.setString(1,home.getAdreess());
        pre.setString(2,home.getDesigantion());
        pre.setString(3,home.getDisponibilite());
        pre.executeUpdate();


    }

    @Override
    public void supprimer(int id) {
        try {

            Statement st = con.createStatement();
            String req = "DELETE FROM `home` WHERE `id_home` =" + id;
            st.executeUpdate(req);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceHome.class.getName()).log(Level.SEVERE, null, ex);

        }
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
                p.setId_home(rs.getInt("id_home"));
                p.setDisponibilite(rs.getString("disponibilite"));
                p.setDesigantion(rs.getString("designation"));
                p.setAdreess(rs.getString("adresse"));
                p.setImage(rs.getString("image"));
                homes.add(p);
                //System.out.println("Afficher avec succés !");
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return homes;
    }


    public Home getHomeById(int id)
    {
        Home home = new Home();
        String sql="select * from home where id_home="+id;
        PreparedStatement ste ;
        try
        {
            ste=con.prepareStatement(sql);

            ResultSet rs=ste.executeQuery();
            while(rs.next()){

                home.setId_home(rs.getInt("id_home"));
                home.setDisponibilite(rs.getString("disponibilite"));
                home.setDesigantion(rs.getString("designation"));
                home.setAdreess(rs.getString("adresse"));
                home.setImage(rs.getString("image"));

                //System.out.println("Afficher avec succés !");
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("hommme"+home);
        return home;
    }
}



