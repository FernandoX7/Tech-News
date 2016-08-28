package com.ramirez.fernando.technews.model;

/**
 * Created by fernandoramirez on 7/19/16.
 */

public class Article {

    private String title, description, author;

    public Article() {

    }

    public Article(String title, String description, String author) {
        this.title = title;
        this.description = description;
        this.author = author;
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

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
