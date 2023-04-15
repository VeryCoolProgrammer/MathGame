package com.mygdx.game.Dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class OptionBoxController extends InputAdapter {
    private OptionBox box;

    public OptionBoxController(OptionBox box){
        this.box = box;
    }
    @Override
    public boolean keyUp(int keycode){
        if(keycode == Input.Keys.E){
            box.moveDown();
        } else if(keycode == Input.Keys.Q){
            box.moveUp();
        }
        return false;
    }

}
