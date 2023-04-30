package com.mygdx.game.battle.steps;

import com.mygdx.game.battle.BattleMechanics;
import com.mygdx.game.battle.events.BattleEventQueue;
import com.mygdx.game.entities.BattleEntity;

public abstract class Step {
    protected StepsDetails details;

    public Step(StepsDetails details){
        this.details = details;
    }

    public int useMove(BattleMechanics mechanics, BattleEntity user, BattleEntity enemy, BattleEventQueue eventQueue){
        int damage = mechanics.calculateDamage(this, user, enemy);
        enemy.applyDamage(damage);
        return damage;
    }

    public abstract String message();

    public abstract boolean isDamaging();

    public String getName() {
        return details.getName();
    }

    public STEP_TYPE getType(){
        return details.getType();
    }

    public int getDamage() {
        return details.getDamage();
    }

    public StepsDetails getStepDetails() {
        return details;
    }

    public abstract Step clone();
}
