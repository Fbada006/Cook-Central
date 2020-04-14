package com.disruption.cookcentral.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.disruption.cookcentral.models.CachedRecipe;

import java.util.List;

@Dao
public interface RecipeDao {

    @Query("SELECT * FROM recipes")
    LiveData<List<CachedRecipe>> loadAllFavs();

    @Query("SELECT * FROM recipes")
    List<CachedRecipe> loadAllFavsForWidget();

    @Query("SELECT * FROM recipes WHERE id = :id")
    LiveData<CachedRecipe> loadRecipeById(int id);

    @Insert
    void addRecipeToFavourites(CachedRecipe recipe);

    @Delete
    void removeRecipeFromFavourites(CachedRecipe recipe);
}
