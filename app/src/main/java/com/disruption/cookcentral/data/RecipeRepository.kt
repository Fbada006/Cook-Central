package com.disruption.cookcentral.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import com.disruption.cookcentral.models.CachedRecipe
import com.disruption.cookcentral.models.Recipe
import com.disruption.cookcentral.models.RecipeResponse
import com.disruption.cookcentral.models.search.SearchedRecipe
import com.disruption.cookcentral.models.search.SearchedRecipeResponse
import com.disruption.cookcentral.network.RecipeApiServiceProvider
import com.disruption.cookcentral.utils.Constants
import com.disruption.cookcentral.utils.Resource
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors

class RecipeRepository(recipeDatabase: RecipeDatabase) {

    private val mRecipeDao = recipeDatabase.recipeDao()

    fun loadRecipeById(recipeId: Int) = mRecipeDao.loadRecipeById(recipeId)

    val allFavs = mRecipeDao.loadAllFavs()

    fun deleteRecipeFromFavs(recipe: CachedRecipe) {
        Executors.newSingleThreadExecutor().execute { mRecipeDao.removeRecipeFromFavourites(recipe) }
    }

    fun nukeFavsTable() {
        Executors.newSingleThreadExecutor().execute { mRecipeDao.nukeFavsTable() }
    }

    fun addRecipeToFavs(recipe: CachedRecipe) {
        Executors.newSingleThreadExecutor().execute { mRecipeDao.addRecipeToFavourites(recipe) }
    }

    companion object {
        private var mRecipeResource: MediatorLiveData<Resource<RecipeResponse>>? = null

        private var mSearchedRecipeResource: MediatorLiveData<Resource<SearchedRecipeResponse>>? = null

        val randomRecipes: LiveData<Resource<RecipeResponse>>?
            get() {
                if (mRecipeResource == null) {
                    mRecipeResource = MediatorLiveData()
                    mRecipeResource!!.value = Resource.loading()
                    val source = LiveDataReactiveStreams.fromPublisher(
                            RecipeApiServiceProvider.recipeApiService.getRandomRecipes(20, Constants.API_KEY)
                                    .onErrorReturn { throwable ->
                                        val recipe = Recipe()
                                        recipe.id = -111111111
                                        val recipes = mutableListOf<Recipe>()
                                        recipes.add(recipe)
                                        RecipeResponse(recipes, throwable.message)
                                    }
                                    .map { response: RecipeResponse ->
                                        if (response.recipes.isNotEmpty()) {
                                            if (response.recipes[0].id == -111111111) {
                                                if (response.errorMessage != null) {
                                                    return@map Resource.error(response.errorMessage, response)
                                                }
                                            }
                                        }
                                        Resource.success(response)
                                    }
                                    .subscribeOn(Schedulers.io())
                    )
                    mRecipeResource!!.addSource(source) { listResource ->
                        mRecipeResource!!.value = listResource
                        mRecipeResource!!.removeSource(source)
                    }
                }
                return mRecipeResource
            }

        fun getSearchedRecipes(query: String?): LiveData<Resource<SearchedRecipeResponse>>? {
            if (mSearchedRecipeResource == null) {
                mSearchedRecipeResource = MediatorLiveData()
                mSearchedRecipeResource!!.value = Resource.loading()

                val source = LiveDataReactiveStreams.fromPublisher(
                        RecipeApiServiceProvider.recipeApiService.searchRecipes(query, 20, Constants.API_KEY)
                                .onErrorReturn { throwable ->
                                    val searchedRecipe = SearchedRecipe()
                                    searchedRecipe.id = -111111111
                                    val recipes = mutableListOf<SearchedRecipe>()
                                    recipes.add(searchedRecipe)
                                    SearchedRecipeResponse(recipes, throwable.message)
                                }
                                .map { response ->
                                    if (response.results.isNotEmpty()) {
                                        if (response.results[0].id == -111111111) {
                                            if (response.errorMessage != null) {
                                                return@map Resource.error(response.errorMessage, response)
                                            }
                                        }
                                    }
                                    Resource.success(response)
                                }
                                .subscribeOn(Schedulers.io())
                )
                mSearchedRecipeResource!!.addSource(source) { listResource ->
                    mSearchedRecipeResource!!.value = listResource
                    mSearchedRecipeResource!!.removeSource(source)
                }
            }
            return mSearchedRecipeResource
        }
    }
}