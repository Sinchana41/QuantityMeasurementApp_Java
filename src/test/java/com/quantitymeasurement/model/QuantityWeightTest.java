package com.quantitymeasurement.model;

import com.quantitymeasurement.enums.WeightUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityWeightTest {

    private static final double EPSILON = 0.000001;

    @Test
    void testEquality_KilogramToKilogram_SameValue() {
        QuantityWeight weight1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight weight2 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertEquals(weight1, weight2);
    }

    @Test
    void testEquality_KilogramToKilogram_DifferentValue() {
        QuantityWeight weight1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight weight2 = new QuantityWeight(2.0, WeightUnit.KILOGRAM);

        assertNotEquals(weight1, weight2);
    }

    @Test
    void testEquality_KilogramToGram() {
        QuantityWeight kilogram = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight gram = new QuantityWeight(1000.0, WeightUnit.GRAM);

        assertEquals(kilogram, gram);
    }

    @Test
    void testEquality_GramToKilogram() {
        QuantityWeight gram = new QuantityWeight(1000.0, WeightUnit.GRAM);
        QuantityWeight kilogram = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertEquals(gram, kilogram);
    }

    @Test
    void testEquality_KilogramToPound() {
        QuantityWeight kilogram = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight pound = new QuantityWeight(2.2046244201837775, WeightUnit.POUND);

        assertEquals(kilogram, pound);
    }

    @Test
    void testEquality_PoundToGram() {
        QuantityWeight pound = new QuantityWeight(1.0, WeightUnit.POUND);
        QuantityWeight gram = new QuantityWeight(453.592, WeightUnit.GRAM);

        assertEquals(pound, gram);
    }

    @Test
    void testEquality_SameReference() {
        QuantityWeight weight = new QuantityWeight(10, WeightUnit.KILOGRAM);

        assertEquals(weight, weight);
    }

    @Test
    void testEquality_NullComparison() {
        QuantityWeight weight = new QuantityWeight(10, WeightUnit.KILOGRAM);

        assertNotEquals(weight, null);
    }

    @Test
    void testEquality_ZeroValue() {
        QuantityWeight kilogram = new QuantityWeight(0.0, WeightUnit.KILOGRAM);
        QuantityWeight gram = new QuantityWeight(0.0, WeightUnit.GRAM);

        assertEquals(kilogram, gram);
    }

    @Test
    void testEquality_NegativeWeight() {
        QuantityWeight kilogram = new QuantityWeight(-1.0, WeightUnit.KILOGRAM);
        QuantityWeight gram = new QuantityWeight(-1000.0, WeightUnit.GRAM);

        assertEquals(kilogram, gram);
    }

    @Test
    void testEquality_LargeWeightValue() {
        QuantityWeight kilogram = new QuantityWeight(1000.0, WeightUnit.KILOGRAM);
        QuantityWeight gram = new QuantityWeight(1000000.0, WeightUnit.GRAM);

        assertEquals(kilogram, gram);
    }

    @Test
    void testEquality_SmallWeightValue() {
        QuantityWeight kilogram = new QuantityWeight(0.001, WeightUnit.KILOGRAM);
        QuantityWeight gram = new QuantityWeight(1.0, WeightUnit.GRAM);

        assertEquals(kilogram, gram);
    }

    @Test
    void testConversion_KilogramToGram() {
        QuantityWeight kilogram = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        QuantityWeight result = kilogram.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    void testConversion_GramToKilogram() {
        QuantityWeight gram = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result = gram.convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    void testConversion_PoundToKilogram() {
        QuantityWeight pound = new QuantityWeight(2.20462, WeightUnit.POUND);

        QuantityWeight result = pound.convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), 0.001);
    }

    @Test
    void testConversion_KilogramToPound() {
        QuantityWeight kilogram = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        QuantityWeight result = kilogram.convertTo(WeightUnit.POUND);

        assertEquals(2.20462, result.getValue(), 0.001);
    }

    @Test
    void testConversion_SameUnit() {
        QuantityWeight kilogram = new QuantityWeight(5.0, WeightUnit.KILOGRAM);

        QuantityWeight result = kilogram.convertTo(WeightUnit.KILOGRAM);

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_ZeroValue() {
        QuantityWeight kilogram = new QuantityWeight(0.0, WeightUnit.KILOGRAM);

        QuantityWeight result = kilogram.convertTo(WeightUnit.GRAM);

        assertEquals(0.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_NegativeValue() {
        QuantityWeight kilogram = new QuantityWeight(-1.0, WeightUnit.KILOGRAM);

        QuantityWeight result = kilogram.convertTo(WeightUnit.GRAM);

        assertEquals(-1000.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_RoundTrip() {
        QuantityWeight original = new QuantityWeight(1.5, WeightUnit.KILOGRAM);

        QuantityWeight converted = original
                .convertTo(WeightUnit.GRAM)
                .convertTo(WeightUnit.KILOGRAM);

        assertEquals(original.getValue(), converted.getValue(), EPSILON);
    }

    @Test
    void testAddition_SameUnit() {
        QuantityWeight first = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight second = new QuantityWeight(2.0, WeightUnit.KILOGRAM);

        QuantityWeight result = first.add(second);

        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    void testAddition_CrossUnit() {
        QuantityWeight kilogram = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight gram = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result = kilogram.add(gram);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_PoundPlusKilogram() {
        QuantityWeight pound = new QuantityWeight(2.20462, WeightUnit.POUND);
        QuantityWeight kilogram = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        QuantityWeight result = pound.add(kilogram);

        assertEquals(4.40924, result.getValue(), 0.01);
    }

    @Test
    void testAddition_TargetUnit() {
        QuantityWeight kilogram = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight gram = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result = kilogram.add(gram, WeightUnit.GRAM);

        assertEquals(2000.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    void testAddition_Commutative() {
        QuantityWeight first = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight second = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result1 = first.add(second, WeightUnit.KILOGRAM);
        QuantityWeight result2 = second.add(first, WeightUnit.KILOGRAM);

        assertEquals(result1, result2);
    }

    @Test
    void testAddition_WithZero() {
        QuantityWeight first = new QuantityWeight(5.0, WeightUnit.KILOGRAM);
        QuantityWeight second = new QuantityWeight(0.0, WeightUnit.GRAM);

        QuantityWeight result = first.add(second);

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_NegativeValue() {
        QuantityWeight first = new QuantityWeight(5.0, WeightUnit.KILOGRAM);
        QuantityWeight second = new QuantityWeight(-2000.0, WeightUnit.GRAM);

        QuantityWeight result = first.add(second);

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_LargeValues() {
        QuantityWeight first = new QuantityWeight(1_000_000, WeightUnit.KILOGRAM);
        QuantityWeight second = new QuantityWeight(1_000_000, WeightUnit.KILOGRAM);

        QuantityWeight result = first.add(second);

        assertEquals(2_000_000, result.getValue(), EPSILON);
    }

    @Test
    void testConstructor_NullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityWeight(1.0, null));
    }

    @Test
    void testConstructor_NaNValue() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityWeight(Double.NaN, WeightUnit.KILOGRAM));
    }

    @Test
    void testConstructor_InfiniteValue() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityWeight(Double.POSITIVE_INFINITY, WeightUnit.KILOGRAM));
    }

    @Test
    void testConvert_NullTargetUnit() {
        QuantityWeight weight = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class,
                () -> weight.convertTo(null));
    }

    @Test
    void testAdd_NullQuantity() {
        QuantityWeight weight = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class,
                () -> weight.add(null));
    }


}