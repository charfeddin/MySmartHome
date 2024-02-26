package entities;

public class Pack {
    private int id;
    private int id_equipement; //
    private int id_categorie; //
    private String description;
    private float prix;
    private String dispo;
    private String nom_equipement;
    private String nom_categorie;


    public Pack(int id, int id_equipement, int id_categorie, String description, float prix, String dispo) {
        this.id = id;
        this.id_equipement = id_equipement;
        this.id_categorie = id_categorie;
        this.description = description;
        this.nom_categorie = nom_categorie;
        this.prix = prix;
        this.dispo = dispo;
    }

    public Pack() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getDispo() {
        return dispo;
    }

    public void setDispo(String dispo) {
        this.dispo = dispo;
    }

    @Override
    public String toString() {
        return "Pack{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                ", dispo='" + dispo + '\'' +
                '}';
    }


    //categorie

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    //equipement

    public int getId_equipement() {
        return id_equipement;
    }

    public void setId_equipement(int id_equipement) {
        this.id_equipement = id_equipement;
    }

    public void setNom_equipement(String nomEquipement) {
        this.nom_equipement = nomEquipement;
    }

    public String getNom_equipement() {
        return nom_equipement;
    }

    //categorie

    public void setNom_categorie(String nomCategorie) {
        this.nom_categorie = nomCategorie;
    }

    public String getNom_categorie() {
        return nom_categorie;
    }


}
