package com.quantitymeasurement.enums;

public enum WeightUnit implements IMeasurable {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

     //Converts a value in this unit to the base unit (Kilogram)
    @Override
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

     //Converts a value from the base unit (Kilogram) to this unit
    @Override
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }

    @Override
    public String getUnitName() {
        return name();
    }
}
