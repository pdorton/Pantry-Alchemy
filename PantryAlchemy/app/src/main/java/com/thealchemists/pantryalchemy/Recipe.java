package com.thealchemists.pantryalchemy;

import android.media.Image;

public class Recipe {
    // Data members
    private String recipe_name;
    private String directions;
    private Image photo;
    private int times_favorited;

    // Constructor
    public Recipe(String recipe_name, String directions, Image photo, int times_favorited) {
        this.recipe_name = recipe_name;
        this.directions = directions;
        this.photo = photo;
        this.times_favorited = times_favorited;
    }

    // Methods
    public String getRecipe_name() { return this.recipe_name; }
    public String getDirections() { return this.directions; }
    public Image getPhoto() { return this.photo; }
    public int getTimes_favorited() { return this.times_favorited; }
}
