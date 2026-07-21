package com.quantitymeasurement.enums;

public enum WeightUnit {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

     //Converts a value in this unit to the base unit (Kilogram)
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

     //Converts a value from the base unit (Kilogram) to this unit
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }
}
