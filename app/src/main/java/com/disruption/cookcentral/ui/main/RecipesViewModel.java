package com.disruption.cookcentral.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.disruption.cookcentral.data.RecipeRepository;
import com.disruption.cookcentral.models.RecipeResponse;
import com.disruption.cookcentral.utils.Resource;

public class RecipesViewModel extends ViewModel {

    public LiveData<Resource<RecipeResponse>> mRecipeResource;

    public RecipesViewModel() {
        mRecipeResource = RecipeRepository.getRandomRecipes();
    }
}
