package com.mygdx.game.Battle.Events;

import com.mygdx.game.Dialog.DialogBox;

public interface BattleEventPlayer {
    public DialogBox getDialogBox();
    public void queueEvent(BattleEvent event);
}
