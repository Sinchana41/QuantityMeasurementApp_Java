package com.quantitymeasurement.model;

public class Feet {

    private final double value;

    public Feet(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
       if( obj instanceof  Feet){
           Feet feet = (Feet) obj;
           return this.value == feet.value;
       }
       return false;
    }

}
