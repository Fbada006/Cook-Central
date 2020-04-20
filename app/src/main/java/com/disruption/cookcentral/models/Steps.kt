package com.disruption.cookcentral.models

import android.os.Parcel
import android.os.Parcelable

class Steps : Parcelable {
    var number: Int
        private set
    var step: String?
        private set
    var ingredients: List<Ingredients>? = null
        private set
    var equipment: List<Equipment>? = null
        private set
    var length: Length? = null
        private set

    constructor(number: Int, step: String?, ingredients: List<Ingredients>?, equipment: List<Equipment>?, length: Length?) {
        this.number = number
        this.step = step
        this.ingredients = ingredients
        this.equipment = equipment
        this.length = length
    }

    protected constructor(`in`: Parcel) {
        number = `in`.readInt()
        step = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeInt(number)
        parcel.writeString(step)
    }

    companion object {
        val CREATOR: Parcelable.Creator<Steps> = object : Parcelable.Creator<Steps> {
            override fun createFromParcel(`in`: Parcel): Steps? {
                return Steps(`in`)
            }

            override fun newArray(size: Int): Array<Steps?> {
                return arrayOfNulls(size)
            }
        }
    }
}