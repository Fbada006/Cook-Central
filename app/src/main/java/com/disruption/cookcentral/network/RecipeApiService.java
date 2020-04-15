package com.disruption.cookcentral.network;

import com.disruption.cookcentral.models.RecipeResponse;
import com.disruption.cookcentral.models.search.SearchedRecipeResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeApiService {

    @GET("recipes/random")
    Flowable<RecipeResponse> getRandomRecipes(
            @Query("number") int number,
            @Query("apiKey") String apiKey
    );

    @GET("recipes/search")
    Flowable<SearchedRecipeResponse> searchRecipes(
            @Query("query") String query,
            @Query("number") int number,
            @Query("apiKey") String apiKey
    );
}
