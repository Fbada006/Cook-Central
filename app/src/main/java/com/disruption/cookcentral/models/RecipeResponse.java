package com.disruption.cookcentral.models;

import androidx.annotation.Nullable;

import java.util.List;

public class RecipeResponse {

    private List<Recipe> recipes;

    @Nullable
    private String errorMessage;

    public RecipeResponse(List<Recipe> recipes, @Nullable String errorMessage) {
        this.recipes = recipes;
        this.errorMessage = errorMessage;
    }

    public List<Recipe> getRecipes() {
        return this.recipes;
    }

    @Nullable
    public String getErrorMessage() {
        return errorMessage;
    }
}
