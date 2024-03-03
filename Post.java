package com.example.rafafx.entities;

import java.time.LocalDate;
import java.util.List;

public class Post {
    private int id;
    private String titre;
    private LocalDate date;
    private String image;
    private String description;
    private static List<String> likes;



    public void setLikes(List<String> likes) {
        this.likes = likes;
    }

    public Post(String titre, String image, String description) {
        this.titre = titre;
         this.date = LocalDate.now();
        this.image = image;
        this.description = description;
    }

    public Post(int id, String titre, String image, String description) {
        this.id = id;
        this.titre = titre;
        this.date = LocalDate.now();
        this.image = image;
        this.description = description;
    }

    public Post() {
        this.date = LocalDate.now();
    }

    public Post(int id, String titre,  String description,String image,LocalDate date) {
        this.id = id;
        this.titre = titre;
        this.date = date;
        this.image = image;
        this.description = description;
    }


    public Post(String titre,   String description,String image,LocalDate date) {
        this.titre = titre;
        this.date = date;
        this.image = image;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", date=" + date +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
    public static List<String> getLikes() {
        return likes;
    }

    public static void likePost(int postId) {
        likes.add(String.valueOf(postId));
    }

    public static void dislikePost(int postId) {
        likes.remove(String.valueOf(postId));
    }
}
