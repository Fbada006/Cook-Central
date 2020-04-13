package com.disruption.cookcentral.models;

import java.util.List;

public class RecipeResponse {

    private List<Recipe> recipes;

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Recipe> getRecipes() {
        return this.recipes;
    }
}
