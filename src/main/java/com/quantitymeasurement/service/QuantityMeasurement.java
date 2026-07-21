package com.quantitymeasurement.service;

import com.quantitymeasurement.enums.LengthUnit;
import com.quantitymeasurement.enums.WeightUnit;
import com.quantitymeasurement.model.QuantityLength;
import com.quantitymeasurement.model.QuantityWeight;

public class QuantityMeasurement {

    // Compare two QuantityLength objects
    public boolean compare(QuantityLength first,QuantityLength second) {

        if (first == null || second == null) {
            throw new IllegalArgumentException("Quantity objects cannot be null");
        }
        return first.equals(second);
    }

    //Convert one unit into another
    public QuantityLength convert(QuantityLength quantity, LengthUnit targetUnit) {

        if (quantity == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }
        return quantity.convertTo(targetUnit);
    }

    // Add two quantities
    public QuantityLength add(QuantityLength first,QuantityLength second){
        if(first == null || second == null){
            throw  new IllegalArgumentException("Quantity objects cannot be null");
        }
        return  first.add(second);
    }

    //Add two quantities to specified target unit

    public QuantityLength add(QuantityLength first,QuantityLength second,LengthUnit targetUnit){
        if(first == null || second == null){
            throw  new IllegalArgumentException("Quantity objects cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("target unit cannot be null");
        }

        return first.add(second,targetUnit);
    }

    // Compare two QuantityWeight objects
    public boolean compare(QuantityWeight first, QuantityWeight second) {

        if (first == null || second == null) {
            throw new IllegalArgumentException("Quantity objects cannot be null");
        }
        return first.equals(second);
    }

    //Convert one unit into another
    public QuantityWeight convert(QuantityWeight quantity, WeightUnit targetUnit) {

        if (quantity == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }
        return quantity.convertTo(targetUnit);
    }

    // Add two quantities
    public QuantityWeight add(QuantityWeight first,QuantityWeight second){
        if(first == null || second == null){
            throw  new IllegalArgumentException("Quantity objects cannot be null");
        }
        return  first.add(second);
    }

    //Add two quantities to specified target unit
    public QuantityWeight add(QuantityWeight first,QuantityWeight second,WeightUnit targetUnit){
        if(first == null || second == null){
            throw  new IllegalArgumentException("Quantity objects cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("target unit cannot be null");
        }

        return first.add(second,targetUnit);
    }
}
