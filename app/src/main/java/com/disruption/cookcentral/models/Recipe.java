package com.disruption.cookcentral.models;

import java.util.List;

public class Recipe {
    private boolean vegetarian;

    private boolean vegan;

    private boolean glutenFree;

    private boolean dairyFree;

    private boolean veryHealthy;

    private boolean cheap;

    private String sourceUrl;

    private String spoonacularSourceUrl;

    private int aggregateLikes;

    private int spoonacularScore;

    private int healthScore;

    private String creditsText;

    private double pricePerServing;

    private int id;

    private String title;

    private int readyInMinutes;

    private int servings;

    private String image;

    private String summary;

    private List<String> dishTypes;

    private List<String> diets;

    private List<String> occasions;

    private String instructions;

    private List<AnalyzedInstructions> analyzedInstructions;

    public boolean getVegetarian() {
        return this.vegetarian;
    }

    public boolean getVegan() {
        return this.vegan;
    }

    public boolean getGlutenFree() {
        return this.glutenFree;
    }

    public boolean getDairyFree() {
        return this.dairyFree;
    }

    public boolean getVeryHealthy() {
        return this.veryHealthy;
    }

    public boolean getCheap() {
        return this.cheap;
    }

    public String getSourceUrl() {
        return this.sourceUrl;
    }

    public String getSpoonacularSourceUrl() {
        return this.spoonacularSourceUrl;
    }

    public int getAggregateLikes() {
        return this.aggregateLikes;
    }

    public int getSpoonacularScore() {
        return this.spoonacularScore;
    }

    public int getHealthScore() {
        return this.healthScore;
    }

    public String getCreditsText() {
        return this.creditsText;
    }

    public double getPricePerServing() {
        return this.pricePerServing;
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

    public String getSummary() {
        return this.summary;
    }

    public List<String> getDishTypes() {
        return this.dishTypes;
    }

    public List<String> getDiets() {
        return this.diets;
    }

    public List<String> getOccasions() {
        return this.occasions;
    }

    public String getInstructions() {
        return this.instructions;
    }

    public List<AnalyzedInstructions> getAnalyzedInstructions() {
        return this.analyzedInstructions;
    }
}
