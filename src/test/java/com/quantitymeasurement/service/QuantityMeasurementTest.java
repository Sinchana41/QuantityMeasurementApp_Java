package com.quantitymeasurement.service;

import com.quantitymeasurement.enums.LengthUnit;
import com.quantitymeasurement.enums.WeightUnit;
import com.quantitymeasurement.model.Quantity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementTest {

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

}