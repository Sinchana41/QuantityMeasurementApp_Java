package com.quantitymeasurement.model;

import com.quantitymeasurement.enums.ArithmeticOperation;
import com.quantitymeasurement.interfaces.IMeasurable;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null.");
        }

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite.");
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

    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null.");
        }

        if (!unit.getClass().equals(targetUnit.getClass())) {
            throw new IllegalArgumentException("Cannot convert between different measurement categories.");
        }

        double baseValue = unit.convertToBaseUnit(value);

        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(roundToTwoDecimals(convertedValue), targetUnit);
    }

    //UC13 -Centralized Validation Helper
    private void validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean targetUnitRequired) {

        if (other == null) {
            throw new IllegalArgumentException("Other quantity cannot be null.");
        }

        if (this.unit == null || other.unit == null) {
            throw new IllegalArgumentException("Unit cannot be null.");
        }

        if (!this.unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Cannot perform arithmetic between different measurement categories.");
        }

        if (!Double.isFinite(this.value)) {
            throw new IllegalArgumentException("Current quantity value must be finite.");
        }

        if (!Double.isFinite(other.value)) {
            throw new IllegalArgumentException("Other quantity value must be finite.");
        }

        if (targetUnitRequired && targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null.");
        }
    }

    private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation operation) {

        double firstBaseValue = unit.convertToBaseUnit(value);

        double secondBaseValue = other.unit.convertToBaseUnit(other.value);

        return operation.compute(firstBaseValue, secondBaseValue);
    }

    public Quantity<U> add(Quantity<U> other) {

        validateArithmeticOperands(other, null, false);

        double baseResult = performBaseArithmetic(other, ArithmeticOperation.ADD);

        double convertedResult = unit.convertFromBaseUnit(baseResult);

        return new Quantity<>(roundToTwoDecimals(convertedResult), unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double baseResult = performBaseArithmetic(other, ArithmeticOperation.ADD);

        double convertedResult = targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(roundToTwoDecimals(convertedResult), targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {

        validateArithmeticOperands(other, null, false);

        double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

        double convertedResult = unit.convertFromBaseUnit(baseResult);

        return new Quantity<>(roundToTwoDecimals(convertedResult), unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

        double convertedResult = targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(roundToTwoDecimals(convertedResult), targetUnit);
    }

    public double divide(Quantity<U> other) {

        validateArithmeticOperands(other, null, false);

        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    private double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Quantity<?> other = (Quantity<?>) obj;

        if (!unit.getClass().equals(other.unit.getClass())) {
            return false;
        }

        double thisBaseValue = unit.convertToBaseUnit(value);
        double otherBaseValue = other.unit.convertToBaseUnit(other.value);

        return Double.compare(thisBaseValue, otherBaseValue) == 0;
    }

    @Override
    public String toString() {
        return "Quantity{" +
                "value=" + value +
                ", unit=" + unit +
                '}';
    }
}
