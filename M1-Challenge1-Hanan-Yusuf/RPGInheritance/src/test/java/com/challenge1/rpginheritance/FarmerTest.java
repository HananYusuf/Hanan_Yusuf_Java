package com.challenge1.rpginheritance;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FarmerTest {

    Farmer frmr1;
    @Before
    public void setUp(){
        frmr1 = new Farmer();
    }

    @Test
    public void attackAnotherCharacter() {
        Constable const1 = new Constable();
        frmr1.attackAnotherCharacter(const1);
    }

    @Test
    public void isPlowing() {
        boolean expectedValue = false;
        boolean actualValue = frmr1.isPlowing();
        assertEquals(expectedValue, actualValue);

    }

    @Test
    public void setPlowing() {
    }

    @Test
    public void isHarvesting() {
        boolean expectedValue = false;
        boolean actualValue = frmr1.isHarvesting();
        assertEquals(expectedValue, actualValue);

    }

    @Test
    public void setHarvesting() {

    }
}