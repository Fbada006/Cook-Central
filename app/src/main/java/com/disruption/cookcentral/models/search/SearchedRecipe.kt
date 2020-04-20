package com.disruption.cookcentral.models.search

class SearchedRecipe {
    var id = 0
    var title: String? = null
        private set
    var readyInMinutes = 0
        private set
    var servings = 0
        private set
    var image: String? = null
        private set

    constructor(id: Int, title: String?, readyInMinutes: Int, servings: Int, image: String?) {
        this.id = id
        this.title = title
        this.readyInMinutes = readyInMinutes
        this.servings = servings
        this.image = image
    }

    constructor()

}