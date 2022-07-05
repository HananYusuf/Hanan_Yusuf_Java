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
        //Arrange
        Warrior warr = new Warrior();
        //Act
        //Assert
        const1.arrestAnotherCharacter(warr);
    }

    @Test
    public void getJurisdiction() {
        Constable const1= new Constable();
        Warrior warr1 = new Warrior();
        const1.getJurisdiction();
    }

    @Test
    public void setJurisdiction() {
    }
}