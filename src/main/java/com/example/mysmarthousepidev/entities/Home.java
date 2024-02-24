package com.example.mysmarthousepidev.entities;

public class Home
{
    int id_home;
    int id_post;
    String etat;
    String disponibilite ;
    int quantite ;

    public Home(int id_home, int id_post, String etat, String disponibilite, int quantite) {
        this.id_home = id_home;
        this.id_post = id_post;
        this.etat = etat;
        this.disponibilite = disponibilite;
        this.quantite = quantite;
    }

    public Home(int id_post, String etat, String disponibilite, int quantite) {
        this.id_post = id_post;
        this.etat = etat;
        this.disponibilite = disponibilite;
        this.quantite = quantite;
    }
    public Home(){}

    public int getId_home() {
        return id_home;
    }

    public void setId_home(int id_home) {
        this.id_home = id_home;
    }

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Home{" +
                "id_home=" + id_home +
                ", id_post=" + id_post +
                ", etat='" + etat + '\'' +
                ", disponibilite='" + disponibilite + '\'' +
                ", quantite=" + quantite +
                '}';
    }
}
