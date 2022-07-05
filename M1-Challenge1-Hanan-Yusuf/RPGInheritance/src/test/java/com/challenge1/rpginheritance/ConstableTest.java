package com.challenge1.rpginheritance;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConstableTest {

    Constable const1;
    @Before
    public void setUp()  {
        const1 = new Constable();
    }

    @Test
    public void arrestAnotherCharacter() {

        Warrior warr = new Warrior();
        const1.arrestAnotherCharacter(warr);

        boolean expectedValue = true;
        boolean actualValue = warr.isArrested();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void getJurisdiction() {
        Constable const1= new Constable();
        Warrior warr1 = new Warrior();
        const1.getJurisdiction();

//        String expectedValue = "You're guilty";
//        String actualValue = const1.getJurisdiction();
//        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void setJurisdiction() {
    }
}