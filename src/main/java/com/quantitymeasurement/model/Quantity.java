package com.quantitymeasurement.model;

import com.quantitymeasurement.interfaces.IMeasurable;

public class Quantity<U extends IMeasurable>{

    private static final double EPSILON = 0.000001;

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = toBaseUnit();

        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(convertedValue, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {

        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        if (other == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double totalBaseValue = this.toBaseUnit() + other.toBaseUnit();

        double convertedValue = targetUnit.convertFromBaseUnit(totalBaseValue);

        return new Quantity<>(convertedValue, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Quantity<?> other)) {
            return false;
        }

        if (this.unit.getClass() != other.unit.getClass()) {
            return false;
        }

        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toBaseUnit());
    }

    @Override
    public String toString() {

        return "Quantity{" +
                "value=" + value +
                ", unit=" + unit +
                '}';
    }
}
