package com.disruption.cookcentral.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Length(
        val number: Int = 0,
        val unit: String? = null
) : Parcelable