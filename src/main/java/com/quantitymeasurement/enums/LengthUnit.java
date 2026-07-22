package com.quantitymeasurement.enums;

import com.quantitymeasurement.interfaces.IMeasurable;

public enum LengthUnit implements IMeasurable {

    FEET(1.0),
    INCH(1.0 / 12),
    YARD(3.0),
    CENTIMETER(0.393701 / 12);

    private double conversionFactor;
    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    // Converts the given value into the base unit (Feet)
    @Override
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

     // Converts the base unit (Feet) into this unit
     @Override
     public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
     }

    @Override
     public String getUnitName() {
        return name();
    }
}
