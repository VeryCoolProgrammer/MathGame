package com.mygdx.game.battle;

import com.mygdx.game.battle.events.B_TextEvent;
import com.mygdx.game.battle.events.BattleEvent;
import com.mygdx.game.battle.events.BattleEventPlayer;
import com.mygdx.game.battle.events.BattleEventQueue;
import com.mygdx.game.entities.Player;

public class Battle implements BattleEventQueue {
    public enum STATE{
        READY_FOR_NEXT,
        WIN,
        LOSE,
        RUN,
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

    public void playerRun(){
        queueEvent(new B_TextEvent("Убежал...", true));
        this.state = STATE.RUN;
    }

    public STATE getState() {
        return state;
    }

    public void setEventPlayer(BattleEventPlayer player) {
        this.eventPlayer = player;
    }

    @Override
    public void queueEvent(BattleEvent event) {
        eventPlayer.queueEvent(event);
    }
}
