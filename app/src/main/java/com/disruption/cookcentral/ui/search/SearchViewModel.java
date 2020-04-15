package com.disruption.cookcentral.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.disruption.cookcentral.data.RecipeRepository;
import com.disruption.cookcentral.models.search.SearchedRecipeResponse;
import com.disruption.cookcentral.utils.Resource;

public class SearchViewModel extends ViewModel {

    private MutableLiveData<String> queryLiveData = new MutableLiveData<>();

    public LiveData<Resource<SearchedRecipeResponse>> getSearchedRecipes() {
        return Transformations.switchMap(queryLiveData, RecipeRepository::getSearchedRecipes);
    }

    public void searchRecipe(String query) {
        queryLiveData.postValue(query);
    }
}
