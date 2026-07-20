package com.quantitymeasurement.enums;

public enum LengthUnit {

    FEET(1.0),
    INCH(1.0 / 12);

    private double conversionFactor;
    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }
}
