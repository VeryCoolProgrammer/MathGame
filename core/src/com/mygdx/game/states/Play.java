package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.handlers.GameKeys;
import com.mygdx.game.handlers.MyContactListener;
import com.mygdx.game.handlers.GameStateManager;

import static com.mygdx.game.handlers.B2DVars.*;

public class Play extends GameState{
    private World world;
    private Body playerBody;
    private Box2DDebugRenderer b2dr;
    private OrthographicCamera b2dCam;
    private MyContactListener cl;

    public Play(GameStateManager gsm) {
        super(gsm);
        world = new World(new Vector2(0, -25), true);
        cl = new MyContactListener();
        world.setContactListener(cl);
        b2dr = new Box2DDebugRenderer();

        //create block
        BodyDef bdef = new BodyDef();
        bdef.position.set(500 / PPM, 300 / PPM);
        bdef.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(bdef);

        PolygonShape ps = new PolygonShape();
        ps.setAsBox(100 / PPM, 30 / PPM);
        FixtureDef fdef = new FixtureDef();
        fdef.shape = ps;
        fdef.filter.categoryBits = BIT_BLOCK;
        fdef.filter.maskBits = BIT_PLAYER;
        body.createFixture(fdef).setUserData("block");

        //create player
        createPlayer();

        //create map/tiles
        createTiles();

        b2dCam = new OrthographicCamera();
        b2dCam.setToOrtho(false, MyGdxGame.V_WIDTH / PPM, MyGdxGame.V_HEIGHT / PPM);
    }


    @Override
    public void handleInput() {
        if(GameKeys.isPressed(GameKeys.KEY_W)){
            playerBody.applyForceToCenter(200, 0, true);
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        world.step(dt, 6, 2);
    }

    @Override
    public void render() {
        Gdx.gl20.glClearColor(0,0,0,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        b2dr.render(world, b2dCam.combined);
    }

    @Override
    public void dispose() {

    }

    private void createPlayer() {
        BodyDef bdef = new BodyDef();
        PolygonShape ps = new PolygonShape();
        FixtureDef fdef = new FixtureDef();

        bdef.position.set(500 / PPM, 500 / PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        playerBody = world.createBody(bdef);

        ps.setAsBox(25 / PPM, 25 / PPM);
        fdef.shape = ps;
        fdef.filter.categoryBits = BIT_PLAYER;
        fdef.filter.maskBits = BIT_BLOCK;
        playerBody.createFixture(fdef).setUserData("player");

        //create foot sensor
        ps.setAsBox(10 / PPM, 10 / PPM, new Vector2(0, -20/PPM), 0);
        fdef.shape = ps;
        fdef.filter.categoryBits = BIT_PLAYER;
        fdef.filter.maskBits = BIT_BLOCK;
        fdef.isSensor = true;
        playerBody.createFixture(fdef).setUserData("foot");
    }
    private void createTiles() {
        //5-6вид
    }
}
