package com.disruption.cookcentral.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipes")
public class CachedRecipe {

    @PrimaryKey
    private int id;

    private String title;

    private int readyInMinutes;

    private int servings;

    private String image;

    private String summary;

    public CachedRecipe(int id, String title, int readyInMinutes, int servings, String image, String summary) {
        this.id = id;
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.image = image;
        this.summary = summary;
    }

    @Ignore
    public CachedRecipe() {
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

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReadyInMinutes() {
        return this.readyInMinutes;
    }

    public void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public int getServings() {
        return this.servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
