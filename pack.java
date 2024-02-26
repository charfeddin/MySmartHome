package entities;

public class pack {

int id_pack, id_post,prix,disponibilite;
String description;


    public pack(int id_pack, int id_post, int prix, int disponibilite, String description) {
        this.id_pack = id_pack;
        this.id_post = id_post;
        this.prix = prix;
        this.disponibilite = disponibilite;
        this.description = description;
    }

    public pack(int prix, int disponibilite, String description) {
        this.prix = prix;
        this.disponibilite = disponibilite;
        this.description = description;
    }
    public pack(){

    }
    public String toString() {
        return "pack{" +
                "id_pack=" + id_pack +
                ", id_post=" + id_post +
                ", prix='" + prix + '\'' +
                ", disponibilite='" + disponibilite + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public int getId_pack() {
        return id_pack;
    }

    public void setId_pack(int id_pack) {
        this.id_pack = id_pack;
    }

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(int disponibilite) {
        this.disponibilite = disponibilite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
