package com.disruption.cookcentral.models

import android.os.Parcel
import android.os.Parcelable

class Recipe : Parcelable {
    var id = 0
    var title: String? = null
        private set
    var readyInMinutes = 0
        private set
    var servings = 0
        private set
    var image: String? = null
    var summary: String? = null
        private set
    private var instructions: String? = null
    val analyzedInstructions: List<AnalyzedInstructions>? = null

    protected constructor(`in`: Parcel) {
        id = `in`.readInt()
        title = `in`.readString()
        readyInMinutes = `in`.readInt()
        servings = `in`.readInt()
        image = `in`.readString()
        summary = `in`.readString()
        instructions = `in`.readString()
    }

    constructor()

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeInt(readyInMinutes)
        parcel.writeInt(servings)
        parcel.writeString(image)
        parcel.writeString(summary)
        parcel.writeString(instructions)
    }

    companion object {
        val CREATOR: Parcelable.Creator<Recipe> = object : Parcelable.Creator<Recipe> {
            override fun createFromParcel(`in`: Parcel): Recipe? {
                return Recipe(`in`)
            }

            override fun newArray(size: Int): Array<Recipe?> {
                return arrayOfNulls(size)
            }
        }
    }
}