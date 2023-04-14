package com.mygdx.game.Dialog;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class OptionBoxController extends InputAdapter {
    private OptionBox optionB;

    public OptionBoxController(OptionBox box){
        this.optionB = box;
    }

    public boolean mouseUp(int key){
        if(key == Input.Keys.E){
            optionB.moveDown();
        } else if(key == Input.Keys.Q){
            optionB.moveUp();
        }
        return false;
    }

    public boolean mouseUp(){
        return false;
    }
}
