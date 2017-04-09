package com.thealchemists.pantryalchemy;

import java.util.ArrayList;

public class User {
    // Data members
    private String first_name;
    private String last_name;
    private String email;
    public ArrayList<Ingredient> favorites;

    // Constructor
    public User(String first_name, String last_name, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.favorites = new ArrayList<Ingredient>();
    }

    // Methods
    public String getFirst_name() { return this.first_name; }
    public String getLast_name() { return this.last_name; }
    public String getEmail() { return this.email; }
    public ArrayList<Ingredient> getFavorites() { return this.favorites; }
    public void addToFavorites(Ingredient i) { this.favorites.add(i); }
    public void removeFromFavorites(Ingredient i) { this.favorites.remove(i); }
}
