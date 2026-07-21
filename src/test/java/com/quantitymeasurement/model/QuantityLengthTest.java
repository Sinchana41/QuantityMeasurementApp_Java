package com.quantitymeasurement.model;

import com.quantitymeasurement.enums.LengthUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityLengthTest {


    @Test
    void testEquality_FeetToFeet_SameValue() {

        QuantityLength first = new QuantityLength(1, LengthUnit.FEET);

        QuantityLength second = new QuantityLength(1, LengthUnit.FEET);

        assertTrue(first.equals(second));
    }

    @Test
    void testEquality_InchToInch_SameValue() {

        QuantityLength first = new QuantityLength(5, LengthUnit.INCH);

        QuantityLength second = new QuantityLength(5, LengthUnit.INCH);

        assertTrue(first.equals(second));
    }

    @Test
    void testEquality_FeetToInch_EquivalentValue() {

        QuantityLength first = new QuantityLength(1, LengthUnit.FEET);

        QuantityLength second = new QuantityLength(12, LengthUnit.INCH);

        assertTrue(first.equals(second));
    }

    @Test
    void testEquality_InchToFeet_EquivalentValue() {

        QuantityLength first = new QuantityLength(12, LengthUnit.INCH);

        QuantityLength second = new QuantityLength(1, LengthUnit.FEET);

        assertTrue(first.equals(second));
    }

    @Test
    void testEquality_FeetToFeet_DifferentValue() {

        QuantityLength first = new QuantityLength(1, LengthUnit.FEET);

        QuantityLength second = new QuantityLength(2, LengthUnit.FEET);

        assertFalse(first.equals(second));
    }

    @Test
    void testEquality_InchToInch_DifferentValue() {

        QuantityLength first = new QuantityLength(5, LengthUnit.INCH);

        QuantityLength second = new QuantityLength(8, LengthUnit.INCH);

        assertFalse(first.equals(second));
    }

    @Test
    void testEquality_SameReference() {

        QuantityLength first = new QuantityLength(1, LengthUnit.FEET);

        assertTrue(first.equals(first));
    }

    @Test
    void testEquality_NullComparison() {

        QuantityLength first = new QuantityLength(1, LengthUnit.FEET);

        assertFalse(first.equals(null));
    }

    @Test
    void testEquality_DifferentType() {

        QuantityLength first = new QuantityLength(1, LengthUnit.FEET);

        assertFalse(first.equals("BridgeLabz"));
    }

    @Test
    void testEquality_NullUnit() {

        assertThrows(IllegalArgumentException.class, () -> new QuantityLength(1, null));
    }


    @Test
    void testEquality_YardToYard_SameValue() {

        QuantityLength first = new QuantityLength(1, LengthUnit.YARD);

        QuantityLength second = new QuantityLength(1, LengthUnit.YARD);

        assertTrue(first.equals(second));
    }

    @Test
    void testEquality_YardToYard_DifferentValue() {

        QuantityLength first = new QuantityLength(1, LengthUnit.YARD);

        QuantityLength second = new QuantityLength(2, LengthUnit.YARD);

        assertFalse(first.equals(second));
    }

    @Test
    void testEquality_YardToFeet_EquivalentValue() {

        QuantityLength first = new QuantityLength(1, LengthUnit.YARD);

        QuantityLength second = new QuantityLength(3, LengthUnit.FEET);

        assertTrue(first.equals(second));
    }

    @Test
    void testEquality_FeetToYard_EquivalentValue() {

        QuantityLength first = new QuantityLength(3, LengthUnit.FEET);

        QuantityLength second = new QuantityLength(1, LengthUnit.YARD);

        assertTrue(first.equals(second));
    }

    @Test
    void testEquality_YardToInches_EquivalentValue() {

        QuantityLength first = new QuantityLength(1, LengthUnit.YARD);

        QuantityLength second = new QuantityLength(36, LengthUnit.INCH);

        assertTrue(first.equals(second));
    }

    @Test
    void testEquality_InchesToYard_EquivalentValue() {

        QuantityLength first = new QuantityLength(36, LengthUnit.INCH);

        QuantityLength second = new QuantityLength(1, LengthUnit.YARD);

        assertTrue(first.equals(second));
    }

    @Test
    void testEquality_CentimeterToCentimeter() {

        QuantityLength first = new QuantityLength(2, LengthUnit.CENTIMETER);

        QuantityLength second = new QuantityLength(2, LengthUnit.CENTIMETER);

        assertTrue(first.equals(second));
    }

    @Test
    void testEquality_CentimeterToInch() {

        QuantityLength first = new QuantityLength(1, LengthUnit.CENTIMETER);

        QuantityLength second = new QuantityLength(0.393701, LengthUnit.INCH);

        assertTrue(first.equals(second));
    }

    @Test
    void testEquality_CentimeterToFeet_NonEquivalent() {

        QuantityLength first = new QuantityLength(1, LengthUnit.CENTIMETER);

        QuantityLength second = new QuantityLength(1, LengthUnit.FEET);

        assertFalse(first.equals(second));
    }

    @Test
    void testEquality_MultiUnit_TransitiveProperty() {

        QuantityLength yard = new QuantityLength(1, LengthUnit.YARD);

        QuantityLength feet = new QuantityLength(3, LengthUnit.FEET);

        QuantityLength inch = new QuantityLength(36, LengthUnit.INCH);

        assertTrue(yard.equals(feet));

        assertTrue(feet.equals(inch));

        assertTrue(yard.equals(inch));
    }

    @Test
    void testConversion_FeetToInches() {

        double result = QuantityLength.convert(1.0, LengthUnit.FEET, LengthUnit.INCH);

        assertEquals(12.0, result);
    }

    @Test
    void testConversion_InchesToFeet() {

        double result = QuantityLength.convert(24.0, LengthUnit.INCH, LengthUnit.FEET);

        assertEquals(2.0, result);
    }

    @Test
    void testConversion_YardsToInches() {

        double result = QuantityLength.convert(1.0, LengthUnit.YARD, LengthUnit.INCH);

        assertEquals(36.0, result);
    }

    @Test
    void testConversion_InchesToYards() {

        double result = QuantityLength.convert(72.0, LengthUnit.INCH, LengthUnit.YARD);

        assertEquals(2.0, result);
    }

    //delta-The maximum acceptable difference between the expected and actual values
    @Test
    void testConversion_CentimetersToInches() {

        double result = QuantityLength.convert(2.54, LengthUnit.CENTIMETER, LengthUnit.INCH);

        assertEquals(1.0, result, 0.000001);
    }

    @Test
    void testConversion_FeetToYard() {

        double result = QuantityLength.convert(6.0, LengthUnit.FEET, LengthUnit.YARD);

        assertEquals(2.0, result);
    }

    @Test
    void testConversion_RoundTrip_PreservesValue() {

        double value = 5.5;

        double inches = QuantityLength.convert(value, LengthUnit.FEET, LengthUnit.INCH);

        double feet = QuantityLength.convert(inches, LengthUnit.INCH, LengthUnit.FEET);

        assertEquals(value, feet, 0.000001);
    }

    @Test
    void testConversion_ZeroValue() {

        double result = QuantityLength.convert(0.0, LengthUnit.FEET, LengthUnit.INCH);

        assertEquals(0.0, result);
    }

    @Test
    void testConversion_NegativeValue() {

        double result = QuantityLength.convert(-1.0, LengthUnit.FEET, LengthUnit.INCH);

        assertEquals(-12.0, result);
    }

    @Test
    void testConversion_InvalidUnit_Throws() {

        assertThrows(IllegalArgumentException.class, () -> QuantityLength.convert(1.0, null, LengthUnit.FEET));
    }

    @Test
    void testConversion_InvalidTargetUnit_Throws() {

        assertThrows(IllegalArgumentException.class, () -> QuantityLength.convert(1.0, LengthUnit.FEET, null));
    }

    @Test
    void testConversion_NaNValue_Throws() {

        assertThrows(IllegalArgumentException.class, () -> QuantityLength.convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCH));
    }

    @Test
    void testConversion_InfiniteValue_Throws() {

        assertThrows(IllegalArgumentException.class, () -> QuantityLength.convert(Double.POSITIVE_INFINITY, LengthUnit.FEET, LengthUnit.INCH));
    }

    @Test
    void testConversion_SameUnit() {

        double result = QuantityLength.convert(10.0, LengthUnit.FEET, LengthUnit.FEET);

        assertEquals(10.0, result);
    }

    @Test
    void testConversion_PrecisionTolerance() {

        double result = QuantityLength.convert(1.0, LengthUnit.CENTIMETER, LengthUnit.INCH);

        assertEquals(0.393701, result, 0.000001);
    }

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {

        QuantityLength result = QuantityLength.add(new QuantityLength(1, LengthUnit.FEET), new QuantityLength(2, LengthUnit.FEET));

        assertEquals(3.0, result.getValue(), 0.000001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAddition_SameUnit_InchPlusInch() {

        QuantityLength result = QuantityLength.add(new QuantityLength(6, LengthUnit.INCH), new QuantityLength(6, LengthUnit.INCH));

        assertEquals(12.0, result.getValue(), 0.000001);
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {

        QuantityLength result = QuantityLength.add(new QuantityLength(1, LengthUnit.FEET), new QuantityLength(12, LengthUnit.INCH));

        assertEquals(2.0, result.getValue(), 0.000001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAddition_CrossUnit_InchPlusFeet() {

        QuantityLength result = QuantityLength.add(new QuantityLength(12, LengthUnit.INCH), new QuantityLength(1, LengthUnit.FEET));

        assertEquals(24.0, result.getValue(), 0.000001);
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    @Test
    void testAddition_CrossUnit_YardPlusFeet() {

        QuantityLength result = QuantityLength.add(new QuantityLength(1, LengthUnit.YARD), new QuantityLength(3, LengthUnit.FEET));

        assertEquals(2.0, result.getValue(), 0.000001);
        assertEquals(LengthUnit.YARD, result.getUnit());
    }

    @Test
    void testAddition_CrossUnit_CentimeterPlusInch() {

        QuantityLength result = QuantityLength.add(new QuantityLength(2.54, LengthUnit.CENTIMETER), new QuantityLength(1, LengthUnit.INCH));

        assertEquals(5.08, result.getValue(), 0.00001);
        assertEquals(LengthUnit.CENTIMETER, result.getUnit());
    }

    @Test
    void testAddition_WithZero() {

        QuantityLength result = QuantityLength.add(new QuantityLength(5, LengthUnit.FEET), new QuantityLength(0, LengthUnit.INCH));

        assertEquals(5.0, result.getValue(), 0.000001);
    }

    @Test
    void testAddition_NegativeValues() {

        QuantityLength result = QuantityLength.add(new QuantityLength(5, LengthUnit.FEET), new QuantityLength(-2, LengthUnit.FEET));

        assertEquals(3.0, result.getValue(), 0.000001);
    }

    @Test
    void testAddition_LargeValues() {

        QuantityLength result = QuantityLength.add(new QuantityLength(1000000, LengthUnit.FEET), new QuantityLength(1000000, LengthUnit.FEET));

        assertEquals(2000000, result.getValue(), 0.000001);
    }

    @Test
    void testAddition_SmallValues() {

        QuantityLength result = QuantityLength.add(new QuantityLength(0.001, LengthUnit.FEET), new QuantityLength(0.002, LengthUnit.FEET));

        assertEquals(0.003, result.getValue(), 0.000001);
    }

    @Test
    void testAddition_StaticMethod() {

        QuantityLength result = QuantityLength.add(1, LengthUnit.FEET, 12, LengthUnit.INCH);

        assertEquals(2.0, result.getValue(), 0.000001);
    }

    @Test
    void testAddition_NullFirstOperand() {

        assertThrows(IllegalArgumentException.class, () -> QuantityLength.add(null, new QuantityLength(1, LengthUnit.FEET)));
    }

    @Test
    void testAddition_ReturnsNewObject() {

        QuantityLength first = new QuantityLength(1, LengthUnit.FEET);

        QuantityLength second = new QuantityLength(2, LengthUnit.FEET);

        QuantityLength result = QuantityLength.add(first, second);

        assertNotSame(first, result);
        assertNotSame(second, result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Feet() {

        QuantityLength result = QuantityLength.add(new QuantityLength(1, LengthUnit.FEET), new QuantityLength(12, LengthUnit.INCH), LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), 0.000001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {

        QuantityLength result = QuantityLength.add(new QuantityLength(1, LengthUnit.FEET), new QuantityLength(12, LengthUnit.INCH), LengthUnit.INCH);

        assertEquals(24.0, result.getValue(), 0.000001);
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {

        QuantityLength result = QuantityLength.add(new QuantityLength(1, LengthUnit.FEET), new QuantityLength(12, LengthUnit.INCH), LengthUnit.YARD);

        assertEquals(0.666667, result.getValue(), 0.000001);
        assertEquals(LengthUnit.YARD, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Centimeters() {

        QuantityLength result = QuantityLength.add(new QuantityLength(1, LengthUnit.INCH), new QuantityLength(1, LengthUnit.INCH), LengthUnit.CENTIMETER);

        assertEquals(5.08, result.getValue(), 0.00001);
        assertEquals(LengthUnit.CENTIMETER, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {

        QuantityLength result = QuantityLength.add(new QuantityLength(2, LengthUnit.YARD), new QuantityLength(3, LengthUnit.FEET), LengthUnit.YARD);

        assertEquals(3.0, result.getValue(), 0.000001);
        assertEquals(LengthUnit.YARD, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {

        QuantityLength result = QuantityLength.add(new QuantityLength(2, LengthUnit.YARD), new QuantityLength(3, LengthUnit.FEET), LengthUnit.FEET);

        assertEquals(9.0, result.getValue(), 0.000001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {

        QuantityLength first = QuantityLength.add(new QuantityLength(1, LengthUnit.FEET), new QuantityLength(12, LengthUnit.INCH), LengthUnit.YARD);

        QuantityLength second = QuantityLength.add(new QuantityLength(12, LengthUnit.INCH), new QuantityLength(1, LengthUnit.FEET), LengthUnit.YARD);

        assertTrue(first.equals(second));
    }

    @Test
    void testAddition_ExplicitTargetUnit_WithZero() {

        QuantityLength result = QuantityLength.add(new QuantityLength(5, LengthUnit.FEET), new QuantityLength(0, LengthUnit.INCH), LengthUnit.YARD);

        assertEquals(1.666667, result.getValue(), 0.000001);
    }

    @Test
    void testAddition_ExplicitTargetUnit_NegativeValues() {

        QuantityLength result = QuantityLength.add(new QuantityLength(5, LengthUnit.FEET), new QuantityLength(-2, LengthUnit.FEET), LengthUnit.INCH);

        assertEquals(36.0, result.getValue(), 0.000001);
    }

    @Test
    void testAddition_ExplicitTargetUnit_NullTargetUnit() {

        assertThrows(IllegalArgumentException.class, () -> QuantityLength.add(new QuantityLength(1, LengthUnit.FEET), new QuantityLength(12, LengthUnit.INCH), null));
    }

    @Test
    void testAddition_ExplicitTargetUnit_NullFirstOperand() {

        assertThrows(IllegalArgumentException.class, () -> QuantityLength.add(null, new QuantityLength(12, LengthUnit.INCH), LengthUnit.FEET));
    }

    @Test
    void testAddition_ExplicitTargetUnit_NullSecondOperand() {

        assertThrows(IllegalArgumentException.class, () -> QuantityLength.add(new QuantityLength(1, LengthUnit.FEET), null, LengthUnit.FEET));
    }

    @Test
    void testAddition_ExplicitTargetUnit_LargeToSmallScale() {

        QuantityLength result = QuantityLength.add(new QuantityLength(1000, LengthUnit.FEET), new QuantityLength(500, LengthUnit.FEET), LengthUnit.INCH);

        assertEquals(18000.0, result.getValue(), 0.000001);
    }

    @Test
    void testAddition_ExplicitTargetUnit_SmallToLargeScale() {

        QuantityLength result = QuantityLength.add(new QuantityLength(12, LengthUnit.INCH), new QuantityLength(12, LengthUnit.INCH), LengthUnit.YARD);

        assertEquals(0.666667, result.getValue(), 0.000001);
    }

    @Test
    void testAddition_ExplicitTargetUnit_PrecisionTolerance() {

        QuantityLength result = QuantityLength.add(new QuantityLength(2.54, LengthUnit.CENTIMETER), new QuantityLength(1, LengthUnit.INCH), LengthUnit.CENTIMETER);

        assertEquals(5.08, result.getValue(), 0.00001);
    }

    @Test
    void testAddition_StaticOverloadedMethod() {

        QuantityLength result = QuantityLength.add(1, LengthUnit.FEET, 12, LengthUnit.INCH, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), 0.000001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAddition_AllUnitCombinations() {

        QuantityLength result1 = QuantityLength.add(new QuantityLength(1, LengthUnit.FEET), new QuantityLength(12, LengthUnit.INCH), LengthUnit.FEET);

        QuantityLength result2 = QuantityLength.add(new QuantityLength(1, LengthUnit.YARD), new QuantityLength(3, LengthUnit.FEET), LengthUnit.YARD);

        QuantityLength result3 = QuantityLength.add(new QuantityLength(2.54, LengthUnit.CENTIMETER), new QuantityLength(1, LengthUnit.INCH), LengthUnit.CENTIMETER);

        assertEquals(2.0, result1.getValue(), 0.000001);
        assertEquals(2.0, result2.getValue(), 0.000001);
        assertEquals(5.08, result3.getValue(), 0.00001);
    }
}