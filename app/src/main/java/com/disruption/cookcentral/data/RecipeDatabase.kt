package com.disruption.cookcentral.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.disruption.cookcentral.models.CachedRecipe

@Database(entities = [CachedRecipe::class], version = 1, exportSchema = false)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}