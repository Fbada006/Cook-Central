package com.disruption.cookcentral.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Steps(
        val number: Int,
        val step: String?,
        val ingredients: List<Ingredients>? = null,
        val equipment: List<Equipment>? = null,
        val length: Length? = null
) : Parcelable