package com.example.mysmarthousepidev.entities;

public class Equipement
{
   private int id_equipement;
    private String nom;
    private String description;
    private String etat;
    private float temper;
    private Home id_home;
    private int nombre;
    public Equipement(){}

    public Equipement(int id_equipement, String nom, String description, String etat, float temper, Home id_home, int nombre) {
        this.id_equipement = id_equipement;
        this.nom = nom;
        this.description = description;
        this.etat = etat;
        this.temper = temper;
        this.id_home = id_home;
        this.nombre = nombre;
    }

    public Equipement(String nom, String description, String etat, float temper, Home id_home, int nombre) {
        this.nom = nom;
        this.description = description;
        this.etat = etat;
        this.temper = temper;
        this.id_home = id_home;
        this.nombre = nombre;
    }

    public int getId_equipement() {
        return id_equipement;
    }

    public void setId_equipement(int id_equipement) {
        this.id_equipement = id_equipement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public float getTemper() {
        return temper;
    }

    public void setTemper(float temper) {
        this.temper = temper;
    }

    public Home getId_home() {
        return id_home;
    }

    public void setId_home(Home home) {
        this.id_home = home;
    }
    @Override
    public String toString() {
        return "Equipement{" +
                "id_equipement=" + id_equipement +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", etat='" + etat + '\'' +
                ", temper=" + temper +
                ", id_home=" + id_home +
                ", nombre=" + nombre +
                '}';
    }
}

