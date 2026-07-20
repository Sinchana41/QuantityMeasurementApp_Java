package com.quantitymeasurement.enums;

public enum LengthUnit {

    FEET(1.0),
    INCH(1.0 / 12),
    YARD(3.0),
    CENTIMETER(0.393701 / 12);

    private double conversionFactor;
    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }
}
