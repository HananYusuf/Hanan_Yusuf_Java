package com.challenge1.rpginheritance;

import com.challenge1.rpginheritance.com.challenge1.Player;

public class Warrior extends Player {

protected int shieldStrength;

    public void arrestAnotherCharacter() {
        System.out.println(this.name + "Warrior:...");
    }

    public int getShieldStrength() {
        return shieldStrength;
    }

    public void setShieldStrength(int shieldStrength) {
        this.shieldStrength = shieldStrength;
    }
}
