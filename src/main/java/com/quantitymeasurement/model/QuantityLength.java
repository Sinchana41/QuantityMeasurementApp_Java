package com.quantitymeasurement.model;

import com.quantitymeasurement.enums.LengthUnit;

public class QuantityLength {

    private double value;
    private LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

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

            return Double.compare(this.toBaseUnit(),length.toBaseUnit()) == 0;
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
