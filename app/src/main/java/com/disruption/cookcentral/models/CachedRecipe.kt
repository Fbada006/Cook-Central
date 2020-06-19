package com.disruption.cookcentral.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class CachedRecipe(
        @PrimaryKey
        var id: Int = 0,
        var title: String? = null,
        var readyInMinutes: Int = 0,
        var servings: Int = 0,
        var image: String? = null,
        var summary: String? = null)
