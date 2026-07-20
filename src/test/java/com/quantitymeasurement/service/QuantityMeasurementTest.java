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
    void testCompare_CentimeterAndInch() {

        QuantityLength cm =
                new QuantityLength(1, LengthUnit.CENTIMETER);

        QuantityLength inch =
                new QuantityLength(0.393701, LengthUnit.INCH);

        assertTrue(quantityMeasurement.compare(cm, inch));
    }

    @Test
    void testCompare_DifferentValues() {

        QuantityLength yard =
                new QuantityLength(1, LengthUnit.YARD);

        QuantityLength feet =
                new QuantityLength(2, LengthUnit.FEET);

        assertFalse(quantityMeasurement.compare(yard, feet));
    }

}