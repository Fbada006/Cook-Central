package com.disruption.cookcentral.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe(
        var id: Int = 0,
        var title: String? = null,
        var readyInMinutes: Int = 0,
        var servings: Int = 0,
        var image: String? = null,
        var summary: String? = null,
        private var instructions: String? = null,
        val analyzedInstructions: List<AnalyzedInstructions>? = null
) : Parcelable