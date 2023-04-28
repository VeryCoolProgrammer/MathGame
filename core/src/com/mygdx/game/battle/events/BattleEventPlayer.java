package com.mygdx.game.battle.events;

import com.mygdx.game.UI.DialogBox;

public interface BattleEventPlayer {
    public DialogBox getDialogBox();
    public void queueEvent(BattleEvent event);
}
