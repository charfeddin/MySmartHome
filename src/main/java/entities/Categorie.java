package entities;

public class Categorie {
    private int id_categorie;
    private String nom_categorie;

    public Categorie() {
    }

    public Categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    // Ajoutez les getters et les setters appropriés
    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getNom_categorie() {
        return nom_categorie;
    }

    public void setNom_categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }
}
