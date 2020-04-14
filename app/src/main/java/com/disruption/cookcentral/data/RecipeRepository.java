package com.disruption.cookcentral.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;

import com.disruption.cookcentral.BuildConfig;
import com.disruption.cookcentral.models.CachedRecipe;
import com.disruption.cookcentral.models.Recipe;
import com.disruption.cookcentral.models.RecipeResponse;
import com.disruption.cookcentral.network.RecipeApiServiceProvider;
import com.disruption.cookcentral.utils.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import io.reactivex.schedulers.Schedulers;

public class RecipeRepository {
    private static MediatorLiveData<Resource<RecipeResponse>> mRecipeResource;
    private static final String API_KEY = BuildConfig.RecipeKey;

    private RecipeDao mRecipeDao;

    public RecipeRepository(RecipeDao recipeDao) {
        mRecipeDao = recipeDao;
    }

    public static LiveData<Resource<RecipeResponse>> getRandomRecipes() {
        if (mRecipeResource == null) {
            mRecipeResource = new MediatorLiveData<>();
            mRecipeResource.setValue(Resource.loading());

            final LiveData<Resource<RecipeResponse>> source =
                    LiveDataReactiveStreams.fromPublisher(
                            RecipeApiServiceProvider.getRecipeApiService().getRandomRecipes(20, API_KEY)
                                    .onErrorReturn(throwable -> {
                                        Recipe Recipe = new Recipe();
                                        Recipe.setId(-111111111);
                                        List<Recipe> recipes = new ArrayList<>();
                                        recipes.add(Recipe);
                                        return new RecipeResponse(recipes, throwable.getMessage());
                                    })
                                    .map(response -> {
                                        if (response.getRecipes().size() > 0) {
                                            if (response.getRecipes().get(0).getId() == -111111111) {
                                                if (response.getErrorMessage() != null) {
                                                    return Resource.error(response.getErrorMessage(), response);
                                                }
                                            }
                                        }
                                        return Resource.success(response);
                                    })
                                    .subscribeOn(Schedulers.io())
                    );
            mRecipeResource.addSource(source, listResource -> {
                mRecipeResource.setValue(listResource);
                mRecipeResource.removeSource(source);
            });
        }

        return mRecipeResource;
    }

    public LiveData<CachedRecipe> loadRecipeById(int recipeId) {
        return mRecipeDao.loadRecipeById(recipeId);
    }

    public LiveData<List<CachedRecipe>> getAllFavs() {
        return mRecipeDao.loadAllFavs();
    }

    public void deleteRecipeFromFavs(CachedRecipe recipe) {
        Executors.newSingleThreadExecutor().execute(() -> mRecipeDao.removeRecipeFromFavourites(recipe));
    }

    public void addRecipeToFavs(CachedRecipe recipe) {
        Executors.newSingleThreadExecutor().execute(() -> mRecipeDao.addRecipeToFavourites(recipe));
    }
}
