package com.mygdx.game.inputs;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class MyInputProcessor extends InputAdapter {
    public boolean keyDown(int k){
        if (k == Input.Keys.W) {
            GameKeys.setKey(GameKeys.KEY_W, true);
        }
        if (k == Input.Keys.S) {
            GameKeys.setKey(GameKeys.KEY_S, true);
        }
        if (k == Input.Keys.A) {
            GameKeys.setKey(GameKeys.KEY_A, true);
        }
        if (k == Input.Keys.D) {
            GameKeys.setKey(GameKeys.KEY_D, true);
        }
        return true;
    }
    public boolean keyUp(int k){
        if (k == Input.Keys.W) {
            GameKeys.setKey(GameKeys.KEY_W, false);
        }
        if (k == Input.Keys.S) {
            GameKeys.setKey(GameKeys.KEY_S, false);
        }
        if (k == Input.Keys.A) {
            GameKeys.setKey(GameKeys.KEY_A, false);
        }
        if (k == Input.Keys.D) {
            GameKeys.setKey(GameKeys.KEY_D, false);
        }
        return true;
    }
}
