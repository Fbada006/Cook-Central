package com.disruption.cookcentral.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.disruption.cookcentral.models.CachedRecipe;

@Database(entities = {CachedRecipe.class}, version = 1, exportSchema = false)
public abstract class RecipeDatabase extends RoomDatabase {
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "recipelist";
    private static RecipeDatabase sInstance;

    public static RecipeDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        RecipeDatabase.class, RecipeDatabase.DATABASE_NAME)
                        .build();
            }
        }
        return sInstance;
    }

    public abstract RecipeDao recipeDao();
}
