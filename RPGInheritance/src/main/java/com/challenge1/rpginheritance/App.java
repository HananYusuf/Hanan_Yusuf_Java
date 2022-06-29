package com.challenge1.rpginheritance;

public class App {

    public static void main(String[] args) {

        Farmer frmr1 = new Farmer();
        frmr1.setName("Hanan");
        frmr1.setStrength(75);
        frmr1.setHealth(100);
        frmr1.setStamina(75);
        frmr1.setSpeed(10);
        frmr1.setAttackPower(1);
        frmr1.setRunning(false);
        frmr1.setArrested(false);
        frmr1.setPlowing(false);
        frmr1.setHarvesting(false);

        frmr1.attackAnotherCharacter();

        Constable const1 = new Constable();
        const1.setName("Steve");
        const1.setStrength(60);
        const1.setHealth(100);
        const1.setStamina(60);
        const1.setSpeed(20);
        const1.setAttackPower(5);
        const1.setRunning(false);
        const1.setArrested(false);
        const1.setJurisdiction("You're guilty");

        const1.arrestAnotherCharacter();

        Warrior warr1 = new Warrior();
        warr1.setName("Dave");
        warr1.setStrength(75);
        warr1.setHealth(100);
        warr1.setStamina(100);
        warr1.setSpeed(50);
        warr1.setAttackPower(10);
        warr1.setShieldStrength(100);
        warr1.setRunning(false);

    }
}
