/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.reclamationprojet.controller;




import javafx.scene.control.ListCell;
import tn.esprit.reclamationprojet.entities.Reclamation;


public class ListViewReclamation extends ListCell<Reclamation> {
    
    
     @Override
     public void updateItem(Reclamation e, boolean empty)
    {
        super.updateItem(e,empty);
        if(e != null)
        {
            
            ReclamationItemController data = new ReclamationItemController();
            data.setInfo(e);
            setGraphic(data.getBox());
        }
    }
    
}
