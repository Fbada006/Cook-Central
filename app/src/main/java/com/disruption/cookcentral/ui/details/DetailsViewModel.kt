package com.disruption.cookcentral.ui.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.disruption.cookcentral.data.RecipeRepository
import com.disruption.cookcentral.models.CachedRecipe
import org.koin.core.KoinComponent
import org.koin.core.inject

class DetailsViewModel(application: Application) : AndroidViewModel(application), KoinComponent {

    private val mRecipeRepository: RecipeRepository by inject()

    fun insertRecipeToFavourites(recipe: CachedRecipe) {
        mRecipeRepository.addRecipeToFavs(recipe)
    }

    fun deleteRecipeFromFavourites(recipe: CachedRecipe) {
        mRecipeRepository.deleteRecipeFromFavs(recipe)
    }

    fun isRecipeInFavs(recipeId: Int): LiveData<CachedRecipe> {
        return mRecipeRepository.loadRecipeById(recipeId)
    }

}