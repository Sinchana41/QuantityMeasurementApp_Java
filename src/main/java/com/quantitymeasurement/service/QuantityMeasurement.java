package com.quantitymeasurement.service;

import com.quantitymeasurement.enums.LengthUnit;
import com.quantitymeasurement.model.QuantityLength;

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
}
