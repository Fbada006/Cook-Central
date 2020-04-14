package com.disruption.cookcentral.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "recipes")
public class Recipe implements Parcelable {

    @PrimaryKey
    private int id;

    private String title;

    private int readyInMinutes;

    private int servings;

    private String image;

    private String summary;

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
        id = in.readInt();
        title = in.readString();
        readyInMinutes = in.readInt();
        servings = in.readInt();
        image = in.readString();
        summary = in.readString();
    }

    public Recipe() {
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

    public List<AnalyzedInstructions> getAnalyzedInstructions() {
        return this.analyzedInstructions;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeInt(readyInMinutes);
        parcel.writeInt(servings);
        parcel.writeString(image);
        parcel.writeString(summary);
    }
}
