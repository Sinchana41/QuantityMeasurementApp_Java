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

}