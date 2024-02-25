package entities;

public class User {
    private int id_user;
    private int cin;

    private String nom;
    private String prenom;
    private int numero;
    private String email;
    private String mdp;
    private String role;

    public User(int id_user, int cin, String nom, String prenom, int numero, String email, String mdp, String role) {
        this.id_user = id_user;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.numero = numero;
        this.email = email;
        this.mdp = mdp;
        this.role = role;
    }


    public User(int cin, String nom, String prenom, int numero, String email, String mdp, String role) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.numero = numero;
        this.email = email;
        this.mdp = mdp;
        this.role = role;
    }

    public User(String email, String mdp) {
        this.email = email;
        this.mdp = mdp;
    }

    // Getters and Setters
    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // toString() method to represent User object as a String
    @Override
    public String toString() {
        return "User{" +
                "cin='" + cin + '\'' +
                ", id_user=" + id_user +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", numero='" + numero + '\'' +
                ", email='" + email + '\'' +
                ", mdp='" + mdp + '\'' +
                ", type='" + role + '\'' +
                '}';
    }
}
