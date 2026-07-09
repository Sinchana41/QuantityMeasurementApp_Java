package com.quantitymeasurement.service;

import com.quantitymeasurement.model.Feet;
import com.quantitymeasurement.model.Inches;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementTest {

    QuantityMeasurement quantityMeasurement = new QuantityMeasurement();

    @Test
    void testCompare_SameFeet_ShouldReturnTrue() {

        Feet first = new Feet(1.0);
        Feet second = new Feet(1.0);

        assertTrue(quantityMeasurement.compare(first, second));
    }

    @Test
    void testCompare_DifferentFeet_ShouldReturnFalse() {

        Feet first = new Feet(1.0);
        Feet second = new Feet(2.0);

        assertFalse(quantityMeasurement.compare(first, second));
    }


    @Test
    void testCompareInches_SameValue_ShouldReturnTrue() {

        Inches first = new Inches(12.0);
        Inches second = new Inches(12.0);

        assertTrue(quantityMeasurement.compare(first, second));
    }

    @Test
    void testCompareInches_DifferentValue_ShouldReturnFalse() {

        Inches first = new Inches(12.0);
        Inches second = new Inches(15.0);

        assertFalse(quantityMeasurement.compare(first, second));
    }


}