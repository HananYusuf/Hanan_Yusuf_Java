package com.challenge1.rpginheritance;

public class Constable extends Player {

   private String jurisdiction;

    public void arrestAnotherCharacter(Player player) {
        player.setArrested(true);
      System.out.println(this.name + " " + "is a Constable.");

    }

    public String getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }
}
