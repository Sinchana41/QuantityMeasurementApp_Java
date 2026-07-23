package com.bl.quantitymeasurement.interfaces;

public interface IMeasurable {

    double convertToBaseUnit(double value);
    double convertFromBaseUnit(double baseValue);
    String getMeasurementType();

}
