package com.quantitymeasurement.model;

import com.quantitymeasurement.enums.LengthUnit;

public class QuantityLength {

    private static final double EPSILON = 0.000001;
    private double value;
    private LengthUnit unit;


    public QuantityLength(double value, LengthUnit unit) {
        validateValue(value);
        validateUnit(unit);

        this.value = value;
        this.unit = unit;
    }

    // Validate measurement value
    private static void validateValue(double value) {

        if (!Double.isFinite(value)) {

            throw new IllegalArgumentException(
                    "Value must be finite.");

        }
    }
     // Validate measurement unit
    private static void validateUnit(LengthUnit unit) {

        if (unit == null) {

            throw new IllegalArgumentException(
                    "Unit cannot be null.");

        }
    }
    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    /**
     * Converts current object into target unit.
     * Returns a NEW QuantityLength object.
     */
    public QuantityLength convertTo(LengthUnit targetUnit) {

        validateUnit(targetUnit);

        double convertedValue =
                convert(value, unit, targetUnit);

        return new QuantityLength(
                convertedValue,
                targetUnit);
    }


    //Static conversion API
    public static double convert(double value,
                                 LengthUnit sourceUnit,
                                 LengthUnit targetUnit) {

        validateValue(value);

        validateUnit(sourceUnit);

        validateUnit(targetUnit);

        // Convert source to base unit (Feet)

        double baseValue = value * sourceUnit.getConversionFactor();

        // Convert base unit to target unit

        return baseValue / targetUnit.getConversionFactor();
    }

     // Converts current object to base unit
    private double toBaseUnit() {

        return value * unit.getConversionFactor();
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toBaseUnit());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof QuantityLength){
            QuantityLength length = (QuantityLength) obj;

            return Math.abs(this.toBaseUnit() - length.toBaseUnit()) < EPSILON;
        }
        return false;
    }

    @Override
    public String toString() {
        return "QuantityLength{" +
                "value=" + value +
                ", unit=" + unit +
                '}';
    }
}
