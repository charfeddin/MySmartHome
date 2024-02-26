package entities;

public class post {
    int id_post ;
    user id_user;
    int id_commentaire;
    String contenu;
   String titre ,commentaire,date_pub;

    public post(int id_post,user  id_user,int id_commentaire,  String titre, String commentaire, String date_pub) {
        this.id_post = id_post;
        this.id_user = id_user;
        this.id_commentaire =id_commentaire;
        this.contenu = contenu;
        this.titre = titre;
        this.commentaire = commentaire;
        this.date_pub = date_pub;
    }

    public post(user id_user, String titre, String commentaire, String date_pub) {
        this.id_user = id_user;
        this.titre = titre;
        this.commentaire = commentaire;
        this.date_pub = date_pub;
    }

    public post(String titre, String commentaire, String date_pub) {

        this.titre = titre;
        this.commentaire = commentaire;
        this.date_pub = date_pub;
    }
//update
public post(int id_commentaire, String titre, String commentaire, String date_pub) {
    this.id_commentaire = id_commentaire;
    this.titre = titre;
    this.commentaire = commentaire;
    this.date_pub = date_pub;
}

    //delete
    public post(int id_post) {
        this.id_post = id_post;
    }


    public post(){

    }

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }


    public user getId_user() {
        return id_user;
    }

    public void setId_user(user id_user) {
        this.id_user = id_user;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getDate_pub() {
        return date_pub;
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

    public void setDate_pub(String date_pub) {
        this.date_pub = date_pub;
    }
    @Override
    public String toString() {
        return "post{" +
                "id_post=" + id_post +
                ", id_user=" + id_user + '\'' +
                ", titre='" + titre + '\'' +
                ", commentaire='" + commentaire + '\'' +
                ", date_pub='" + date_pub + '\'' +
                '}';
    }
}

