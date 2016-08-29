package com.ramirez.fernando.technews.model;

/**
 * Created by fernandoramirez on 7/19/16.
 */

public class Article {

    private String title, description, author, image, timestamp;

    public Article() {}

    // With image
    public Article(String title, String description, String author, String image, String timestamp) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.image = image;
        this.timestamp = timestamp;
    }

    // Normal
    public Article(String title, String description, String author, String timestamp) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Article {" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", image='" + image + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
