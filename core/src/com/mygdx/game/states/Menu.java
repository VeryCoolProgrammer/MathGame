package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.handlers.GameStateManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static com.mygdx.game.MyGdxGame.V_WIDTH;

public class Menu extends GameState implements StateMethods{
    private World world;
    private Texture texture;
    private SpriteBatch batch;

    public Menu(GameStateManager gsm) {
        super(gsm);
        world = new World(new Vector2(0, 0), true);
        texture = new Texture("gnom1rowP1.png");
        batch = new SpriteBatch();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
        world.step(dt, 6, 2);
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            gsm.setState(GameStateManager.PLAY);
        }
    }

    @Override
    public void render() {
        batch.begin();
        batch.draw(texture,50,50);
        batch.end();
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
