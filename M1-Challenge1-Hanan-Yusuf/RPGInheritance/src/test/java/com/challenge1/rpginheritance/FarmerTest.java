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
    public void shouldAttackAnotherCharacter() {
        Constable const1 = new Constable("Guilty");
        int expectedValue = 59;
        frmr1.attackAnotherCharacter(const1);
        int actualValue = const1.getStrength(); //frmr1 attack const1
        assertEquals(expectedValue, actualValue);
    }

//    @Test
//    public void isPlowing() {
//        boolean expectedValue = false;
//        boolean actualValue = frmr1.isPlowing();
//        assertEquals(expectedValue, actualValue);
//
//    }
//
//    @Test
//    public void setPlowing() {
//    }
//
//    @Test
//    public void isHarvesting() {
//        boolean expectedValue = false;
//        boolean actualValue = frmr1.isHarvesting();
//        assertEquals(expectedValue, actualValue);
//
//    }
//
//    @Test
//    public void setHarvesting() {
//
//    }
}