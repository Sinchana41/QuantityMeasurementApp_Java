package com.quantitymeasurement.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FeetTest {

    @Test
    void testEquality_SameValue() {

        Feet first = new Feet(1.0);
        Feet second = new Feet(1.0);

        assertTrue(first.equals(second));
    }

    @Test
    void testEquality_DifferentValue() {

        Feet first = new Feet(1.0);
        Feet second = new Feet(2.0);

        assertFalse(first.equals(second));
    }

    @Test
    void testEquality_NullComparison() {

        Feet first = new Feet(1.0);

        assertFalse(first.equals(null));
    }

    @Test
    void testEquality_SameReference() {

        Feet first = new Feet(1.0);

        assertTrue(first.equals(first));
    }

    @Test
    void testEquality_DifferentType() {

        Feet first = new Feet(1.0);

        assertFalse(first.equals("Hello"));
    }
}