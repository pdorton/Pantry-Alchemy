package com.thealchemists.pantryalchemy;

import android.media.Image;

import java.util.ArrayList;

public class Recipe {
    // Data members
    private String recipe_name;
    private String directions;
    private Image photo;
    private int times_favorited;
    private ArrayList<Ingredient> ingredients;

    // Constructor
    public Recipe(String recipe_name, String directions, Image photo) {
        this.recipe_name = recipe_name;
        this.directions = directions;
        this.photo = photo;
        this.times_favorited = 0;
        this.ingredients = new ArrayList<Ingredient>();
    }

    // Methods
    public String getRecipe_name() { return this.recipe_name; }
    public String getDirections() { return this.directions; }
    public Image getPhoto() { return this.photo; }
    public int getTimes_favorited() { return this.times_favorited; }
}
