package com.mygdx.game.battle.render;

import com.badlogic.gdx.InputAdapter;
import com.mygdx.game.Dialog.DialogBox;
import com.mygdx.game.Dialog.OptionBox;
import com.mygdx.game.battle.Battle;
import com.mygdx.game.battle.events.BattleEvent;

public class BattleScreenController extends InputAdapter{
    private Battle battle;
    private DialogBox dialogBox;
    private OptionBox optionBox;

    public BattleScreenController(Battle battle, DialogBox dialogBox, OptionBox optionBox){
        this.battle = battle;
        this.dialogBox = dialogBox;
        this.optionBox = optionBox;
    }

    public void update(float dt) {
        if (dialogBox.isFinished() && !optionBox.isVisible()) {
            optionBox.clearChoices();
            optionBox.addOption("Да");
            optionBox.addOption("Нет");
            optionBox.setVisible(true);
        }
    }
}
