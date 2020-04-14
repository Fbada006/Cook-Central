package com.disruption.cookcentral.ui.details;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.disruption.cookcentral.data.RecipeDatabase;
import com.disruption.cookcentral.data.RecipeRepository;
import com.disruption.cookcentral.models.CachedRecipe;

public class DetailsViewModel extends AndroidViewModel {

    private final RecipeRepository mRecipeRepository;

    public DetailsViewModel(@NonNull Application application) {
        super(application);
        mRecipeRepository = new RecipeRepository(RecipeDatabase.getInstance(application).recipeDao());
    }

    public void insertRecipeToFavourites(CachedRecipe recipe) {
        mRecipeRepository.addRecipeToFavs(recipe);
    }

    public void deleteRecipeFromFavourites(CachedRecipe recipe) {
        mRecipeRepository.deleteRecipeFromFavs(recipe);
    }

    public LiveData<CachedRecipe> isRecipeInFavs(int recipeId) {
        return mRecipeRepository.loadRecipeById(recipeId);
    }
}
