package com.disruption.cookcentral.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Equipment implements Parcelable {

    private int id;

    private String name;

    private String image;

    public static final Creator<Equipment> CREATOR = new Creator<Equipment>() {
        @Override
        public Equipment createFromParcel(Parcel in) {
            return new Equipment(in);
        }

        @Override
        public Equipment[] newArray(int size) {
            return new Equipment[size];
        }
    };

    protected Equipment(Parcel in) {
        id = in.readInt();
        name = in.readString();
        image = in.readString();
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
