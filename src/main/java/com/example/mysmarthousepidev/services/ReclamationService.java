

package tn.esprit.reclamationprojet.services;

import tn.esprit.reclamationprojet.entities.Reclamation;
import tn.esprit.reclamationprojet.entities.User;
import tn.esprit.reclamationprojet.utils.MyDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ReclamationService {
  
    private final Connection cnx;
    private static ReclamationService instance;
    
    public ReclamationService() {
        cnx = MyDB.getInstance().getConnection();
    }
    
    public static ReclamationService getInstance()
    {
        if (instance == null) {
            instance = new ReclamationService();
        }
        return instance; 
    }
    
    
    public boolean Reclamation(Reclamation r) {
        int verf = 0 ;
        try{
        String req ;
        
        req="INSERT INTO `reclamation`(`sujet`,`contenue`,`id_user`,`dateenv`,`etat`,`image`,`type`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement res=cnx.prepareStatement(req);
        
        res.setString(1, r.getSujet());
        res.setString(2, r.getContenue());
        res.setInt(3, r.getIdUser());
        res.setDate(4, (Date) r.getDateenv());
        res.setString(5, r.getEtat());
        res.setString(6, r.getImage());
        res.setString(7, r.getType());
        verf=res.executeUpdate();
         
        
        }
        catch(SQLException e ){
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE,null,e);
        
        }
        if (verf==0)
        {return false;}
        else {return true;}
    }
    
        public List<Reclamation> getMesclamation(int id){
        
        List<Reclamation> list = new ArrayList<Reclamation>();
        int count =0;
        
        String requete="select * from reclamation where id_user ="+id;
         try{
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                Reclamation r = new Reclamation();
                r.setIdrec(rs.getInt(1));
                r.setSujet(rs.getString(2));
                r.setContenue(rs.getString(3));
                r.setIdUser(rs.getInt(4));
                r.setDateenv(rs.getDate(5));
                r.setEtat(rs.getString(6));
                r.setImage(rs.getString(7));
                r.setType(rs.getString(8));

                list.add(r);
                
                count++;
            }
            if(count == 0){
                return null;
           }else{
               return list;
            
           
        }
         }
        catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
           
}
        
        
           

        
        
            
        public void modifieReclamation(Reclamation res) throws SQLDataException {
               
                String query = "UPDATE reclamation SET sujet=?,contenue=?,etat=?,image=?,type=? WHERE id_reclamation = ?";
		PreparedStatement st;
        try {
                st = cnx.prepareStatement(query);
                st.setString(1,res.getSujet());
                st.setString(2,res.getContenue());
                st.setString(3,res.getEtat());
                st.setString(4,res.getImage());
                st.setString(5,res.getType());
                st.setInt(6,res.getIdrec());

                st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        
            
    public Reclamation findReclamationById(int id){
       
        Reclamation r = new Reclamation();
        int count =0;
        
        String requete="select * from reclamation where id_reclamation="+id;
         try{
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){

                r.setIdrec(rs.getInt(1));
                r.setSujet(rs.getString(2));
                r.setContenue(rs.getString(3));
                r.setIdUser(rs.getInt(4));
                r.setDateenv(rs.getDate(5));
                r.setEtat(rs.getString(6));
                r.setImage(rs.getString(7));
                r.setType(rs.getString(8));
                count++;
            }
            if(count == 0){
                return null;
           }else{
               return r;
            
           
        }
         }
        catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
           
}
    
     public List<Reclamation> Allreclamation(){
        
        List<Reclamation> list = new ArrayList<Reclamation>();
        int count =0;
        
        String requete="select * from reclamation";
         try{
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                Reclamation r = new Reclamation();
                r.setIdrec(rs.getInt(1));
                r.setSujet(rs.getString(2));
                r.setContenue(rs.getString(3));
                r.setIdUser(rs.getInt(4));
                r.setDateenv(rs.getDate(5));
                r.setEtat(rs.getString(6));
                r.setImage(rs.getString(7));
                r.setType(rs.getString(8));
 
                
                list.add(r);
                
                count++;
            }
            if(count == 0){
                return null;
           }else{
               return list;
            
           
        }
         }
        catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
           
}
     
     
  public boolean Traiter(int id) throws SQLDataException {
               
                String query = "UPDATE reclamation SET etat=? WHERE id_reclamation="+id;
		PreparedStatement st;
        try {
                st = cnx.prepareStatement(query);
                st.setString(1,"Traité");
                st.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public User findUserById(int id){

        User user = new User();
        int count =0;

        String requete="select * from user where id_user="+id;
        try{
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()){

                user.setId(rs.getInt(1));
                user.setNom(rs.getString(3));

                count++;
            }
            if(count == 0){
                return null;
            }else{
                return user;


            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }


    }




    public int getNbrReclamation(String type) throws SQLException {

            int count =0;

            String requete="select * from reclamation where type='"+type+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()){
                count++;
            }
        System.out.println("hhhh"+count);
            return count;



    }

    public boolean deleteReclamation(int id) throws SQLDataException {
        try {

            Statement st=cnx.createStatement();
            String req= "DELETE FROM `reclamation` WHERE `id_reclamation`="+id;
            st.executeUpdate(req);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ReponseReclamationService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }


}
