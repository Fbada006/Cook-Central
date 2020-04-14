package com.disruption.cookcentral.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class AnalyzedInstructions implements Parcelable {

    private String name;

    private List<Steps> steps;

    public static final Creator<AnalyzedInstructions> CREATOR = new Creator<AnalyzedInstructions>() {
        @Override
        public AnalyzedInstructions createFromParcel(Parcel in) {
            return new AnalyzedInstructions(in);
        }

        @Override
        public AnalyzedInstructions[] newArray(int size) {
            return new AnalyzedInstructions[size];
        }
    };

    protected AnalyzedInstructions(Parcel in) {
        name = in.readString();
    }

    public String getName() {
        return this.name;
    }

    public List<Steps> getSteps() {
        return this.steps;
    }

    public AnalyzedInstructions(String name, List<Steps> steps) {
        this.name = name;
        this.steps = steps;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
    }
}
