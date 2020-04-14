package com.disruption.cookcentral.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Ingredients implements Parcelable {

    private int id;

    private String name;

    private String image;

    public static final Creator<Ingredients> CREATOR = new Creator<Ingredients>() {
        @Override
        public Ingredients createFromParcel(Parcel in) {
            return new Ingredients(in);
        }

        @Override
        public Ingredients[] newArray(int size) {
            return new Ingredients[size];
        }
    };

    protected Ingredients(Parcel in) {
        id = in.readInt();
        name = in.readString();
        image = in.readString();
    }

    public Ingredients(int id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getImage() {
        return this.image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(image);
    }
}
