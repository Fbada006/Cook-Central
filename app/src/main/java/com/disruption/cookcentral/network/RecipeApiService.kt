package com.disruption.cookcentral.network

import com.disruption.cookcentral.models.RecipeResponse
import com.disruption.cookcentral.models.search.SearchedRecipeResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApiService {
    @GET("recipes/random")
    fun getRandomRecipes(
            @Query("number") number: Int,
            @Query("apiKey") apiKey: String?
    ): Flowable<RecipeResponse?>?

    @GET("recipes/search")
    fun searchRecipes(
            @Query("query") query: String?,
            @Query("number") number: Int,
            @Query("apiKey") apiKey: String?
    ): Flowable<SearchedRecipeResponse?>?
}