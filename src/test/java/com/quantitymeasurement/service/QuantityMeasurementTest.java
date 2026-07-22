package com.quantitymeasurement.service;

import com.quantitymeasurement.enums.LengthUnit;
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

}