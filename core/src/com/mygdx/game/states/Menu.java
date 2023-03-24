package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.handlers.GameStateManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static com.mygdx.game.MyGdxGame.V_WIDTH;

public class Menu extends GameState implements StateMethods{

    public Menu(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            gsm.setState(GameStateManager.PLAY);
        }
    }

    @Override
    public void render() {

    }

    @Override
    public void dispose() {

    }


    @Override
    public void draw(Graphics g) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent k) {

    }

    @Override
    public void keyReleased(KeyEvent k) {

    }
}
