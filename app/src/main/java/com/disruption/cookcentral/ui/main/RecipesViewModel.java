package com.disruption.cookcentral.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.disruption.cookcentral.data.RecipeRepository;
import com.disruption.cookcentral.models.Recipe;
import com.disruption.cookcentral.models.RecipeResponse;
import com.disruption.cookcentral.utils.Resource;

public class RecipesViewModel extends ViewModel {

    LiveData<Resource<RecipeResponse>> mRecipeResource;
    private MutableLiveData<Recipe> navigateToRecipe = new MutableLiveData<>();

    public RecipesViewModel() {
        mRecipeResource = RecipeRepository.getRandomRecipes();
    }

    LiveData<Recipe> getNavigateToRecipe() {
        return navigateToRecipe;
    }

    /**
     * Triggered once a recipe is clicked
     */
    void displayRecipeDetails(Recipe recipe) {
        navigateToRecipe.setValue(recipe);
    }

    /**
     * Set the value back to null so that we do not trigger multiple navigation events
     */
    void displayRecipeDetailsComplete() {
        navigateToRecipe.setValue(null);
    }
}
