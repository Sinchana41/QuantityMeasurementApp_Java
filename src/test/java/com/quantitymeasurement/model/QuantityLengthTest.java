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

    @Test
    void testConversion_FeetToInches() {

        double result = QuantityLength.convert(
                1.0,
                LengthUnit.FEET,
                LengthUnit.INCH);

        assertEquals(12.0, result);
    }

    @Test
    void testConversion_InchesToFeet() {

        double result = QuantityLength.convert(
                24.0,
                LengthUnit.INCH,
                LengthUnit.FEET);

        assertEquals(2.0, result);
    }

    @Test
    void testConversion_YardsToInches() {

        double result = QuantityLength.convert(
                1.0,
                LengthUnit.YARD,
                LengthUnit.INCH);

        assertEquals(36.0, result);
    }

    @Test
    void testConversion_InchesToYards() {

        double result = QuantityLength.convert(
                72.0,
                LengthUnit.INCH,
                LengthUnit.YARD);

        assertEquals(2.0, result);
    }

    @Test
    void testConversion_CentimetersToInches() {

        double result = QuantityLength.convert(
                2.54,
                LengthUnit.CENTIMETER,
                LengthUnit.INCH);

        assertEquals(1.0, result, 0.000001);
    }

    @Test
    void testConversion_FeetToYard() {

        double result = QuantityLength.convert(
                6.0,
                LengthUnit.FEET,
                LengthUnit.YARD);

        assertEquals(2.0, result);
    }

    @Test
    void testConversion_RoundTrip_PreservesValue() {

        double value = 5.5;

        double inches = QuantityLength.convert(
                value,
                LengthUnit.FEET,
                LengthUnit.INCH);

        double feet = QuantityLength.convert(
                inches,
                LengthUnit.INCH,
                LengthUnit.FEET);

        assertEquals(value, feet, 0.000001);
    }

    @Test
    void testConversion_ZeroValue() {

        double result = QuantityLength.convert(
                0.0,
                LengthUnit.FEET,
                LengthUnit.INCH);

        assertEquals(0.0, result);
    }

    @Test
    void testConversion_NegativeValue() {

        double result = QuantityLength.convert(
                -1.0,
                LengthUnit.FEET,
                LengthUnit.INCH);

        assertEquals(-12.0, result);
    }

    @Test
    void testConversion_InvalidUnit_Throws() {

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityLength.convert(
                        1.0,
                        null,
                        LengthUnit.FEET));
    }

    @Test
    void testConversion_InvalidTargetUnit_Throws() {

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityLength.convert(
                        1.0,
                        LengthUnit.FEET,
                        null));
    }

    @Test
    void testConversion_NaNValue_Throws() {

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityLength.convert(
                        Double.NaN,
                        LengthUnit.FEET,
                        LengthUnit.INCH));
    }

    @Test
    void testConversion_InfiniteValue_Throws() {

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityLength.convert(
                        Double.POSITIVE_INFINITY,
                        LengthUnit.FEET,
                        LengthUnit.INCH));
    }

    @Test
    void testConversion_SameUnit() {

        double result = QuantityLength.convert(
                10.0,
                LengthUnit.FEET,
                LengthUnit.FEET);

        assertEquals(10.0, result);
    }

    @Test
    void testConversion_PrecisionTolerance() {

        double result = QuantityLength.convert(
                1.0,
                LengthUnit.CENTIMETER,
                LengthUnit.INCH);

        assertEquals(0.393701, result, 0.000001);
    }
}