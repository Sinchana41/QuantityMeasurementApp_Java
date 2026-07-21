package com.quantitymeasurement.model;

import com.quantitymeasurement.enums.LengthUnit;

public class QuantityLength {

    private static final double EPSILON = 0.000001;

    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {

        validateValue(value);
        validateUnit(unit);

        this.value = value;
        this.unit = unit;
    }

    public static double convert(double value, LengthUnit sourceUnit, LengthUnit targetUnit) {

        validateValue(value);
        validateUnit(sourceUnit);
        validateUnit(targetUnit);

        double baseValue = sourceUnit.convertToBaseUnit(value);

        return targetUnit.convertFromBaseUnit(baseValue);
    }

    public static QuantityLength add(QuantityLength first, QuantityLength second) {

        if (first == null || second == null) {
            throw new IllegalArgumentException("Quantity cannot be null.");
        }

        return first.add(second);
    }

    public static QuantityLength add(double firstValue, LengthUnit firstUnit, double secondValue, LengthUnit secondUnit) {

        QuantityLength first = new QuantityLength(firstValue, firstUnit);

        QuantityLength second = new QuantityLength(secondValue, secondUnit);

        return first.add(second);
    }

    public static QuantityLength add(QuantityLength first, QuantityLength second, LengthUnit targetUnit) {

        if (first == null || second == null) {
            throw new IllegalArgumentException("Quantity cannot be null.");
        }

        validateUnit(targetUnit);

        return first.add(second, targetUnit);
    }

    public static QuantityLength add(double firstValue, LengthUnit firstUnit, double secondValue, LengthUnit secondUnit, LengthUnit targetUnit) {

        QuantityLength first = new QuantityLength(firstValue, firstUnit);

        QuantityLength second = new QuantityLength(secondValue, secondUnit);

        return add(first, second, targetUnit);
    }

    // Validation
    private static void validateValue(double value) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite.");
        }
    }

    private static void validateUnit(LengthUnit unit) {

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null.");
        }
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    // UC5 - Conversion
    public QuantityLength convertTo(LengthUnit targetUnit) {

        validateUnit(targetUnit);

        double baseValue = unit.convertToBaseUnit(value);

        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

        return new QuantityLength(convertedValue, targetUnit);
    }

    // UC6 - Addition
    public QuantityLength add(QuantityLength other) {

        if (other == null) {
            throw new IllegalArgumentException("Second quantity cannot be null.");
        }

        return addInternal(other, this.unit);
    }

    // UC7 - Addition With Target Unit
    public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {

        if (other == null) {
            throw new IllegalArgumentException("Second quantity cannot be null.");
        }

        validateUnit(targetUnit);

        return addInternal(other, targetUnit);
    }

    // Common Addition Logic
    private QuantityLength addInternal(QuantityLength other, LengthUnit targetUnit) {

        double firstBase = unit.convertToBaseUnit(value);

        double secondBase = other.unit.convertToBaseUnit(other.value);

        double totalBase = firstBase + secondBase;

        double result = targetUnit.convertFromBaseUnit(totalBase);

        return new QuantityLength(result, targetUnit);
    }

    // Equals
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (!(obj instanceof QuantityLength)) return false;

        QuantityLength other = (QuantityLength) obj;

        return Math.abs(unit.convertToBaseUnit(value) - other.unit.convertToBaseUnit(other.value)) < EPSILON;
    }

    @Override
    public int hashCode() {

        return Double.hashCode(value);
    }

    @Override
    public String toString() {

        return "QuantityLength{" + "value=" + value + ", unit=" + unit + '}';
    }
}