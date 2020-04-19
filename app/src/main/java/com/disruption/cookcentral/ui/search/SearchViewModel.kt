package com.disruption.cookcentral.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.disruption.cookcentral.data.RecipeRepository
import com.disruption.cookcentral.models.search.SearchedRecipeResponse
import com.disruption.cookcentral.utils.Resource

class SearchViewModel : ViewModel() {

    private val queryLiveData = MutableLiveData<String>()

    val searchedRecipes: LiveData<Resource<SearchedRecipeResponse>>
        get() = Transformations.switchMap(queryLiveData) { query: String? -> RecipeRepository.getSearchedRecipes(query) }

    fun searchRecipe(query: String) {
        queryLiveData.postValue(query)
    }
}