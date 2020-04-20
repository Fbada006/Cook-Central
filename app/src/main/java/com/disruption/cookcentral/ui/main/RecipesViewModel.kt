package com.disruption.cookcentral.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.disruption.cookcentral.data.RecipeRepository
import com.disruption.cookcentral.models.Recipe

class RecipesViewModel : ViewModel() {
    var mRecipeResource = RecipeRepository.randomRecipes

    private val navigateToRecipe = MutableLiveData<Recipe?>()

    fun getNavigateToRecipe(): LiveData<Recipe?> {
        return navigateToRecipe
    }

    /**
     * Triggered once a recipe is clicked
     */
    fun displayRecipeDetails(recipe: Recipe?) {
        navigateToRecipe.value = recipe
    }

    /**
     * Set the value back to null so that we do not trigger multiple navigation events
     */
    fun displayRecipeDetailsComplete() {
        navigateToRecipe.value = null
    }

}