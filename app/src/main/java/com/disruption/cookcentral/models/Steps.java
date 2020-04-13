package com.disruption.cookcentral.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Steps implements Parcelable {

    private int number;

    private String step;

    private List<Ingredients> ingredients;

    private List<Equipment> equipment;

    private Length length;

    public static final Creator<Steps> CREATOR = new Creator<Steps>() {
        @Override
        public Steps createFromParcel(Parcel in) {
            return new Steps(in);
        }

        @Override
        public Steps[] newArray(int size) {
            return new Steps[size];
        }
    };

    protected Steps(Parcel in) {
        number = in.readInt();
        step = in.readString();
    }

    public int getNumber() {
        return this.number;
    }

    public String getStep() {
        return this.step;
    }

    public List<Ingredients> getIngredients() {
        return this.ingredients;
    }

    public List<Equipment> getEquipment() {
        return this.equipment;
    }

    public Length getLength() {
        return this.length;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(number);
        parcel.writeString(step);
    }
}
