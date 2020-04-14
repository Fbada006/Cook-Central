package com.disruption.cookcentral.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.disruption.cookcentral.models.Recipe;

import java.util.List;

@Dao
public interface RecipeDao {

    @Query("SELECT * FROM recipes")
    LiveData<List<Recipe>> loadAllFavs();

    @Query("SELECT * FROM recipes WHERE id = :id")
    LiveData<Recipe> loadRecipeById(int id);

    @Insert
    void addRecipeToFavourites(Recipe recipe);

    @Delete
    void removeRecipeFromFavourites(Recipe recipe);
}
