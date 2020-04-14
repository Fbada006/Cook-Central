package com.disruption.cookcentral.ui.favs;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.disruption.cookcentral.data.RecipeDatabase;
import com.disruption.cookcentral.data.RecipeRepository;
import com.disruption.cookcentral.models.Recipe;

import java.util.List;

public class FavouritesViewModel extends AndroidViewModel {

    public LiveData<List<Recipe>> mFavData;

    public FavouritesViewModel(@NonNull Application application) {
        super(application);
        mFavData =
                new RecipeRepository(RecipeDatabase.getInstance(application).recipeDao()).getAllFavs();
    }
}
