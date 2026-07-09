package com.quantitymeasurement.model;

import java.util.Objects;

public class Inches {

    private final double value;

    public Inches(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Inches){
            Inches inches = (Inches) o;
            return this.value == inches.value;
        }
        return false;
    }
}
