package com.ramirez.fernando.technews.model;

/**
 * Created by fernandoramirez on 7/19/16.
 */

public class Article {

    private String title, description;

    public Article() {
    }

    public Article(String title, String description) {
        this.title = title;
        this.description = description;
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
}
