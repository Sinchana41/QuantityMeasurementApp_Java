package com.quantitymeasurement.model;

import com.quantitymeasurement.enums.LengthUnit;
import com.quantitymeasurement.enums.WeightUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityTest {

    @Test
    void testLengthEquality() {

        Quantity<LengthUnit> feet = new Quantity<>(1, LengthUnit.FEET);

        Quantity<LengthUnit> inch = new Quantity<>(12, LengthUnit.INCH);

        assertEquals(feet, inch);
    }

    @Test
    void testWeightEquality() {

        Quantity<WeightUnit> kilogram = new Quantity<>(1, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> gram = new Quantity<>(1000, WeightUnit.GRAM);

        assertEquals(kilogram, gram);
    }

    @Test
    void testCrossCategoryComparison() {

        Quantity<LengthUnit> feet = new Quantity<>(1, LengthUnit.FEET);

        Quantity<WeightUnit> kilogram = new Quantity<>(1, WeightUnit.KILOGRAM);

        assertNotEquals(feet, kilogram);
    }

    @Test
    void testLengthConversion() {

        Quantity<LengthUnit> feet = new Quantity<>(1, LengthUnit.FEET);

        Quantity<LengthUnit> result = feet.convertTo(LengthUnit.INCH);

        assertEquals(12.0, result.getValue(), 0.000001);
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    @Test
    void testWeightConversion() {

        Quantity<WeightUnit> kilogram = new Quantity<>(1, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result = kilogram.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), 0.000001);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    void testLengthAddition() {

        Quantity<LengthUnit> result = new Quantity<>(1, LengthUnit.FEET).add(new Quantity<>(12, LengthUnit.INCH));

        assertEquals(2.0, result.getValue(), 0.000001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testWeightAddition() {

        Quantity<WeightUnit> result = new Quantity<>(1, WeightUnit.KILOGRAM).add(new Quantity<>(1000, WeightUnit.GRAM));

        assertEquals(2.0, result.getValue(), 0.000001);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    void testLengthAddition_TargetUnit() {

        Quantity<LengthUnit> result = new Quantity<>(1, LengthUnit.FEET).add(new Quantity<>(12, LengthUnit.INCH), LengthUnit.INCH);

        assertEquals(24.0, result.getValue(), 0.000001);
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    @Test
    void testWeightAddition_TargetUnit() {

        Quantity<WeightUnit> result = new Quantity<>(1, WeightUnit.KILOGRAM).add(new Quantity<>(1000, WeightUnit.GRAM), WeightUnit.GRAM);

        assertEquals(2000.0, result.getValue(), 0.000001);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    void testNullUnit() {

        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(1, null));
    }

    @Test
    void testNaNValue() {

        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }

    @Test
    void testInfiniteValue() {

        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(Double.POSITIVE_INFINITY, WeightUnit.KILOGRAM));
    }

    @Test
    void testConvertToNullUnit() {

        Quantity<LengthUnit> feet = new Quantity<>(1, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> feet.convertTo(null));
    }

    @Test
    void testAddNullQuantity() {

        Quantity<WeightUnit> kilogram = new Quantity<>(1, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class, () -> kilogram.add(null));
    }

    @Test
    void testAddNullTargetUnit() {

        Quantity<WeightUnit> kilogram = new Quantity<>(1, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> gram = new Quantity<>(1000, WeightUnit.GRAM);

        assertThrows(IllegalArgumentException.class, () -> kilogram.add(gram, null));
    }

}