package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.entities.Player;
import com.mygdx.game.handlers.BoundedCamera;
import com.mygdx.game.handlers.MyContactListener;
import com.mygdx.game.handlers.GameStateManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static com.mygdx.game.handlers.B2DVars.*;

public class Play extends GameState implements StateMethods{
    private boolean debug = false;
    private World world;
    private Box2DDebugRenderer b2dr;
    private BoundedCamera b2dCam;
    private MyContactListener cl;
    private Player player;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tmr;
    private float tileSize;
    private int tileMapWidth;
    private int tileMapHeight;

    public Play(GameStateManager gsm) {
        super(gsm);
        world = new World(new Vector2(0, 0), true);
        cl = new MyContactListener();
        world.setContactListener(cl);
        b2dr = new Box2DDebugRenderer();

        //create block
        /* BodyDef bdef = new BodyDef();
        bdef.position.set(500f / PPM, 300f / PPM);
        bdef.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(bdef);

        PolygonShape ps = new PolygonShape();
        ps.setAsBox(100f / PPM, 30f / PPM);
        FixtureDef fdef = new FixtureDef();
        fdef.shape = ps;
        fdef.filter.categoryBits = BIT_BLOCK;
        fdef.filter.maskBits = BIT_PLAYER;
        body.createFixture(fdef).setUserData("block");*/

        createPlayer();
        createTiles();
        cam.setBounds(0, tileMapWidth * tileSize * 4, 0, tileMapHeight * tileSize * 4);

        b2dCam = new BoundedCamera();
        b2dCam.setToOrtho(false, MyGdxGame.V_WIDTH / PPM, MyGdxGame.V_HEIGHT / PPM);
        b2dCam.setBounds(0, (tileMapWidth * tileSize) / PPM, 0, (tileMapHeight * tileSize) / PPM);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
        world.step(dt, 6, 2);
        player.update(dt);
        player.updatePL();
    }

    @Override
    public void render() {
        Gdx.gl20.glClearColor(0,0,0,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.setPosition(player.getPosition().x * PPM + MyGdxGame.V_WIDTH /35, player.getPosition().y * PPM + MyGdxGame.V_HEIGHT /35);
        //cam.position.set(player.getPosition().x * PPM / 2, player.getPosition().y * PPM / 2, 0);
        cam.update();

        //draw map
        tmr.setView(cam);
        tmr.render();

        //draw player
        sb.setProjectionMatrix(cam.combined);
        player.render(sb);

        //draw box?
        if (debug) {
            b2dCam.setPosition(player.getPosition().x + MyGdxGame.V_WIDTH / 35 , MyGdxGame.V_HEIGHT / 35 );
            b2dCam.update();
            b2dr.render(world, b2dCam.combined);
        }
    }

    @Override
    public void dispose() {
    }

    private void createPlayer() {
        BodyDef bdef = new BodyDef();
        PolygonShape ps = new PolygonShape();
        FixtureDef fdef = new FixtureDef();

        bdef.position.set(607f / PPM, 337f / PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        Body body = world.createBody(bdef);

        ps.setAsBox(55f / PPM, 59f / PPM);
        fdef.shape = ps;
        fdef.filter.categoryBits = BIT_PLAYER;
        fdef.filter.maskBits = BIT_PENEK;
        body.createFixture(fdef).setUserData("player");
        ps.dispose();

        //create foot sensor
        /*ps.setAsBox(10f / PPM, 10f / PPM, new Vector2(0, -50f/PPM), 0);
        fdef.shape = ps;
        fdef.filter.categoryBits = BIT_PLAYER;
        fdef.filter.maskBits = BIT_BLOCK;
        fdef.isSensor = true;
        body.createFixture(fdef).setUserData("foot");*/

        player = new Player(body);
        body.setUserData(player);
    }
    private void createTiles() {
        tiledMap = new TmxMapLoader().load("sprites/mystic_woods_free_2.1/map.tmx");
        tmr = new OrthogonalTiledMapRenderer(tiledMap, 4); // !!!
        tileSize = (int) tiledMap.getProperties().get("tilewidth");

        tileMapWidth = (int) tiledMap.getProperties().get("width");
        tileMapHeight = (int) tiledMap.getProperties().get("height");

        TiledMapTileLayer layer;
        layer = (TiledMapTileLayer) tiledMap.getLayers().get("tropa borders");
        createLayer(layer, BIT_TROPA);
        //layer = (TiledMapTileLayer) tiledMap.getLayers().get("grass");
    }
    private void createLayer(TiledMapTileLayer layer, short bits){
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();

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
                //fdef.filter.categoryBits = BIT_PENEK;
                fdef.filter.maskBits = BIT_PLAYER;
                fdef.isSensor = false;
                world.createBody(bdef).createFixture(fdef);
                cs.dispose();
            }
        }
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
