package com.disruption.cookcentral.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.disruption.cookcentral.models.CachedRecipe

@Database(entities = [CachedRecipe::class], version = 1, exportSchema = false)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao

    companion object {
        private val LOCK = Any()
        private const val DATABASE_NAME = "recipelist"

        @Volatile
        private var sInstance: RecipeDatabase? = null

        fun getInstance(context: Context): RecipeDatabase {
            synchronized(LOCK) {
                var instance = sInstance
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,
                            RecipeDatabase::class.java, DATABASE_NAME)
                            .build()
                    sInstance = instance
                }
                return instance
            }
        }
    }
}