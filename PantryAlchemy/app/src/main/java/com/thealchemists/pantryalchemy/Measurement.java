package com.thealchemists.pantryalchemy;

public class Measurement {
    // Data members
    private int quantity;
    private String unit;

    // Constructor
    public Measurement(int quantity, String unit) {
        this.quantity = quantity;
        this.unit = unit;
    }

    // Methods
    public int getQuantity() { return this.quantity; }
    public String getUnit() { return this.unit; }
}
