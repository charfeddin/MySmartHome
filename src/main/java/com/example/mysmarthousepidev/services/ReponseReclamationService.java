

package tn.esprit.reclamationprojet.services;

import tn.esprit.reclamationprojet.entities.Reclamation;
import tn.esprit.reclamationprojet.entities.ReponseReclamation;
import tn.esprit.reclamationprojet.entities.User;
import tn.esprit.reclamationprojet.utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ReponseReclamationService {

    private final Connection cnx;
    private static ReponseReclamationService instance;

    public ReponseReclamationService() {
        cnx = MyDB.getInstance().getConnection();
    }
    
    public static ReponseReclamationService getInstance()
    {
        if (instance == null) {
            instance = new ReponseReclamationService();
        }
        return instance; 
    }
    
    
    public boolean ReclamationReponse(ReponseReclamation r) {
        int verf = 0 ;
        try{
        String req ;
        
        req="INSERT INTO `reponsereclamation`(`id_reponse`,`id_reclamation`,`contenue`,`date`) VALUES (?,?,?,?)";
            PreparedStatement res=cnx.prepareStatement(req);
        
        res.setInt(1, r.getId());
        res.setInt(2, r.getIdRec());
        res.setString(3, r.getContenu());
        res.setDate(4, (Date) r.getDate());
        verf=res.executeUpdate();
         
        
        }
        catch(SQLException e ){
            Logger.getLogger(ReponseReclamationService.class.getName()).log(Level.SEVERE,null,e);
        
        }
        if (verf==0)
        {return false;}
        else {return true;}
    }
    

        
        
           
        public boolean deleteReclamation(int id) throws SQLDataException {
        try {
            
            Statement st=cnx.createStatement();
            String req= "DELETE FROM `reponsereclamation` WHERE `id_reponse` ="+id;
            st.executeUpdate(req);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ReponseReclamationService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }    
        }
        
        
            
        public boolean modifieReclamation(ReponseReclamation res) throws SQLDataException {
               
                String query = "UPDATE reponsereclamation SET contenue=? WHERE id_reponse= ?";
		PreparedStatement st;
        try {
                st = cnx.prepareStatement(query);
                st.setString(1,res.getContenu());
                st.setInt(2,res.getId());
                st.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(ReponseReclamationService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
        
        
            
    public ReponseReclamation findReponseById(int id){
       
        ReponseReclamation r = new ReponseReclamation();
        int count =0;
        
        String requete="select * from reponsereclamation where id_reponse="+id;
         try{
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){

                r.setId(rs.getInt(1));
                r.setIdRec(rs.getInt(2));
                r.setContenu(rs.getString(3));
                r.setDate(rs.getDate(4));

                count++;
            }
            if(count == 0){
                return null;
           }else{
               return r;
            
           
        }
         }
        catch (SQLException ex) {
            Logger.getLogger(ReponseReclamationService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
           
}


    public ReponseReclamation findReponseByReclamation(int id){

        ReponseReclamation r = new ReponseReclamation();
        int count =0;

        String requete="select * from reponsereclamation where id_reclamation="+id;
        try{
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()){

                r.setId(rs.getInt(1));
                r.setIdRec(rs.getInt(2));
                r.setContenu(rs.getString(3));
                r.setDate(rs.getDate(4));

                count++;
            }
            if(count == 0){
                return null;
            }else{
                return r;


            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ReponseReclamationService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }


    }







    
}
