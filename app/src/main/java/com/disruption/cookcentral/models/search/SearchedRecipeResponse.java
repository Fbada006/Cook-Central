package com.disruption.cookcentral.models.search;

import androidx.annotation.Nullable;

import java.util.List;

public class SearchedRecipeResponse {

    private List<SearchedRecipe> results;

    @Nullable
    private String errorMessage;

    public SearchedRecipeResponse(List<SearchedRecipe> results, @Nullable String errorMessage) {
        this.results = results;
        this.errorMessage = errorMessage;
    }

    public List<SearchedRecipe> getResults() {
        return this.results;
    }

    @Nullable
    public String getErrorMessage() {
        return errorMessage;
    }
}
