package com.disruption.cookcentral.ui.favs;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.disruption.cookcentral.data.RecipeDatabase;
import com.disruption.cookcentral.data.RecipeRepository;
import com.disruption.cookcentral.models.CachedRecipe;

import java.util.List;

public class FavouritesViewModel extends AndroidViewModel {

    public LiveData<List<CachedRecipe>> mFavData;
    private RecipeRepository mRecipeRepository;

    public FavouritesViewModel(@NonNull Application application) {
        super(application);
        mRecipeRepository = new RecipeRepository(RecipeDatabase.getInstance(application).recipeDao());
        mFavData = mRecipeRepository.getAllFavs();
    }

    public void deleteRecipeFromFavourites(CachedRecipe recipe) {
        mRecipeRepository.deleteRecipeFromFavs(recipe);
    }

    public void nukeFavsTable() {
        mRecipeRepository.nukeFavsTable();
    }

    public void insertRecipeToFavourites(CachedRecipe recipe) {
        mRecipeRepository.addRecipeToFavs(recipe);
    }
}
