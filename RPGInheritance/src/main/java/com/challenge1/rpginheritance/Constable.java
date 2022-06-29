package com.challenge1.rpginheritance;

import com.challenge1.rpginheritance.com.challenge1.Player;

public class Constable extends Player {

    protected String jurisdiction;

    public void arrestAnotherCharacter() {
      System.out.println(this.name + "Constable:...");

    }

    public String getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }
}
