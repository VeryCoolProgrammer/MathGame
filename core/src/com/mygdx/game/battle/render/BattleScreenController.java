package com.mygdx.game.battle.render;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.mygdx.game.Dialog.DialogBox;
import com.mygdx.game.Dialog.OptionBox;
import com.mygdx.game.battle.Battle;
import com.mygdx.game.battle.events.BattleEvent;

import java.util.Queue;

public class BattleScreenController extends InputAdapter {
    private Battle battle;
    private DialogBox dialogBox;
    private OptionBox optionBox;
    private Queue<BattleEvent> queue;

    public BattleScreenController(Battle battle, DialogBox dialogBox, OptionBox optionBox, Queue queue) {
        this.battle = battle;
        this.dialogBox = dialogBox;
        this.optionBox = optionBox;
        this.queue = queue;
    }

    public void update() {
        if (dialogBox.isFinished() && !optionBox.isVisible()) {
            optionBox.clearChoices();
            optionBox.addOption("Да");
            optionBox.addOption("Нет");
            optionBox.setVisible(true);
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        if (optionBox.isVisible()) {
            if (keycode == Input.Keys.Q) {
                optionBox.moveUp();
            } else if (keycode == Input.Keys.E) {
                optionBox.moveDown();
            } else if (keycode == Input.Keys.X) {
                if (optionBox.getIndex() == 0){
                    System.out.println(":)");
                } else if (optionBox.getIndex() == 1) {
                    battle.playerRun();
                    optionBox.setVisible(false);
                }
            }
        }
        return false;
    }
}
