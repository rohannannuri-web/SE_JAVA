package com.selab;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Calculator class.
 */
public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void testAdd() {
        assertEquals(8, calculator.add(5, 3));
        assertEquals(0, calculator.add(0, 0));
        assertEquals(-2, calculator.add(-5, 3));
    }

    @Test
    public void testSubtract() {
        assertEquals(6, calculator.subtract(10, 4));
        assertEquals(0, calculator.subtract(5, 5));
        assertEquals(-3, calculator.subtract(2, 5));
    }

    @Test
    public void testMultiply() {
        assertEquals(42, calculator.multiply(6, 7));
        assertEquals(0, calculator.multiply(5, 0));
        assertEquals(-15, calculator.multiply(-3, 5));
    }

    @Test
    public void testDivide() {
        assertEquals(5.0, calculator.divide(20, 4));
        assertEquals(2.5, calculator.divide(5, 2));
    }

    @Test
    public void testDivideByZero() {
        assertThrows(IllegalArgumentException.class, () -> calculator.divide(10, 0));
    }
}
