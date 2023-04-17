package com.mygdx.game.Battle;

import com.mygdx.game.Battle.Events.B_TextEvent;
import com.mygdx.game.Battle.Events.BattleEvent;
import com.mygdx.game.Battle.Events.BattleEventPlayer;
import com.mygdx.game.Battle.Events.BattleEventQueue;
import com.mygdx.game.entities.Player;

public class Battle implements BattleEventQueue {
    public enum STATE{
        READY_FOR_NEXT,
        WIN,
        LOSE,
        ;
    }
    private STATE state;
    private Player player;
    private BattleEventPlayer eventPlayer;

    public Battle(){
        //this.player = player;
        this.state = STATE.READY_FOR_NEXT;
    }

    public void beginBattle(){
        queueEvent(new B_TextEvent("хехехехехех!", 1f));
    }

    public void progress(int input){
        if(state != STATE.READY_FOR_NEXT){
            return;
        }
    }

    public void setEventPlayer(BattleEventPlayer player) {
        this.eventPlayer = player;
    }

    @Override
    public void queueEvent(BattleEvent event) {
        eventPlayer.queueEvent(event);
    }
}
