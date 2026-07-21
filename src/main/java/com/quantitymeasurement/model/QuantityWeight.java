package com.quantitymeasurement.model;

import com.quantitymeasurement.enums.WeightUnit;

public class QuantityWeight {
    private static final double EPSILON = 0.000001;

    private final double value;
    private final WeightUnit unit;

    public QuantityWeight(double value, WeightUnit unit) {

        validateValue(value);
        validateUnit(unit);

        this.value = value;
        this.unit = unit;
    }

    public static double convert(double value, WeightUnit sourceUnit, WeightUnit targetUnit) {

        validateValue(value);
        validateUnit(sourceUnit);
        validateUnit(targetUnit);

        double baseValue = sourceUnit.convertToBaseUnit(value);

        return targetUnit.convertFromBaseUnit(baseValue);
    }

    public static QuantityWeight add(QuantityWeight first, QuantityWeight second) {

        if (first == null || second == null) {
            throw new IllegalArgumentException("Quantity cannot be null.");
        }

        return first.add(second);
    }

    // Conversion
    public static QuantityWeight add(QuantityWeight first, QuantityWeight second, WeightUnit targetUnit) {

        if (first == null || second == null) {
            throw new IllegalArgumentException("Quantity cannot be null.");
        }

        validateUnit(targetUnit);

        return first.add(second, targetUnit);
    }

    private static void validateValue(double value) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite.");
        }
    }

    // Addition (Default Target Unit)
    private static void validateUnit(WeightUnit unit) {

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null.");
        }
    }

    public double getValue() {
        return value;
    }

    // Addition (Explicit Target Unit)
    public WeightUnit getUnit() {
        return unit;
    }

    public QuantityWeight convertTo(WeightUnit targetUnit) {

        validateUnit(targetUnit);

        double baseValue = unit.convertToBaseUnit(value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

        return new QuantityWeight(convertedValue, targetUnit);
    }

    // Common Addition Logic
    public QuantityWeight add(QuantityWeight other) {

        if (other == null) {
            throw new IllegalArgumentException("Second quantity cannot be null.");
        }

        return addInternal(other, this.unit);
    }

    // Validation
    public QuantityWeight add(QuantityWeight other, WeightUnit targetUnit) {

        if (other == null) {
            throw new IllegalArgumentException("Second quantity cannot be null.");
        }

        validateUnit(targetUnit);

        return addInternal(other, targetUnit);
    }

    private QuantityWeight addInternal(QuantityWeight other, WeightUnit targetUnit) {

        double firstBase = unit.convertToBaseUnit(value);
        double secondBase = other.unit.convertToBaseUnit(other.value);

        double totalBase = firstBase + secondBase;

        double result = targetUnit.convertFromBaseUnit(totalBase);

        return new QuantityWeight(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        QuantityWeight other = (QuantityWeight) obj;

        return Math.abs(unit.convertToBaseUnit(value) - other.unit.convertToBaseUnit(other.value)) < EPSILON;
    }

    @Override
    public int hashCode() {

        return Double.hashCode(value);
    }

    @Override
    public String toString() {

        return "QuantityWeight{" + "value=" + value + ", unit=" + unit + '}';
    }
}
