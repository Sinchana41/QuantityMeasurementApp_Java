package com.quantitymeasurement.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InchesTest {

    @Test
    void testEquality_SameValue() {

        Inches first = new Inches(10.0);
        Inches second = new Inches(10.0);

        assertTrue(first.equals(second));
    }

    @Test
    void testEquality_DifferentValue() {

        Inches first = new Inches(10.0);
        Inches second = new Inches(20.0);

        assertFalse(first.equals(second));
    }

    @Test
    void testEquality_NullComparison() {

        Inches first = new Inches(10.0);

        assertFalse(first.equals(null));
    }

    @Test
    void testEquality_SameReference() {

        Inches first = new Inches(10.0);

        assertTrue(first.equals(first));
    }

    @Test
    void testEquality_DifferentType() {

        Inches first = new Inches(10.0);

        assertFalse(first.equals(100));
    }

}