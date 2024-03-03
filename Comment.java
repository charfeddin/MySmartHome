package com.example.rafafx.entities;

import java.util.Objects;

public class Comment {
    private int id;
    private String content;
    private Post post;

    public Comment(int id, String content, Post post) {
        this.id = id;
        this.content = content;
        this.post = post;
    }

    public Comment(String content, Post post) {
        this.content = content;
        this.post = post;
    }

    public Comment() {
    }

    public Comment(String content) {
        this.content=content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

//    @Override
//    public String toString() {
//        return "Comment{" +
//                "id=" + id +
//                ", content='" + content + '\'' +
//                ", post=" + post +
//                '}';
//    }
    @Override
    public String toString() {
        return content; // Assuming 'content' is the field holding the comment text.
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id && Objects.equals(content, comment.content) && Objects.equals(post, comment.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, post);
    }
}
