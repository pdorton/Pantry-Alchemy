/**
 * Created by mfanx2 on 4/9/2017.
 */

package com.thealchemists.pantryalchemy;

public class Ingredient {
    // Data members
    private String ing_name;
    private Measurement measurement;

    // Constructor
    public Ingredient(String ing_name, Measurement measurement) {
        this.ing_name = ing_name;
        this.measurement = measurement;
    }

    // Methods
    public String getName() { return this.ing_name; }
    public Measurement getMeasurement() { return this.measurement; }
}