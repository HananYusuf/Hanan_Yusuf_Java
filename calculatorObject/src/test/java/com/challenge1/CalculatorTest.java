package com.challenge1;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    Calculator calculator;
    @Before
    public void setUp()  {
        calculator = new Calculator();
    }

    @Test
    public void shouldAddTwoPositiveIntegers(){
        int expectedOutput = 2;
        int actualOutput = calculator.add(1, 1);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void shouldSubtractTwoPositiveIntegers(){
        int expectedOutput = -29;
        int actualOutput = calculator.subtract(23, 52);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void shouldMultiplyTwoPositiveIntegers(){
        int expectedOutput = 68;
        int actualOutput = calculator.multiply(34, 2);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void shouldDivideTwoPositiveIntegers(){
        int expectedOutput = 4;
        int actualOutput = calculator.divide(12, 3);
        assertEquals(expectedOutput, actualOutput);

    }

    @Test
    public void shouldAddTwoPositiveDoubles(){
        double expectedOutput = 5.0;
        double actualOutput = calculator.add(3.0, 2.0);
        assertEquals(expectedOutput, actualOutput,0.0001);
    }

    @Test
    public void shouldMultiplyTwoPositiveDoubles(){
        double expectedOutput = 15.41;
        double actualOutput = calculator.multiply(6.7, 2.3);
        assertEquals(expectedOutput, actualOutput, 0.00001);
    }

    @Test
    public void shouldSubtractTwoPositiveDoubles(){
        double expectedOutput = 5.0;
        double actualOutput = calculator.subtract(5.5, 0.5);
        assertEquals(expectedOutput, actualOutput, 0.0001);
    }

    @Test
    public void shouldDivideTwoPositiveDoubles(){
        double expectedOutput = 4.9;
        double actualOutput = calculator.divide(10.8, 2.2);
        assertEquals(expectedOutput, actualOutput, 0.01);
    }

}