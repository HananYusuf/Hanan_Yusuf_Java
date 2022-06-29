package com.challenge1.rpginheritance;

import com.challenge1.rpginheritance.com.challenge1.Player;

public class Farmer extends Player {

    protected boolean plowing;
    protected boolean harvesting;

    public void attackAnotherCharacter() {
        System.out.println(this.name + "Farmer:....");

    }


    public boolean isPlowing() {
        return plowing;
    }

    public void setPlowing(boolean plowing) {
        this.plowing = plowing;
    }

    public boolean isHarvesting() {
        return harvesting;
    }

    public void setHarvesting(boolean harvesting) {
        this.harvesting = harvesting;
    }


}
