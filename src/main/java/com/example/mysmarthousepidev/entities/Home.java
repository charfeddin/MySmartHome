package com.example.mysmarthousepidev.entities;

public class Home
{
    private int id_home;
    private String desigantion;
    private String image ;
    private String adreess ;
    private String disponibilite ;

    public Home() {
    }

    public int getId_home() {
        return id_home;
    }

    public void setId_home(int id_home) {
        this.id_home = id_home;
    }

    public String getDesigantion() {
        return desigantion;
    }

    public void setDesigantion(String desigantion) {
        this.desigantion = desigantion;
    }


    public String getAdreess() {
        return adreess;
    }

    public void setAdreess(String adreess) {
        this.adreess = adreess;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    @Override
    public String toString() {
        return "Home{" +
                "id_home=" + id_home +
                ", desigantion='" + desigantion + '\'' +
                ", image='" + image + '\'' +
                ", adreess='" + adreess + '\'' +
                ", disponibilite='" + disponibilite + '\'' +
                '}';
    }
}
