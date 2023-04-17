package com.mygdx.game.Battle.Events;

import com.mygdx.game.Battle.Battle;

public interface BattleEventQueue {
    public void queueEvent(BattleEvent event);
}
