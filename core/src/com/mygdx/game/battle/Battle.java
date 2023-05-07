package com.mygdx.game.battle;

import com.mygdx.game.UI.SelectionBox;
import com.mygdx.game.battle.events.B_TextEvent;
import com.mygdx.game.battle.events.BattleEvent;
import com.mygdx.game.battle.events.BattleEventPlayer;
import com.mygdx.game.battle.events.BattleEventQueue;
import com.mygdx.game.battle.examples.EXAMPLE_LIST;
import com.mygdx.game.battle.examples.Example;
import com.mygdx.game.battle.steps.Step;
import com.mygdx.game.battle.steps.StepsDetails;
import com.mygdx.game.entities.BattleEntity;
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
    private BattleEntity player;
    private BattleEntity enemy;
    private BattleMechanics mechanics;
    public int currentIndex;
    public int currentAnswer;

    public Battle(BattleEntity player, BattleEntity enemy){
        this.player = player;
        this.enemy = enemy;
        mechanics = new BattleMechanics();
        this.state = STATE.READY_TO_PROGRESS;
        currentIndex = 1;
        currentAnswer = 0;
    }

    public void beginBattle(){
        queueEvent(new B_TextEvent("хехехехехех!", true));
    }

    public void progress(int input){
        if(state != STATE.READY_TO_PROGRESS){
            return;
        }
        if(mechanics.isFirst(player, enemy)){
            playTurn(ENTITY_LIST.PLAYER, input);
            System.out.println(player.getCurrentHP() + " HP " + enemy.getCurrentHP());
            if (state == STATE.READY_TO_PROGRESS) {
                playTurn(ENTITY_LIST.ENEMY, 0);
            }
        }
    }

    private void playTurn(ENTITY_LIST entity, int input){
        ENTITY_LIST list = ENTITY_LIST.getEntities(entity);
        BattleEntity battleUser = null;
        BattleEntity battleTarget = null;

        if (entity == ENTITY_LIST.PLAYER) {
            battleUser = player;
            battleTarget = enemy;
        } else if (entity == ENTITY_LIST.ENEMY) {
            battleUser = enemy;
            battleTarget = player;
        }

        Step step = battleUser.getSteps(input);

        queueEvent(new B_TextEvent(battleUser.getName() + " атакует!", 0.5f));

        if(mechanics.attemptHit(step, battleUser, battleTarget)){
            step.useMove(mechanics, battleUser, battleTarget, entity, this);
        }

        if(player.isDefeated()){
            boolean isAlive = false;
            if(isAlive){
                queueEvent(new B_TextEvent("Прогирал...", true));
                this.state = STATE.LOSE;
            }
        } else if(enemy.isDefeated()){
            queueEvent(new B_TextEvent("Ура, победа!", true));
            this.state = STATE.WIN;
        }
    }

    public void playAnswers(StepsDetails steps, SelectionBox selectionBox){
        System.out.println(currentAnswer + " currentAnswer");
        for (int i = 0; i <= 3; i++) {
            String label = "---";
            steps = player.getDetails(currentAnswer + i);
            if (steps != null) {
                label = steps.getName();
            }
            selectionBox.setLabel(i, label);
        }
        currentAnswer++;
    }

    public void playExamples(Example example){ //if else???
        System.out.println(example.getList());
        Example thisEx = example;
        if(example.getList() == EXAMPLE_LIST.EXAMPLE_1){
            queueEvent(new B_TextEvent(thisEx.getName(), true));
            currentIndex++;
        } else if(example.getList() == EXAMPLE_LIST.EXAMPLE_2){
            queueEvent(new B_TextEvent(thisEx.getName(), true));
            currentIndex++;
        } else if(example.getList() == EXAMPLE_LIST.EXAMPLE_3){
            queueEvent(new B_TextEvent(thisEx.getName(), true));
            currentIndex++;
        } else if(example.getList() == EXAMPLE_LIST.EXAMPLE_4){
            queueEvent(new B_TextEvent(thisEx.getName(), true));
            currentIndex++;
        } else if(example.getList() == EXAMPLE_LIST.EXAMPLE_5){
            queueEvent(new B_TextEvent(thisEx.getName(), true));
            currentIndex++;
        }
    }

    public void playerRun(){
        queueEvent(new B_TextEvent("Убежал...", true));
        this.state = STATE.RUN;
    }

    public BattleEntity getPlayer() {
        return player;
    }

    public BattleEntity getEnemy() {
        return enemy;
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
