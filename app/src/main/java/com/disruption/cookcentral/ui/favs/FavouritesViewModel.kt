package com.disruption.cookcentral.ui.favs

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.disruption.cookcentral.data.RecipeRepository
import com.disruption.cookcentral.models.CachedRecipe
import org.koin.core.KoinComponent
import org.koin.core.inject

class FavouritesViewModel(application: Application) : AndroidViewModel(application), KoinComponent {

    private val mRecipeRepository: RecipeRepository by inject()

    val mFavData = mRecipeRepository.allFavs

    fun deleteRecipeFromFavourites(recipe: CachedRecipe) {
        mRecipeRepository.deleteRecipeFromFavs(recipe)
    }

    fun nukeFavsTable() {
        mRecipeRepository.nukeFavsTable()
    }

    fun insertRecipeToFavourites(recipe: CachedRecipe) {
        mRecipeRepository.addRecipeToFavs(recipe)
    }
}