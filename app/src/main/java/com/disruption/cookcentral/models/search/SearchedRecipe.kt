package com.disruption.cookcentral.models.search

data class SearchedRecipe(
        var id: Int = 0,
        var title: String? = null,
        var readyInMinutes: Int = 0,
        var servings: Int = 0,
        var image: String? = null)

