package com.quantitymeasurement.service;

import com.quantitymeasurement.enums.LengthUnit;
import com.quantitymeasurement.enums.TemperatureUnit;
import com.quantitymeasurement.enums.VolumeUnit;
import com.quantitymeasurement.enums.WeightUnit;
import com.quantitymeasurement.model.Quantity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementTest {

    private static final double EPSILON = 0.000001;
    QuantityMeasurement quantityMeasurement = new QuantityMeasurement();

    @Test
    void testCompareLength() {

        Quantity<LengthUnit> first = new Quantity<>(1, LengthUnit.FEET);

        Quantity<LengthUnit> second = new Quantity<>(12, LengthUnit.INCH);

        assertTrue(quantityMeasurement.compare(first, second));
    }

    @Test
    void testCompareWeight() {

        Quantity<WeightUnit> first = new Quantity<>(1, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> second = new Quantity<>(1000, WeightUnit.GRAM);

        assertTrue(quantityMeasurement.compare(first, second));
    }

    @Test
    void testConvertLength() {

        Quantity<LengthUnit> result = quantityMeasurement.convert(new Quantity<>(1, LengthUnit.FEET), LengthUnit.INCH);

        assertEquals(12.0, result.getValue(), 0.000001);
    }

    @Test
    void testConvertWeight() {

        Quantity<WeightUnit> result = quantityMeasurement.convert(new Quantity<>(1, WeightUnit.KILOGRAM), WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), 0.000001);
    }

    @Test
    void testAddLength() {

        Quantity<LengthUnit> result = quantityMeasurement.add(new Quantity<>(1, LengthUnit.FEET), new Quantity<>(12, LengthUnit.INCH));

        assertEquals(2.0, result.getValue(), 0.000001);
    }

    @Test
    void testAddWeight() {

        Quantity<WeightUnit> result = quantityMeasurement.add(new Quantity<>(1, WeightUnit.KILOGRAM), new Quantity<>(1000, WeightUnit.GRAM));

        assertEquals(2.0, result.getValue(), 0.000001);
    }

    @Test
    void testAddLengthTargetUnit() {

        Quantity<LengthUnit> result = quantityMeasurement.add(new Quantity<>(1, LengthUnit.FEET), new Quantity<>(12, LengthUnit.INCH), LengthUnit.INCH);

        assertEquals(24.0, result.getValue(), 0.000001);
    }

    @Test
    void testAddWeightTargetUnit() {

        Quantity<WeightUnit> result = quantityMeasurement.add(new Quantity<>(1, WeightUnit.KILOGRAM), new Quantity<>(1000, WeightUnit.GRAM), WeightUnit.GRAM);

        assertEquals(2000.0, result.getValue(), 0.000001);
    }

    @Test
    void testCompareNull() {

        assertThrows(IllegalArgumentException.class, () -> quantityMeasurement.compare(null, new Quantity<>(1, LengthUnit.FEET)));
    }

    @Test
    void testConvertNull() {

        assertThrows(IllegalArgumentException.class, () -> quantityMeasurement.convert(null, LengthUnit.FEET));
    }

    @Test
    void testAddNull() {

        assertThrows(IllegalArgumentException.class, () -> quantityMeasurement.add(null, new Quantity<>(1, WeightUnit.KILOGRAM)));
    }

    @Test
    void testCompareVolume() {

        Quantity<VolumeUnit> litre = new Quantity<>(1, VolumeUnit.LITRE);

        Quantity<VolumeUnit> millilitre = new Quantity<>(1000, VolumeUnit.MILLILITRE);

        assertTrue(quantityMeasurement.compare(litre, millilitre));
    }

    @Test
    void testConvertVolume() {

        Quantity<VolumeUnit> result = quantityMeasurement.convert(new Quantity<>(1, VolumeUnit.LITRE), VolumeUnit.MILLILITRE);

        assertEquals(1000, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    @Test
    void testAddVolume() {

        Quantity<VolumeUnit> result = quantityMeasurement.add(new Quantity<>(1, VolumeUnit.LITRE), new Quantity<>(1000, VolumeUnit.MILLILITRE));

        assertEquals(2, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    void testAddVolumeWithTargetUnit() {

        Quantity<VolumeUnit> result = quantityMeasurement.add(new Quantity<>(1, VolumeUnit.LITRE), new Quantity<>(1000, VolumeUnit.MILLILITRE), VolumeUnit.MILLILITRE);

        assertEquals(2000, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    @Test
    void testCompareNullQuantity() {

        assertThrows(IllegalArgumentException.class, () -> quantityMeasurement.compare(null, new Quantity<>(1, VolumeUnit.LITRE)));
    }

    @Test
    void testConvertNullQuantity() {

        assertThrows(IllegalArgumentException.class, () -> quantityMeasurement.convert(null, VolumeUnit.LITRE));
    }

    @Test
    void testSubtractSameUnit() {

        Quantity<LengthUnit> first = new Quantity<>(10, LengthUnit.FEET);

        Quantity<LengthUnit> second = new Quantity<>(5, LengthUnit.FEET);

        Quantity<LengthUnit> result = quantityMeasurement.subtract(first, second);

        assertEquals(5, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testSubtractDifferentUnit() {

        Quantity<LengthUnit> feet = new Quantity<>(10, LengthUnit.FEET);

        Quantity<LengthUnit> inch = new Quantity<>(6, LengthUnit.INCH);

        Quantity<LengthUnit> result = quantityMeasurement.subtract(feet, inch);

        assertEquals(9.5, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testSubtractWithTargetUnit() {

        Quantity<LengthUnit> feet = new Quantity<>(10, LengthUnit.FEET);

        Quantity<LengthUnit> inch = new Quantity<>(6, LengthUnit.INCH);

        Quantity<LengthUnit> result = quantityMeasurement.subtract(feet, inch, LengthUnit.INCH);

        assertEquals(114, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    @Test
    void testDivideSameUnit() {

        Quantity<LengthUnit> first = new Quantity<>(10, LengthUnit.FEET);

        Quantity<LengthUnit> second = new Quantity<>(2, LengthUnit.FEET);

        double result = quantityMeasurement.divide(first, second);

        assertEquals(5.0, result, EPSILON);
    }

    @Test
    void testDivideDifferentUnit() {

        Quantity<LengthUnit> inch = new Quantity<>(24, LengthUnit.INCH);

        Quantity<LengthUnit> feet = new Quantity<>(2, LengthUnit.FEET);

        double result = quantityMeasurement.divide(inch, feet);

        assertEquals(1.0, result, EPSILON);
    }

    @Test
    void testDivideByZero() {

        Quantity<LengthUnit> first = new Quantity<>(10, LengthUnit.FEET);

        Quantity<LengthUnit> second = new Quantity<>(0, LengthUnit.FEET);

        assertThrows(ArithmeticException.class, () -> quantityMeasurement.divide(first, second));
    }

    @Test
    void testSubtractNullFirstQuantity() {

        Quantity<LengthUnit> second = new Quantity<>(5, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> quantityMeasurement.subtract(null, second));
    }

    @Test
    void testSubtractNullSecondQuantity() {

        Quantity<LengthUnit> first = new Quantity<>(10, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> quantityMeasurement.subtract(first, null));
    }

    @Test
    void testDivideNullFirstQuantity() {

        Quantity<LengthUnit> second = new Quantity<>(5, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> quantityMeasurement.divide(null, second));
    }

    @Test
    void testDivideNullSecondQuantity() {

        Quantity<LengthUnit> first = new Quantity<>(10, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> quantityMeasurement.divide(first, null));
    }

    @Test
    void testCompareTemperature() {

        Quantity<TemperatureUnit> celsius = new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> fahrenheit = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        assertTrue(quantityMeasurement.compare(celsius, fahrenheit));
    }

    @Test
    void testConvertTemperature() {

        Quantity<TemperatureUnit> celsius = new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> expected = new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT);

        assertEquals(expected, quantityMeasurement.convert(celsius, TemperatureUnit.FAHRENHEIT));
    }

    @Test
    void testAddTemperature_ShouldThrowException() {

        Quantity<TemperatureUnit> first = new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> second = new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(UnsupportedOperationException.class, () -> quantityMeasurement.add(first, second));
    }

    @Test
    void testSubtractTemperature_ShouldThrowException() {

        Quantity<TemperatureUnit> first = new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> second = new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(UnsupportedOperationException.class, () -> quantityMeasurement.subtract(first, second));
    }

    @Test
    void testDivideTemperature_ShouldThrowException() {

        Quantity<TemperatureUnit> first = new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> second = new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(UnsupportedOperationException.class, () -> quantityMeasurement.divide(first, second));
    }
}