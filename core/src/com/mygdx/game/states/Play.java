package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.handlers.GameStateManager;

public class Play extends GameState{
    private World world;
    private Box2DDebugRenderer b2dr;

    public Play(GameStateManager gsm) {
        super(gsm);
        world = new World(new Vector2(0, 0), true);
        b2dr = new Box2DDebugRenderer();

        //create body
        BodyDef bdef = new BodyDef();
        bdef.position.set(500, 200);
        bdef.type = BodyDef.BodyType.DynamicBody;
        Body body = world.createBody(bdef);

        PolygonShape ps = new PolygonShape();
        ps.setAsBox(20, 20);
        FixtureDef fdef = new FixtureDef();
        fdef.shape = ps;
        body.createFixture(fdef);

    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
        world.step(dt, 6, 2);
    }

    @Override
    public void render() {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        b2dr.render(world, cam.combined);
    }

    @Override
    public void dispose() {

    }
}
