package com.disruption.cookcentral.models;

import java.util.List;

public class Steps {

    private int number;

    private String step;

    private List<Ingredients> ingredients;

    private List<Equipment> equipment;

    private Length length;

    public int getNumber() {
        return this.number;
    }

    public String getStep() {
        return this.step;
    }

    public List<Ingredients> getIngredients() {
        return this.ingredients;
    }

    public List<Equipment> getEquipment() {
        return this.equipment;
    }

    public Length getLength() {
        return this.length;
    }
}
