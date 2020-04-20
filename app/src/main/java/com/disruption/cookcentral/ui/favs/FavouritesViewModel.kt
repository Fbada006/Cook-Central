package com.disruption.cookcentral.ui.favs

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.disruption.cookcentral.data.RecipeDatabase
import com.disruption.cookcentral.data.RecipeRepository
import com.disruption.cookcentral.models.CachedRecipe

class FavouritesViewModel(application: Application) : AndroidViewModel(application) {
    var mFavData: LiveData<List<CachedRecipe>>

    private val mRecipeRepository: RecipeRepository = RecipeRepository(RecipeDatabase.getInstance(application).recipeDao())

    fun deleteRecipeFromFavourites(recipe: CachedRecipe) {
        mRecipeRepository.deleteRecipeFromFavs(recipe)
    }

    fun nukeFavsTable() {
        mRecipeRepository.nukeFavsTable()
    }

    fun insertRecipeToFavourites(recipe: CachedRecipe) {
        mRecipeRepository.addRecipeToFavs(recipe)
    }

    init {
        mFavData = mRecipeRepository.allFavs
    }
}