package com.disruption.cookcentral.models.search;

public class SearchedRecipe {

    private int id;

    private String title;

    private int readyInMinutes;

    private int servings;

    private String image;

    public SearchedRecipe(int id, String title, int readyInMinutes, int servings, String image) {
        this.id = id;
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.image = image;
    }

    public SearchedRecipe() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public int getReadyInMinutes() {
        return this.readyInMinutes;
    }

    public int getServings() {
        return this.servings;
    }

    public String getImage() {
        return this.image;
    }
}
