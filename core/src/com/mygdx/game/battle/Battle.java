package com.mygdx.game.battle;

import com.mygdx.game.battle.events.B_TextEvent;
import com.mygdx.game.battle.events.BattleEvent;
import com.mygdx.game.battle.events.BattleEventPlayer;
import com.mygdx.game.battle.events.BattleEventQueue;
import com.mygdx.game.entities.Player;

public class Battle implements BattleEventQueue {
    public enum STATE{
        READY_TO_PROGRESS,
        WIN,
        LOSE,
        RUN,
        ;
    }
    private STATE state;
    private BattleEventPlayer eventPlayer;

    public Battle(){
        this.state = STATE.READY_TO_PROGRESS;
    }

    public void beginBattle(){
        queueEvent(new B_TextEvent("хехехехехех!", 1f));
    }

    public void progress(int input){
        if(state != STATE.READY_TO_PROGRESS){
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

    public BattleEventPlayer getEventPlayer() {
        return eventPlayer;
    }

    public void setEventPlayer(BattleEventPlayer player) {
        this.eventPlayer = player;
    }

    @Override
    public void queueEvent(BattleEvent event) {
        eventPlayer.queueEvent(event);
    }
}
