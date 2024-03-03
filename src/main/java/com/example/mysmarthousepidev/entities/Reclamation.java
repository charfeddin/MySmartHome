/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.reclamationprojet.entities;


import java.util.Date;


public class Reclamation {
    
    private int idrec ,idUser ;
    private Date dateenv ;
    private String etat , sujet ,contenue,image,type ;

    public int getIdrec() {
        return idrec;
    }

    public void setIdrec(int idrec) {
        this.idrec = idrec;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Date getDateenv() {
        return dateenv;
    }

    public void setDateenv(Date dateenv) {
        this.dateenv = dateenv;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getContenue() {
        return contenue;
    }

    public void setContenue(String contenue) {
        this.contenue = contenue;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "idrec=" + idrec +
                ", idUser=" + idUser +
                ", dateenv=" + dateenv +
                ", etat='" + etat + '\'' +
                ", sujet='" + sujet + '\'' +
                ", contenue='" + contenue + '\'' +
                ", image='" + image + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
