package com.company;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConverterSwitchTest {

    Converter conv;

    @Before
    public void setUp() {
        conv = new ConverterSwitch();
    }

    @Test
    public void shouldConvertMonth() {

        assertEquals("MAY", conv.convertMonth(5));
        assertEquals("DECEMBER", conv.convertMonth(12));
    }

    @Test
    public void shouldConvertDay() {

        assertEquals("SUNDAY", conv.convertDay(1));
        assertEquals("TUESDAY", conv.convertDay(3));
    }
}