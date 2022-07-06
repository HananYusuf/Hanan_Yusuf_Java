package com.challenge1.rpginheritance;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WarriorTest {

    Warrior warr1;
    @Before
    public void setUp() {
        warr1 = new Warrior();
    }

    @Test
    public void shouldAttackAnotherCharacter() {
        //Arrange
        Farmer frmr1 = new Farmer();
        int expectedStrengthValue = 58;
        warr1.attackAnotherCharacter(frmr1);
        //Act
        int actualStrengthValue = frmr1.strength-2;
        //Assort
        assertEquals(expectedStrengthValue, actualStrengthValue);

    }

    @Test
    public void shouldTestShieldStrength() {
        int expectedOutput = 100;
        int actualOutput = warr1.getShieldStrength();
        assertEquals(expectedOutput, actualOutput);

    }

}