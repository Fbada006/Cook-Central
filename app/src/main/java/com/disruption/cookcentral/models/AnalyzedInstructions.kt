package com.disruption.cookcentral.models

import android.os.Parcel
import android.os.Parcelable

class AnalyzedInstructions : Parcelable {
    var name: String?
        private set
    var steps: List<Steps>? = null
        private set

    protected constructor(`in`: Parcel) {
        name = `in`.readString()
    }

    constructor(name: String?, steps: List<Steps>?) {
        this.name = name
        this.steps = steps
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(name)
    }

    companion object {
        val CREATOR: Parcelable.Creator<AnalyzedInstructions> = object : Parcelable.Creator<AnalyzedInstructions> {
            override fun createFromParcel(`in`: Parcel): AnalyzedInstructions? {
                return AnalyzedInstructions(`in`)
            }

            override fun newArray(size: Int): Array<AnalyzedInstructions?> {
                return arrayOfNulls(size)
            }
        }
    }
}