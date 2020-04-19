package com.disruption.cookcentral.data

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.disruption.cookcentral.models.CachedRecipe
import com.google.gson.Gson

class TinyDb(context: Context) {
    private val preferences: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(context.applicationContext)

    fun getListOfFavouriteRecipes(key: String?): List<CachedRecipe> {
        val jsonText = preferences.getString(key, null)
        val recipes = Gson().fromJson(
                jsonText,
                Array<CachedRecipe>::class.java)
        return listOf(*recipes)
    }

    fun saveListOfFavouriteRecipes(key: String?, recipes: List<CachedRecipe?>?) {
        val editor = preferences.edit()
        val jsonText = Gson().toJson(recipes)
        editor.putString(key, jsonText)
        editor.apply()
    }

}