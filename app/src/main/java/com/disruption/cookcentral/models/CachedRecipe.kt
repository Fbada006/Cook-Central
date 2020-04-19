package com.disruption.cookcentral.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
class CachedRecipe {
    @PrimaryKey
    var id = 0
    var title: String? = null
    var readyInMinutes = 0
    var servings = 0
    var image: String? = null
    var summary: String? = null

    constructor(id: Int, title: String?, readyInMinutes: Int, servings: Int, image: String?, summary: String?) {
        this.id = id
        this.title = title
        this.readyInMinutes = readyInMinutes
        this.servings = servings
        this.image = image
        this.summary = summary
    }

    @Ignore
    constructor()
}