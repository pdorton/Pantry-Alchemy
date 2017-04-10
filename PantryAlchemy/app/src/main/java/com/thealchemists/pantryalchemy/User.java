package com.thealchemists.pantryalchemy;

import java.util.ArrayList;

public class User {
    // Data members
    private String first_name;
    private String last_name;
    private String email;
    private ArrayList<Recipe> favorites;
    private ArrayList<Ingredient> shoppingList;
    private ArrayList<Ingredient> pantry;

    // Constructor
    public User(String first_name, String last_name, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.favorites = new ArrayList<Recipe>();
        this.shoppingList = new ArrayList<Ingredient>();
        this.pantry = new ArrayList<Ingredient>();
    }

    // Methods
    public String getFirst_name() { return this.first_name; }
    public String getLast_name() { return this.last_name; }
    public String getEmail() { return this.email; }
    public ArrayList<Recipe> getFavorites() { return this.favorites; }
    public ArrayList<Ingredient> getShoppingList() { return this.shoppingList; }
    public ArrayList<Ingredient> getPantry() { return this.pantry; }
    public void addToFavorites(Recipe r) { this.favorites.add(r); }
    public void removeFromFavorites(Recipe r) { this.favorites.remove(r); }
    public void addToShoppingList(Ingredient i) { this.shoppingList.add(i); }
    public void removeFromShoppingList(Ingredient i) { this.shoppingList.remove(i); }
    public void addToPantry(Ingredient i) { this.pantry.add(i); }
    public void removeFromPantry(Ingredient i) { this.pantry.remove(i); }
}
