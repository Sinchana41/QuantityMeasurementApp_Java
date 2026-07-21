package com.quantitymeasurement.service;

import com.quantitymeasurement.enums.LengthUnit;
import com.quantitymeasurement.model.QuantityLength;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementTest {

    QuantityMeasurement quantityMeasurement = new QuantityMeasurement();

    @Test
    void testCompare_FeetAndInch_ShouldReturnTrue() {

        QuantityLength first = new QuantityLength(1, LengthUnit.FEET);

        QuantityLength second = new QuantityLength(12, LengthUnit.INCH);

        assertTrue(quantityMeasurement.compare(first, second));
    }

    @Test
    void testCompare_DifferentValues_ShouldReturnFalse() {

        QuantityLength first = new QuantityLength(1, LengthUnit.FEET);

        QuantityLength second = new QuantityLength(2, LengthUnit.FEET);

        assertFalse(quantityMeasurement.compare(first, second));
    }

    @Test
    void testCompare_YardAndFeet() {

        QuantityLength yard = new QuantityLength(1, LengthUnit.YARD);

        QuantityLength feet = new QuantityLength(3, LengthUnit.FEET);

        assertTrue(quantityMeasurement.compare(yard, feet));
    }

    @Test
    void testCompare_YardAndInch() {

        QuantityLength yard = new QuantityLength(1, LengthUnit.YARD);

        QuantityLength inch = new QuantityLength(36, LengthUnit.INCH);

        assertTrue(quantityMeasurement.compare(yard, inch));
    }


    @Test
    void testCompare_FeetAndInches() {

        QuantityLength first = new QuantityLength(1, LengthUnit.FEET);

        QuantityLength second = new QuantityLength(12, LengthUnit.INCH);

        assertTrue(quantityMeasurement.compare(first, second));
    }

    @Test
    void testCompare_CentimeterAndInch() {

        QuantityLength first = new QuantityLength(1, LengthUnit.CENTIMETER);

        QuantityLength second = new QuantityLength(0.393701, LengthUnit.INCH);

        assertTrue(quantityMeasurement.compare(first, second));
    }

    @Test
    void testCompare_DifferentValues() {

        QuantityLength first = new QuantityLength(2, LengthUnit.FEET);

        QuantityLength second = new QuantityLength(12, LengthUnit.INCH);

        assertFalse(quantityMeasurement.compare(first, second));
    }

    @Test
    void testConvert_ObjectToTargetUnit() {

        QuantityLength first = new QuantityLength(2, LengthUnit.YARD);

        QuantityLength converted = quantityMeasurement.convert(first, LengthUnit.INCH);

        assertEquals(72.0, converted.getValue(), 0.000001);

        assertEquals(LengthUnit.INCH, converted.getUnit());
    }

    @Test
    void testCompare_NullObject() {

        QuantityLength first = new QuantityLength(1, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> quantityMeasurement.compare(first, null));
    }

    @Test
    void testConvert_NullObject() {

        assertThrows(IllegalArgumentException.class, () -> quantityMeasurement.convert(null, LengthUnit.FEET));
    }

    @Test
    void testAdd_SameUnit() {

        QuantityLength result = quantityMeasurement.add(new QuantityLength(1, LengthUnit.FEET), new QuantityLength(2, LengthUnit.FEET));

        assertEquals(3.0, result.getValue(), 0.000001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAdd_CrossUnit() {

        QuantityLength result = quantityMeasurement.add(new QuantityLength(1, LengthUnit.FEET), new QuantityLength(12, LengthUnit.INCH));

        assertEquals(2.0, result.getValue(), 0.000001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAdd_YardAndFeet() {

        QuantityLength result = quantityMeasurement.add(new QuantityLength(1, LengthUnit.YARD), new QuantityLength(3, LengthUnit.FEET));

        assertEquals(2.0, result.getValue(), 0.000001);
        assertEquals(LengthUnit.YARD, result.getUnit());
    }

    @Test
    void testAdd_CentimeterAndInch() {

        QuantityLength result = quantityMeasurement.add(new QuantityLength(2.54, LengthUnit.CENTIMETER), new QuantityLength(1, LengthUnit.INCH));

        assertEquals(5.08, result.getValue(), 0.00001);
        assertEquals(LengthUnit.CENTIMETER, result.getUnit());
    }

    @Test
    void testAdd_WithZero() {

        QuantityLength result = quantityMeasurement.add(new QuantityLength(5, LengthUnit.FEET), new QuantityLength(0, LengthUnit.INCH));

        assertEquals(5.0, result.getValue(), 0.000001);
    }

    @Test
    void testAdd_NegativeValues() {

        QuantityLength result = quantityMeasurement.add(new QuantityLength(5, LengthUnit.FEET), new QuantityLength(-2, LengthUnit.FEET));

        assertEquals(3.0, result.getValue(), 0.000001);
    }

    @Test
    void testAdd_NullFirstOperand() {

        assertThrows(IllegalArgumentException.class, () -> quantityMeasurement.add(null, new QuantityLength(1, LengthUnit.FEET)));
    }

    @Test
    void testAdd_NullSecondOperand() {

        assertThrows(IllegalArgumentException.class, () -> quantityMeasurement.add(new QuantityLength(1, LengthUnit.FEET), null));
    }

    @Test
    void testAdd_ExplicitTargetUnit_Feet() {

        QuantityLength result = quantityMeasurement.add(new QuantityLength(1, LengthUnit.FEET), new QuantityLength(12, LengthUnit.INCH), LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), 0.000001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAdd_ExplicitTargetUnit_Inches() {

        QuantityLength result = quantityMeasurement.add(new QuantityLength(1, LengthUnit.FEET), new QuantityLength(12, LengthUnit.INCH), LengthUnit.INCH);

        assertEquals(24.0, result.getValue(), 0.000001);
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    @Test
    void testAdd_ExplicitTargetUnit_Yards() {

        QuantityLength result = quantityMeasurement.add(new QuantityLength(1, LengthUnit.FEET), new QuantityLength(12, LengthUnit.INCH), LengthUnit.YARD);

        assertEquals(0.666667, result.getValue(), 0.000001);
        assertEquals(LengthUnit.YARD, result.getUnit());
    }

    @Test
    void testAdd_ExplicitTargetUnit_Centimeters() {

        QuantityLength result = quantityMeasurement.add(new QuantityLength(1, LengthUnit.INCH), new QuantityLength(1, LengthUnit.INCH), LengthUnit.CENTIMETER);

        assertEquals(5.08, result.getValue(), 0.00001);
        assertEquals(LengthUnit.CENTIMETER, result.getUnit());
    }

    @Test
    void testAdd_TargetSameAsFirstOperand() {

        QuantityLength result = quantityMeasurement.add(new QuantityLength(2, LengthUnit.YARD), new QuantityLength(3, LengthUnit.FEET), LengthUnit.YARD);

        assertEquals(3.0, result.getValue(), 0.000001);
        assertEquals(LengthUnit.YARD, result.getUnit());
    }

    @Test
    void testAdd_TargetSameAsSecondOperand() {

        QuantityLength result = quantityMeasurement.add(new QuantityLength(2, LengthUnit.YARD), new QuantityLength(3, LengthUnit.FEET), LengthUnit.FEET);

        assertEquals(9.0, result.getValue(), 0.000001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAdd_Commutativity() {

        QuantityLength first = quantityMeasurement.add(new QuantityLength(1, LengthUnit.FEET), new QuantityLength(12, LengthUnit.INCH), LengthUnit.YARD);

        QuantityLength second = quantityMeasurement.add(new QuantityLength(12, LengthUnit.INCH), new QuantityLength(1, LengthUnit.FEET), LengthUnit.YARD);

        assertTrue(first.equals(second));
    }

    @Test
    void testAdd_NullTargetUnit() {

        assertThrows(IllegalArgumentException.class, () -> quantityMeasurement.add(new QuantityLength(1, LengthUnit.FEET), new QuantityLength(12, LengthUnit.INCH), null));
    }

    @Test
    void testAdd_LargeValues() {

        QuantityLength result = quantityMeasurement.add(new QuantityLength(1000, LengthUnit.FEET), new QuantityLength(500, LengthUnit.FEET), LengthUnit.INCH);

        assertEquals(18000.0, result.getValue(), 0.000001);
    }

    @Test
    void testAdd_SmallValues() {

        QuantityLength result = quantityMeasurement.add(new QuantityLength(12, LengthUnit.INCH), new QuantityLength(12, LengthUnit.INCH), LengthUnit.YARD);

        assertEquals(0.666667, result.getValue(), 0.000001);
    }

    @Test
    void testAdd_PrecisionTolerance() {

        QuantityLength result = quantityMeasurement.add(new QuantityLength(2.54, LengthUnit.CENTIMETER), new QuantityLength(1, LengthUnit.INCH), LengthUnit.CENTIMETER);

        assertEquals(5.08, result.getValue(), 0.00001);
    }

    @Test
    void testCompare_AfterAddition() {

        QuantityLength expected = new QuantityLength(2, LengthUnit.FEET);

        QuantityLength actual = quantityMeasurement.add(new QuantityLength(1, LengthUnit.FEET), new QuantityLength(12, LengthUnit.INCH), LengthUnit.FEET);

        assertTrue(quantityMeasurement.compare(expected, actual));
    }

    @Test
    void testConvert_AfterAddition() {

        QuantityLength result = quantityMeasurement.add(new QuantityLength(1, LengthUnit.FEET), new QuantityLength(12, LengthUnit.INCH), LengthUnit.FEET);

        QuantityLength converted = quantityMeasurement.convert(result, LengthUnit.INCH);

        assertEquals(24.0, converted.getValue(), 0.000001);
        assertEquals(LengthUnit.INCH, converted.getUnit());
    }
}