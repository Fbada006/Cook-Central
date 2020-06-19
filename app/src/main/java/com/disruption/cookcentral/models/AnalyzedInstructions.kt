package com.disruption.cookcentral.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AnalyzedInstructions(
        var name: String?,
        var steps: List<Steps>? = null
) : Parcelable