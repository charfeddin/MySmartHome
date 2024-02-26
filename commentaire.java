package entities;

public class commentaire {
    int id_commentaire;
    String contenu;


    public commentaire(int id_commentaire, String contenu) {
        this.id_commentaire = id_commentaire;
        this.contenu = contenu;
    }

    public commentaire(String contenu) {
        this.contenu = contenu;
    }

    public commentaire(){

    }

    @Override
    public String toString() {
        return "commentaire{" +
                "id_commentaire=" + id_commentaire +
                ", contenu='" + contenu + '\'' +
                '}';
    }


    public int getId_commentaire() {
        return id_commentaire;
    }

    public void setId_commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
}

