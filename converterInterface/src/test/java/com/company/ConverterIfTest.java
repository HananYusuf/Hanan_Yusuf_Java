package com.company;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConverterIfTest {

    Converter conv;

    @Before
    public void setUp() {

        conv = new ConverterIf();
    }

    @Test
    public void shouldConvertMonth() {

        assertEquals("JANUARY", conv.convertMonth(1));
    }

    @Test
    public void shouldConvertDay() {

        assertEquals("FRIDAY", conv.convertDay(6));
    }
}