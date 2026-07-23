package com.quantitymeasurement.enums;

import com.quantitymeasurement.interfaces.IMeasurable;
import com.quantitymeasurement.interfaces.SupportsArithmetic;

import java.util.function.Function;

public enum TemperatureUnit implements IMeasurable {


    CELSIUS(
            celsius -> celsius,
            celsius -> celsius
    ),

    FAHRENHEIT(
            fahrenheit -> (fahrenheit - 32) * 5 / 9,
            celsius -> (celsius * 9 / 5) + 32
    ),

    KELVIN(
            kelvin -> kelvin - 273.15,
            celsius -> celsius + 273.15
    );

    private final Function<Double, Double> toBaseConverter;
    private final Function<Double, Double> fromBaseConverter;

    private final SupportsArithmetic supportsArithmetic = () -> false;

    TemperatureUnit(Function<Double, Double> toBaseConverter,
                    Function<Double, Double> fromBaseConverter) {

        this.toBaseConverter = toBaseConverter;
        this.fromBaseConverter = fromBaseConverter;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return toBaseConverter.apply(value);
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return fromBaseConverter.apply(baseValue);
    }

    @Override
    public double getConversionFactor() {
        return 1.0;
    }

    @Override
    public String getUnitName() {
        return name();
    }

    @Override
    public boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    @Override
    public void validateOperationSupport(String operation) {
        throw new UnsupportedOperationException(
                "Temperature does not support " + operation.toLowerCase() + " operation."
        );
    }
}
