package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.entities.Player;
import com.mygdx.game.handlers.GameKeys;
import com.mygdx.game.handlers.MyContactListener;
import com.mygdx.game.handlers.GameStateManager;

import static com.mygdx.game.handlers.B2DVars.*;

public class Play extends GameState{
    private World world;
    private Box2DDebugRenderer b2dr;
    private OrthographicCamera b2dCam;
    private MyContactListener cl;
    private Player player;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tmr;
    private float tileSize;

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

        // ----------------------------- load map ----------------------------- 
        
        /*tiledMap = new TmxMapLoader().load("sprites/mystic_woods_free_2.1/map.tmx");
        tmr = new OrthogonalTiledMapRenderer(tiledMap, 1); // !!!

        TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get("tropa borders");
        tileSize = layer.getTileWidth();

        for (int row = 0; row < layer.getHeight(); row++) {
            for (int col = 0; col < layer.getWidth(); col++) {
                TiledMapTileLayer.Cell cell = layer.getCell(col, row);
                if (cell == null) {
                    continue;
                }
                if (cell.getTile() == null) {
                    continue;
                }

                bdef.type = BodyDef.BodyType.StaticBody;
                bdef.position.set(
                        (col + 0.5f) * tileSize / PPM,
                        (row + 0.5f) * tileSize / PPM);

                ChainShape cs = new ChainShape();
                Vector2[] v = new Vector2[3];
                v[0] = new Vector2(-tileSize / 2 / PPM, -tileSize / 2 / PPM);
                v[1] = new Vector2(-tileSize / 2 / PPM, tileSize / 2 / PPM);
                v[2] = new Vector2( tileSize / 2 / PPM, tileSize / 2 / PPM);
                cs.createChain(v);
                fdef.friction = 0;
                fdef.shape = cs;
                fdef.filter.categoryBits = 9;
                fdef.filter.maskBits = BIT_PLAYER;
                fdef.isSensor = false;
                world.createBody(bdef).createFixture(fdef);
            }
        }*/
        // ----------------------------- continue -----------------------------

        createPlayer();
        createTiles();

        b2dCam = new OrthographicCamera();
        b2dCam.setToOrtho(false, MyGdxGame.V_WIDTH / PPM, MyGdxGame.V_HEIGHT / PPM);
    }

    @Override
    public void handleInput() {
        if(GameKeys.isPressed(GameKeys.KEY_W)){
            //playerBody.applyForceToCenter(200, 0, true);
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        world.step(dt, 6, 2);
        player.update(dt);
    }

    @Override
    public void render() {
        Gdx.gl20.glClearColor(0,0,0,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /*tmr.setView(cam);
        tmr.render();*/

        //draw player
        sb.setProjectionMatrix(cam.combined);
        player.render(sb);

        //world
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
        Body body = world.createBody(bdef);

        ps.setAsBox(55f / PPM, 59f / PPM);
        fdef.shape = ps;
        fdef.filter.categoryBits = BIT_PLAYER;
        fdef.filter.maskBits = BIT_BLOCK;
        body.createFixture(fdef).setUserData("player");

        //create foot sensor
        ps.setAsBox(10f / PPM, 10f / PPM, new Vector2(0, -50f/PPM), 0);
        fdef.shape = ps;
        fdef.filter.categoryBits = BIT_PLAYER;
        fdef.filter.maskBits = BIT_BLOCK;
        fdef.isSensor = true;
        body.createFixture(fdef).setUserData("foot");

        player = new Player(body);
    }
    private void createTiles() {
        //5-6вид
    }
}
