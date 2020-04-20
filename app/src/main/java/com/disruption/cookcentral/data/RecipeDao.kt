package com.disruption.cookcentral.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.disruption.cookcentral.models.CachedRecipe

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes")
    fun loadAllFavs(): LiveData<List<CachedRecipe>>

    @Query("SELECT * FROM recipes")
    fun loadAllFavsForWidget(): List<CachedRecipe>

    @Query("SELECT * FROM recipes WHERE id = :id")
    fun loadRecipeById(id: Int): LiveData<CachedRecipe>

    @Insert
    fun addRecipeToFavourites(recipe: CachedRecipe)

    @Delete
    fun removeRecipeFromFavourites(recipe: CachedRecipe)

    @Query("DELETE FROM recipes")
    fun nukeFavsTable()
}