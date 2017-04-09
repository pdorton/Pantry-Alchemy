package com.thealchemists.pantryalchemy;

public class Measurement {
    // Data members
    private String unit;
    private String unit_name;

    // Constructor
    public Measurement(String unit, String unit_name) {
        this.unit = unit;
        this.unit_name = unit_name;
    }

    // Methods
    public String getUnit() { return this.unit; }
    public String getUnit_name() { return this.unit_name; }
}
