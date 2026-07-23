package com.quantitymeasurement.interfaces;

public interface IMeasurable {

    SupportsArithmetic  supportsArithmetic = () -> true;

    double getConversionFactor();

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    String getUnitName();

    default boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    default void validateOperationSupport(String operation) {
        // Default implementation does nothing
        // Units supporting all arithmetic operations inherit this behavior
    }
}
