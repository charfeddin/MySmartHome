package entities;

import java.util.Date;

public class Commande {
    private int id;
    private String statut;
    private Date date;
    private String description;
    private int idProduit;
    private int idUser;

    public Commande(int id, String statut, Date date, String description, int idProduit, int idUser) {
        this.id = id;
        this.statut = statut;
        this.date = date;
        this.description = description;
        this.idProduit = idProduit;
        this.idUser = idUser;
    }

    public Commande(String statut, Date date, String description, int idProduit, int idUser) {
        this.statut = statut;
        this.date = date;
        this.description = description;
        this.idProduit = idProduit;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
