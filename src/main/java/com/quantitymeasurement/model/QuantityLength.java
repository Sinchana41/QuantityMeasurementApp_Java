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

            throw new IllegalArgumentException("Value must be finite");

        }
    }
     // Validate measurement unit
    private static void validateUnit(LengthUnit unit) {

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
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

        double convertedValue = convert(value, unit, targetUnit);

        return new QuantityLength(convertedValue, targetUnit);
    }


    //Static conversion API
    public static double convert(double value, LengthUnit sourceUnit, LengthUnit targetUnit) {

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



    // UC6
    public QuantityLength add(QuantityLength other) {

        if (other == null) {
            throw new IllegalArgumentException("Second quantity cannot be null.");
        }

        double firstBase = this.toBaseUnit();

        double secondBase = other.toBaseUnit();

        double totalBase = firstBase + secondBase;

        double result = totalBase / this.unit.getConversionFactor();

        return new QuantityLength(result,this.unit);
    }

     // Static add method
    public static QuantityLength add(QuantityLength first,QuantityLength second) {

        if (first == null || second == null) {
            throw new IllegalArgumentException("Quantity cannot be null.");
        }
        return first.add(second);
    }

     // Overloaded add method
    public static QuantityLength add(double firstValue, LengthUnit firstUnit, double secondValue, LengthUnit secondUnit) {

        QuantityLength first = new QuantityLength(firstValue,firstUnit);

        QuantityLength second = new QuantityLength(secondValue,secondUnit);

        return first.add(second);
    }

    // UC7 - ADDITION WITH TARGET UNIT
    //1 FEET + 12 INCH, target = YARD, Result = 0.666667 YARD

    public QuantityLength add(QuantityLength other,LengthUnit targetUnit) {

        if (other == null) {
            throw new IllegalArgumentException("Second quantity cannot be null");
        }

        validateUnit(targetUnit);

        return addInternal(other,targetUnit);
    }

     //Adds two QuantityLength objects with an explicitly specified target unit
    public static QuantityLength add(QuantityLength first, QuantityLength second, LengthUnit targetUnit) {

        if (first == null || second == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }

        validateUnit(targetUnit);

        return first.add(second,targetUnit);
    }

     // Static overloaded method using raw values
     //Example:add(1, FEET, 12, INCH,YARD)
    public static QuantityLength add(double firstValue,LengthUnit firstUnit,
                                     double secondValue, LengthUnit secondUnit,
                                     LengthUnit targetUnit) {

        QuantityLength first = new QuantityLength(firstValue,firstUnit);

        QuantityLength second = new QuantityLength(secondValue,secondUnit);

        return add(first, second, targetUnit);
    }

    private QuantityLength addInternal(QuantityLength other, LengthUnit targetUnit) {

        double firstBaseValue = this.toBaseUnit();

        double secondBaseValue = other.toBaseUnit();

        double totalBaseValue = firstBaseValue + secondBaseValue;

        double resultValue = totalBaseValue / targetUnit.getConversionFactor();

        return new QuantityLength(resultValue, targetUnit);
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
