package com.mygdx.game.states;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.handlers.GameStateManager;

public class Play extends GameState{
    private World world;
    public Play(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render() {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
