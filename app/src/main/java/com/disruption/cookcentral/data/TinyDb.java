package com.disruption.cookcentral.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.disruption.cookcentral.models.CachedRecipe;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class TinyDb {

    private SharedPreferences preferences;

    public TinyDb(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }

    public List<CachedRecipe> getListOfFavouriteRecipes(String key) {
        String jsonText = preferences.getString(key, null);

        CachedRecipe[] recipes = new Gson().fromJson(
                jsonText,
                CachedRecipe[].class);

        return Arrays.asList(recipes);
    }

    public void saveListOfFavouriteRecipes(String key, List<CachedRecipe> recipes) {
        SharedPreferences.Editor editor = preferences.edit();

        String jsonText = new Gson().toJson(recipes);
        editor.putString(key, jsonText);
        editor.apply();
    }
}
