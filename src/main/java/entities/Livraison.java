package entities;

public class Livraison {
    private int id;
    private int prix;
    private String adresse;
    private int idCommande;

    public Livraison(int id, int prix, String adresse, int idCommande) {
        this.id = id;
        this.prix = prix;
        this.adresse = adresse;
        this.idCommande = idCommande;
    }

    public Livraison(int prix, String adresse, int idCommande) {
        this.prix = prix;
        this.adresse = adresse;
        this.idCommande = idCommande;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }
}
