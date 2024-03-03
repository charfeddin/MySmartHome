/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.reclamationprojet.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import tn.esprit.reclamationprojet.entities.Reclamation;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class ReclamationItemController implements Initializable {

    @FXML
    private HBox Hbox;
    @FXML
    private Text nomuser;
    @FXML
    private Text type;
    @FXML
    private ImageView imv;


    @FXML
    private HBox hbox2 ;

    @FXML
    private Text desc ;
    
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
 
   
    }  
    
    public ReclamationItemController(){
    
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tn/esprit/reclamationprojet/ReclamationItem.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        
    }

    public HBox getBox() {
        return Hbox;
    }


    
    
    
    
        public void setInfo(Reclamation p)  {
        nomuser.setText("Sujet :"+p.getSujet());
        type.setText("Etat :"+p.getEtat());
     
}


        
}
