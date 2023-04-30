package com.mygdx.game.battle.steps;

import com.mygdx.game.battle.BattleMechanics;
import com.mygdx.game.battle.events.BattleEventQueue;
import com.mygdx.game.entities.BattleEntity;

public class DamageStep extends Step{

    public DamageStep(StepsDetails details) {
        super(details);
    }
    //for anim
    /*@Override
    public int useMove(BattleMechanics mechanics, BattleEntity user, BattleEntity enemy, BattleEventQueue eventQueue){
        int hpBefore = enemy.getCurrentHitpoints();
        int damage = super.useMove(mechanics, user, enemy, eventQueue);

        return damage;
    }*/

    @Override
    public String message() {
        return null;
    }

    @Override
    public boolean isDamaging() {
        return true;
    }

    @Override
    public Step clone() {
        return new DamageStep(details);
    }
}
