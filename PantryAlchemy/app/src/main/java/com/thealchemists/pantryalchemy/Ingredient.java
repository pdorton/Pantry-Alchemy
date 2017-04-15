package com.thealchemists.pantryalchemy;

public class Ingredient {
    // Data members
    private String ing_name;
    private String unit;
    private int quantity;


    // Constructor
    public Ingredient(String ing_name, String unit, int quantity) {
        this.ing_name = ing_name;
        this.unit = unit;
        this.quantity = quantity;
    }


    // Methods
    public String getName() { return this.ing_name; }
    public String getUnit() { return this.unit; }
    public int getQuantity() { return this.quantity; }
}