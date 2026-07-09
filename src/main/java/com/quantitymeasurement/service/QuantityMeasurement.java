package com.quantitymeasurement.service;

import com.quantitymeasurement.model.Feet;
import com.quantitymeasurement.model.Inches;

public class QuantityMeasurement {

    public boolean compare(Feet feet1 , Feet feet2){
        return feet1.equals(feet2);
    }

    public boolean compare(Inches inches1,Inches inches2){
        return inches1.equals(inches2);
    }
}
