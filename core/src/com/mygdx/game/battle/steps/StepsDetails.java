package com.mygdx.game.battle.steps;

public class StepsDetails {
    STEP_TYPE type;
    private int damage;
    private String name;

    public StepsDetails(STEP_TYPE type, int damage, String name){
        this.type = type;
        this.damage = damage;
        this.name = name;
    }

    public STEP_TYPE getType() {
        return type;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }
}
