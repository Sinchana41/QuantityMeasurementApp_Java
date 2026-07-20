package com.quantitymeasurement.service;

import com.quantitymeasurement.model.QuantityLength;

public class QuantityMeasurement {

    public boolean compare(QuantityLength first,QuantityLength second) {

        return first.equals(second);
    }
}
