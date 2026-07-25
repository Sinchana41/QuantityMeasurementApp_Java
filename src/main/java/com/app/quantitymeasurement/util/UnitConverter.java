package com.app.quantitymeasurement.util;

import com.app.quantitymeasurement.exception.QuantityMeasurementException;

public class UnitConverter {

    // 1. Convert any value to Base Unit
    public static double convertToBase(double value, String unit, String category) {
        return switch (category.toUpperCase()) {
            case "LENGTHUNIT" -> convertLengthToBase(value, unit.toUpperCase());
            case "WEIGHTUNIT" -> convertWeightToBase(value, unit.toUpperCase());
            case "TEMPERATURE" -> convertTemperatureToBase(value, unit.toUpperCase());
            default -> throw new QuantityMeasurementException("Unsupported category: " + category);
        };
    }

    // 2. Convert value from Base Unit to Target Unit
    public static double convertFromBase(double baseValue, String targetUnit, String category) {
        return switch (category.toUpperCase()) {
            case "LENGTHUNIT" -> convertLengthFromBase(baseValue, targetUnit.toUpperCase());
            case "WEIGHTUNIT" -> convertWeightFromBase(baseValue, targetUnit.toUpperCase());
            case "TEMPERATURE" -> convertTemperatureFromBase(baseValue, targetUnit.toUpperCase());
            default -> throw new QuantityMeasurementException("Unsupported category: " + category);
        };
    }

    // --- Length Conversions (Base: INCHES) ---
    private static double convertLengthToBase(double val, String unit) {
        return switch (unit) {
            case "FEET" -> val * 12.0;
            case "INCHES" -> val;
            case "YARDS" -> val * 36.0;
            case "CENTIMETERS" -> val / 2.54;
            default -> throw new QuantityMeasurementException("Invalid length unit: " + unit);
        };
    }

    private static double convertLengthFromBase(double baseVal, String unit) {
        return switch (unit) {
            case "FEET" -> baseVal / 12.0;
            case "INCHES" -> baseVal;
            case "YARDS" -> baseVal / 36.0;
            case "CENTIMETERS" -> baseVal * 2.54;
            default -> throw new QuantityMeasurementException("Invalid length unit: " + unit);
        };
    }

    // --- Weight Conversions (Base: KILOGRAMS) ---
    private static double convertWeightToBase(double val, String unit) {
        return switch (unit) {
            case "KILOGRAMS" -> val;
            case "GRAMS" -> val / 1000.0;
            case "TONNES" -> val * 1000.0;
            case "POUNDS" -> val * 0.453592;
            default -> throw new QuantityMeasurementException("Invalid weight unit: " + unit);
        };
    }

    private static double convertWeightFromBase(double baseVal, String unit) {
        return switch (unit) {
            case "KILOGRAMS" -> baseVal;
            case "GRAMS" -> baseVal * 1000.0;
            case "TONNES" -> baseVal / 1000.0;
            case "POUNDS" -> baseVal / 0.453592;
            default -> throw new QuantityMeasurementException("Invalid weight unit: " + unit);
        };
    }

    // --- Temperature Conversions (Base: CELSIUS) ---
    private static double convertTemperatureToBase(double val, String unit) {
        return switch (unit) {
            case "CELSIUS" -> val;
            case "FAHRENHEIT" -> (val - 32) * 5.0 / 9.0;
            case "KELVIN" -> val - 273.15;
            default -> throw new QuantityMeasurementException("Invalid temperature unit: " + unit);
        };
    }

    private static double convertTemperatureFromBase(double celsiusVal, String unit) {
        return switch (unit) {
            case "CELSIUS" -> celsiusVal;
            case "FAHRENHEIT" -> (celsiusVal * 9.0 / 5.0) + 32;
            case "KELVIN" -> celsiusVal + 273.15;
            default -> throw new QuantityMeasurementException("Invalid temperature unit: " + unit);
        };
    }
}