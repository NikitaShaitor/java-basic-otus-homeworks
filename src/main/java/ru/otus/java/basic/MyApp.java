package ru.otus.java.basic;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MyApp {
    @Test
    public void testValidArray() {
        assertTrue(ArrayValidator.isValidArray(new int[]{1, 2}));
        assertTrue(ArrayValidator.isValidArray(new int[]{1, 2, 2, 1}));
    }

    @Test
    public void testInvalidArrayOnlyOnes() {
        assertFalse(ArrayValidator.isValidArray(new int[]{1, 1}));
    }

    @Test
    public void testInvalidArrayOtherNumbers() {
        assertFalse(ArrayValidator.isValidArray(new int[]{1, 3}));
    }

    @Test
    public void testEmptyArray() {
        assertFalse(ArrayValidator.isValidArray(new int[]{}));
    }

    @Test
    public void testNullArray() {
        assertFalse(ArrayValidator.isValidArray(null));
    }
}
