package com.quantitymeasurement.model;

import com.quantitymeasurement.enums.LengthUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityLengthTest {


    @Test
    void testEquality_FeetToFeet_SameValue() {

        QuantityLength first =
                new QuantityLength(1, LengthUnit.FEET);

        QuantityLength second =
                new QuantityLength(1, LengthUnit.FEET);

        assertTrue(first.equals(second));
    }

    @Test
    void testEquality_InchToInch_SameValue() {

        QuantityLength first =
                new QuantityLength(5, LengthUnit.INCH);

        QuantityLength second =
                new QuantityLength(5, LengthUnit.INCH);

        assertTrue(first.equals(second));
    }

    @Test
    void testEquality_FeetToInch_EquivalentValue() {

        QuantityLength first =
                new QuantityLength(1, LengthUnit.FEET);

        QuantityLength second =
                new QuantityLength(12, LengthUnit.INCH);

        assertTrue(first.equals(second));
    }

    @Test
    void testEquality_InchToFeet_EquivalentValue() {

        QuantityLength first =
                new QuantityLength(12, LengthUnit.INCH);

        QuantityLength second =
                new QuantityLength(1, LengthUnit.FEET);

        assertTrue(first.equals(second));
    }

    @Test
    void testEquality_FeetToFeet_DifferentValue() {

        QuantityLength first =
                new QuantityLength(1, LengthUnit.FEET);

        QuantityLength second =
                new QuantityLength(2, LengthUnit.FEET);

        assertFalse(first.equals(second));
    }

    @Test
    void testEquality_InchToInch_DifferentValue() {

        QuantityLength first =
                new QuantityLength(5, LengthUnit.INCH);

        QuantityLength second =
                new QuantityLength(8, LengthUnit.INCH);

        assertFalse(first.equals(second));
    }

    @Test
    void testEquality_SameReference() {

        QuantityLength first =
                new QuantityLength(1, LengthUnit.FEET);

        assertTrue(first.equals(first));
    }

    @Test
    void testEquality_NullComparison() {

        QuantityLength first =
                new QuantityLength(1, LengthUnit.FEET);

        assertFalse(first.equals(null));
    }

    @Test
    void testEquality_DifferentType() {

        QuantityLength first =
                new QuantityLength(1, LengthUnit.FEET);

        assertFalse(first.equals("BridgeLabz"));
    }

    @Test
    void testEquality_NullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new QuantityLength(1, null)
        );
    }
}