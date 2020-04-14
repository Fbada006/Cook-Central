package com.disruption.cookcentral.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Recipe implements Parcelable {
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

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    protected Recipe(Parcel in) {
        vegetarian = in.readByte() != 0;
        vegan = in.readByte() != 0;
        glutenFree = in.readByte() != 0;
        dairyFree = in.readByte() != 0;
        veryHealthy = in.readByte() != 0;
        cheap = in.readByte() != 0;
        sourceUrl = in.readString();
        spoonacularSourceUrl = in.readString();
        aggregateLikes = in.readInt();
        spoonacularScore = in.readInt();
        healthScore = in.readInt();
        creditsText = in.readString();
        pricePerServing = in.readDouble();
        id = in.readInt();
        title = in.readString();
        readyInMinutes = in.readInt();
        servings = in.readInt();
        image = in.readString();
        summary = in.readString();
        dishTypes = in.createStringArrayList();
        diets = in.createStringArrayList();
        occasions = in.createStringArrayList();
        instructions = in.readString();
    }

    public Recipe() {
    }

    public boolean getVegetarian() {
        return this.vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public boolean getVegan() {
        return this.vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public boolean getGlutenFree() {
        return this.glutenFree;
    }

    public void setGlutenFree(boolean glutenFree) {
        this.glutenFree = glutenFree;
    }

    public boolean getDairyFree() {
        return this.dairyFree;
    }

    public void setDairyFree(boolean dairyFree) {
        this.dairyFree = dairyFree;
    }

    public boolean getVeryHealthy() {
        return this.veryHealthy;
    }

    public void setVeryHealthy(boolean veryHealthy) {
        this.veryHealthy = veryHealthy;
    }

    public boolean getCheap() {
        return this.cheap;
    }

    public void setCheap(boolean cheap) {
        this.cheap = cheap;
    }

    public String getSourceUrl() {
        return this.sourceUrl;
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

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getSpoonacularSourceUrl() {
        return this.spoonacularSourceUrl;
    }

    public void setSpoonacularSourceUrl(String spoonacularSourceUrl) {
        this.spoonacularSourceUrl = spoonacularSourceUrl;
    }

    public int getAggregateLikes() {
        return this.aggregateLikes;
    }

    public List<AnalyzedInstructions> getAnalyzedInstructions() {
        return this.analyzedInstructions;
    }

    public void setAggregateLikes(int aggregateLikes) {
        this.aggregateLikes = aggregateLikes;
    }

    public int getSpoonacularScore() {
        return this.spoonacularScore;
    }

    public void setSpoonacularScore(int spoonacularScore) {
        this.spoonacularScore = spoonacularScore;
    }

    public int getHealthScore() {
        return this.healthScore;
    }

    public void setHealthScore(int healthScore) {
        this.healthScore = healthScore;
    }

    public String getCreditsText() {
        return this.creditsText;
    }

    public void setCreditsText(String creditsText) {
        this.creditsText = creditsText;
    }

    public double getPricePerServing() {
        return this.pricePerServing;
    }

    public void setPricePerServing(double pricePerServing) {
        this.pricePerServing = pricePerServing;
    }

    public List<String> getDishTypes() {
        return this.dishTypes;
    }

    public void setDishTypes(List<String> dishTypes) {
        this.dishTypes = dishTypes;
    }

    public List<String> getDiets() {
        return this.diets;
    }

    public void setDiets(List<String> diets) {
        this.diets = diets;
    }

    public List<String> getOccasions() {
        return this.occasions;
    }

    public void setOccasions(List<String> occasions) {
        this.occasions = occasions;
    }

    public String getInstructions() {
        return this.instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setAnalyzedInstructions(List<AnalyzedInstructions> analyzedInstructions) {
        this.analyzedInstructions = analyzedInstructions;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (vegetarian ? 1 : 0));
        parcel.writeByte((byte) (vegan ? 1 : 0));
        parcel.writeByte((byte) (glutenFree ? 1 : 0));
        parcel.writeByte((byte) (dairyFree ? 1 : 0));
        parcel.writeByte((byte) (veryHealthy ? 1 : 0));
        parcel.writeByte((byte) (cheap ? 1 : 0));
        parcel.writeString(sourceUrl);
        parcel.writeString(spoonacularSourceUrl);
        parcel.writeInt(aggregateLikes);
        parcel.writeInt(spoonacularScore);
        parcel.writeInt(healthScore);
        parcel.writeString(creditsText);
        parcel.writeDouble(pricePerServing);
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeInt(readyInMinutes);
        parcel.writeInt(servings);
        parcel.writeString(image);
        parcel.writeString(summary);
        parcel.writeStringList(dishTypes);
        parcel.writeStringList(diets);
        parcel.writeStringList(occasions);
        parcel.writeString(instructions);
    }
}
