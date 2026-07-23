package com.bl.quantitymeasurement.dto;

import com.bl.quantitymeasurement.interfaces.IMeasurable;
import java.io.Serializable;

public class QuantityDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    // --- ENUMS IMPLEMENTING IMeasurable ---

    public enum LengthUnit implements IMeasurable {
        FEET(1.0),
        INCHES(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(1.0 / 30.48);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        @Override
        public double convertToBaseUnit(double value) {
            return value * conversionFactor;
        }

        @Override
        public double convertFromBaseUnit(double baseValue) {
            return baseValue / conversionFactor;
        }

        @Override
        public String getMeasurementType() {
            return "LengthUnit";
        }
    }

    public enum VolumeUnit implements IMeasurable {
        LITERS(1.0),
        GALLONS(3.78541),
        MILLILITERS(0.001);

        private final double conversionFactor;

        VolumeUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        @Override
        public double convertToBaseUnit(double value) {
            return value * conversionFactor;
        }

        @Override
        public double convertFromBaseUnit(double baseValue) {
            return baseValue / conversionFactor;
        }

        @Override
        public String getMeasurementType() {
            return "VolumeUnit";
        }
    }

    public enum WeightUnit implements IMeasurable {
        KILOGRAMS(1.0),
        GRAMS(0.001),
        POUNDS(0.453592),
        OUNCES(0.0283495);

        private final double conversionFactor;

        WeightUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        @Override
        public double convertToBaseUnit(double value) {
            return value * conversionFactor;
        }

        @Override
        public double convertFromBaseUnit(double baseValue) {
            return baseValue / conversionFactor;
        }

        @Override
        public String getMeasurementType() {
            return "WeightUnit";
        }
    }

    public enum TemperatureUnit implements IMeasurable {
        CELSIUS, FAHRENHEIT;

        @Override
        public double convertToBaseUnit(double value) {
            if (this == FAHRENHEIT) {
                return (value - 32.0) * 5.0 / 9.0;
            }
            return value;
        }

        @Override
        public double convertFromBaseUnit(double baseValue) {
            if (this == FAHRENHEIT) {
                return (baseValue * 9.0 / 5.0) + 32.0;
            }
            return baseValue;
        }

        @Override
        public String getMeasurementType() {
            return "TemperatureUnit";
        }
    }

    // --- DTO FIELDS & CONSTRUCTORS ---

    public double value;
    public String unit;
    public String measurementType;

    public QuantityDTO() {}

    public QuantityDTO(double value, IMeasurable unit) {
        this.value = value;
        this.unit = ((Enum<?>) unit).name();
        this.measurementType = unit.getMeasurementType();
    }

    public QuantityDTO(double value, String unit, String measurementType) {
        this.value = value;
        this.unit = unit;
        this.measurementType = measurementType;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}