package com.disruption.cookcentral.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ingredients(
        var id: Int,
        var name: String?,
        var image: String?
) : Parcelable