package com.quantitymeasurement.service;

import com.quantitymeasurement.enums.LengthUnit;
import com.quantitymeasurement.model.QuantityLength;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementTest {

    QuantityMeasurement quantityMeasurement = new QuantityMeasurement();

    @Test
    void testCompare_FeetAndInch_ShouldReturnTrue() {

        QuantityLength first =
                new QuantityLength(1, LengthUnit.FEET);

        QuantityLength second =
                new QuantityLength(12, LengthUnit.INCH);

        assertTrue(quantityMeasurement.compare(first, second));
    }

    @Test
    void testCompare_DifferentValues_ShouldReturnFalse() {

        QuantityLength first =
                new QuantityLength(1, LengthUnit.FEET);

        QuantityLength second =
                new QuantityLength(2, LengthUnit.FEET);

        assertFalse(quantityMeasurement.compare(first, second));
    }

    @Test
    void testCompare_YardAndFeet() {

        QuantityLength yard =
                new QuantityLength(1, LengthUnit.YARD);

        QuantityLength feet =
                new QuantityLength(3, LengthUnit.FEET);

        assertTrue(quantityMeasurement.compare(yard, feet));
    }

    @Test
    void testCompare_YardAndInch() {

        QuantityLength yard =
                new QuantityLength(1, LengthUnit.YARD);

        QuantityLength inch =
                new QuantityLength(36, LengthUnit.INCH);

        assertTrue(quantityMeasurement.compare(yard, inch));
    }



    @Test
    void testCompare_FeetAndInches() {

        QuantityLength first =
                new QuantityLength(
                        1,
                        LengthUnit.FEET);

        QuantityLength second =
                new QuantityLength(
                        12,
                        LengthUnit.INCH);

        assertTrue(quantityMeasurement.compare(first, second));
    }

    @Test
    void testCompare_CentimeterAndInch() {

        QuantityLength first =
                new QuantityLength(
                        1,
                        LengthUnit.CENTIMETER);

        QuantityLength second =
                new QuantityLength(
                        0.393701,
                        LengthUnit.INCH);

        assertTrue(quantityMeasurement.compare(first, second));
    }

    @Test
    void testCompare_DifferentValues() {

        QuantityLength first =
                new QuantityLength(
                        2,
                        LengthUnit.FEET);

        QuantityLength second =
                new QuantityLength(
                        12,
                        LengthUnit.INCH);

        assertFalse(quantityMeasurement.compare(first, second));
    }

    @Test
    void testConvert_ObjectToTargetUnit() {

        QuantityLength first =
                new QuantityLength(
                        2,
                        LengthUnit.YARD);

        QuantityLength converted =
                quantityMeasurement.convert(
                        first,
                        LengthUnit.INCH);

        assertEquals(
                72.0,
                converted.getValue(),
                0.000001);

        assertEquals(
                LengthUnit.INCH,
                converted.getUnit());
    }

    @Test
    void testCompare_NullObject() {

        QuantityLength first =
                new QuantityLength(
                        1,
                        LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> quantityMeasurement.compare(
                        first,
                        null));
    }

    @Test
    void testConvert_NullObject() {

        assertThrows(
                IllegalArgumentException.class,
                () -> quantityMeasurement.convert(
                        null,
                        LengthUnit.FEET));
    }

    @Test
    void testAdd_SameUnit() {

        QuantityLength result =
                quantityMeasurement.add(
                        new QuantityLength(1, LengthUnit.FEET),
                        new QuantityLength(2, LengthUnit.FEET));

        assertEquals(3.0, result.getValue(), 0.000001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAdd_CrossUnit() {

        QuantityLength result =
                quantityMeasurement.add(
                        new QuantityLength(1, LengthUnit.FEET),
                        new QuantityLength(12, LengthUnit.INCH));

        assertEquals(2.0, result.getValue(), 0.000001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAdd_YardAndFeet() {

        QuantityLength result =
                quantityMeasurement.add(
                        new QuantityLength(1, LengthUnit.YARD),
                        new QuantityLength(3, LengthUnit.FEET));

        assertEquals(2.0, result.getValue(), 0.000001);
        assertEquals(LengthUnit.YARD, result.getUnit());
    }

    @Test
    void testAdd_CentimeterAndInch() {

        QuantityLength result =
                quantityMeasurement.add(
                        new QuantityLength(2.54, LengthUnit.CENTIMETER),
                        new QuantityLength(1, LengthUnit.INCH));

        assertEquals(5.08, result.getValue(), 0.00001);
        assertEquals(LengthUnit.CENTIMETER, result.getUnit());
    }

    @Test
    void testAdd_WithZero() {

        QuantityLength result =
                quantityMeasurement.add(
                        new QuantityLength(5, LengthUnit.FEET),
                        new QuantityLength(0, LengthUnit.INCH));

        assertEquals(5.0, result.getValue(), 0.000001);
    }

    @Test
    void testAdd_NegativeValues() {

        QuantityLength result =
                quantityMeasurement.add(
                        new QuantityLength(5, LengthUnit.FEET),
                        new QuantityLength(-2, LengthUnit.FEET));

        assertEquals(3.0, result.getValue(), 0.000001);
    }

    @Test
    void testAdd_NullFirstOperand() {

        assertThrows(
                IllegalArgumentException.class,
                () -> quantityMeasurement.add(
                        null,
                        new QuantityLength(1, LengthUnit.FEET)));
    }

    @Test
    void testAdd_NullSecondOperand() {

        assertThrows(
                IllegalArgumentException.class,
                () -> quantityMeasurement.add(
                        new QuantityLength(1, LengthUnit.FEET),
                        null));
    }
}