package com.disruption.cookcentral.models

import android.os.Parcel
import android.os.Parcelable

class Ingredients : Parcelable {
    var id: Int
        private set
    var name: String?
        private set
    var image: String?
        private set

    protected constructor(`in`: Parcel) {
        id = `in`.readInt()
        name = `in`.readString()
        image = `in`.readString()
    }

    constructor(id: Int, name: String?, image: String?) {
        this.id = id
        this.name = name
        this.image = image
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(image)
    }

    companion object {
        val CREATOR: Parcelable.Creator<Ingredients> = object : Parcelable.Creator<Ingredients> {
            override fun createFromParcel(`in`: Parcel): Ingredients? {
                return Ingredients(`in`)
            }

            override fun newArray(size: Int): Array<Ingredients?> {
                return arrayOfNulls(size)
            }
        }
    }
}