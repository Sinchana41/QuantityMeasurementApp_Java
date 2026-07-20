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


    @Test
    void testEquality_YardToYard_SameValue() {

        QuantityLength first =
                new QuantityLength(1, LengthUnit.YARD);

        QuantityLength second =
                new QuantityLength(1, LengthUnit.YARD);

        assertTrue(first.equals(second));
    }

    @Test
    void testEquality_YardToYard_DifferentValue() {

        QuantityLength first =
                new QuantityLength(1, LengthUnit.YARD);

        QuantityLength second =
                new QuantityLength(2, LengthUnit.YARD);

        assertFalse(first.equals(second));
    }

    @Test
    void testEquality_YardToFeet_EquivalentValue() {

        QuantityLength first =
                new QuantityLength(1, LengthUnit.YARD);

        QuantityLength second =
                new QuantityLength(3, LengthUnit.FEET);

        assertTrue(first.equals(second));
    }

    @Test
    void testEquality_FeetToYard_EquivalentValue() {

        QuantityLength first =
                new QuantityLength(3, LengthUnit.FEET);

        QuantityLength second =
                new QuantityLength(1, LengthUnit.YARD);

        assertTrue(first.equals(second));
    }

    @Test
    void testEquality_YardToInches_EquivalentValue() {

        QuantityLength first =
                new QuantityLength(1, LengthUnit.YARD);

        QuantityLength second =
                new QuantityLength(36, LengthUnit.INCH);

        assertTrue(first.equals(second));
    }

    @Test
    void testEquality_InchesToYard_EquivalentValue() {

        QuantityLength first =
                new QuantityLength(36, LengthUnit.INCH);

        QuantityLength second =
                new QuantityLength(1, LengthUnit.YARD);

        assertTrue(first.equals(second));
    }

    @Test
    void testEquality_CentimeterToCentimeter() {

        QuantityLength first =
                new QuantityLength(2, LengthUnit.CENTIMETER);

        QuantityLength second =
                new QuantityLength(2, LengthUnit.CENTIMETER);

        assertTrue(first.equals(second));
    }

    @Test
    void testEquality_CentimeterToInch() {

        QuantityLength first =
                new QuantityLength(1, LengthUnit.CENTIMETER);

        QuantityLength second =
                new QuantityLength(0.393701, LengthUnit.INCH);

        assertTrue(first.equals(second));
    }

    @Test
    void testEquality_CentimeterToFeet_NonEquivalent() {

        QuantityLength first =
                new QuantityLength(1, LengthUnit.CENTIMETER);

        QuantityLength second =
                new QuantityLength(1, LengthUnit.FEET);

        assertFalse(first.equals(second));
    }

    @Test
    void testEquality_MultiUnit_TransitiveProperty() {

        QuantityLength yard =
                new QuantityLength(1, LengthUnit.YARD);

        QuantityLength feet =
                new QuantityLength(3, LengthUnit.FEET);

        QuantityLength inch =
                new QuantityLength(36, LengthUnit.INCH);

        assertTrue(yard.equals(feet));

        assertTrue(feet.equals(inch));

        assertTrue(yard.equals(inch));
    }

}