package com.quantitymeasurement.service;

import com.quantitymeasurement.interfaces.IMeasurable;
import com.quantitymeasurement.model.Quantity;

public class QuantityMeasurement {

    // Compare two Quantity objects
    public <U extends IMeasurable> boolean compare(Quantity<U> first, Quantity<U> second) {

        if (first == null || second == null) {
            throw new IllegalArgumentException("Quantity objects cannot be null");
        }
        return first.equals(second);
    }

    //Convert one unit into another
    public <U extends IMeasurable> Quantity<U> convert(Quantity<U> quantity, U targetUnit) {

        if (quantity == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }
        return quantity.convertTo(targetUnit);
    }

    // Add two quantities
    public <U extends IMeasurable> Quantity<U> add(Quantity<U> first,Quantity<U> second){
        if(first == null || second == null){
            throw  new IllegalArgumentException("Quantity objects cannot be null");
        }
        return  first.add(second);
    }

    //Add two quantities to specified target unit
    public <U extends IMeasurable> Quantity<U> add(Quantity<U> first, Quantity<U> second, U targetUnit){
        if(first == null || second == null){
            throw  new IllegalArgumentException("Quantity objects cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("target unit cannot be null");
        }

        return first.add(second,targetUnit);
    }

}
