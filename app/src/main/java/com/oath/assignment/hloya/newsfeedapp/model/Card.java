package com.oath.assignment.hloya.newsfeedapp.model;

/**
 * Java Bean Class that groups all required fields for a NewsFeed Card in 1 object
 * Provides accessors and mutators for each of the required field for a NewsFeed Card
 */
public class Card {

    private String title;
    private String url;
    private String imageURL;

    public Card() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}