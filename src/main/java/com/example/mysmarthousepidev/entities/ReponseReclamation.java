package tn.esprit.reclamationprojet.entities;

import java.util.Date;

public class ReponseReclamation {

    private  int id ,idRec ;
    private Date Date ;
    private String contenu ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRec() {
        return idRec;
    }

    public void setIdRec(int idRec) {
        this.idRec = idRec;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public String toString() {
        return "ReponseReclamation{" +
                "id=" + id +
                ", idRec=" + idRec +
                ", Date=" + Date +
                ", contenu='" + contenu + '\'' +
                '}';
    }
}
