package com.challenge1.rpginheritance;

import java.util.Objects;

public class Warrior extends Player {

private int shieldStrength;

    public Warrior(String name, int strength, int health, int stamina, int speed, int attackPower, boolean running, boolean arrested, int shieldStrength) {
        super(name, strength, health, stamina, speed, attackPower, running, arrested);
        this.shieldStrength = shieldStrength;
    }

    public Warrior(int shieldStrength) {
        this.shieldStrength = shieldStrength;
    }

    public Warrior() {

    }

    public int attackAnotherCharacter(Player player) {
        //  player.getStrength();
          //if player attacks someone else then player strength increases by 2
//        if(player.){
//     }
         //when this method is called pass in a player that is being attacked or attacking
        //strength += 2
        System.out.println(this.name + " " + "is a Warrior.");
         return strength +=2;
    }

    public int getShieldStrength() {
        //System.out.println(player.getSpeed());
        return shieldStrength = 100;

    }

    public void setShieldStrength(int shieldStrength) {
        this.shieldStrength = shieldStrength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warrior warrior = (Warrior) o;
        return shieldStrength == warrior.shieldStrength;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shieldStrength);
    }

    @Override
    public String toString() {
        return "Warrior{" +
                "shieldStrength=" + shieldStrength +
                ", name='" + name + '\'' +
                ", strength=" + strength +
                ", health=" + health +
                ", stamina=" + stamina +
                ", speed=" + speed +
                ", attackPower=" + attackPower +
                ", running=" + running +
                ", arrested=" + arrested +
                '}';
    }
}
