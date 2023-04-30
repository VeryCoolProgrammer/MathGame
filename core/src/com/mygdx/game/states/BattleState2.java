package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Dialog.*;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.UI.DialogBox;
import com.mygdx.game.UI.OptionBox;
import com.mygdx.game.UI.SelectionBox;
import com.mygdx.game.battle.Battle;
import com.mygdx.game.battle.events.BattleEvent;
import com.mygdx.game.battle.events.BattleEventPlayer;
import com.mygdx.game.battle.render.BattleRenderer;
import com.mygdx.game.battle.render.BattleScreenController;
import com.mygdx.game.entities.BattleEntity;
import com.mygdx.game.entities.Boss;
import com.mygdx.game.handlers.GameStateManager;
import com.mygdx.game.handlers.MyContactListener;

import java.util.ArrayDeque;
import java.util.Queue;

import static com.mygdx.game.handlers.B2DVars.*;

public class BattleState2 extends GameState implements BattleEventPlayer {
    private MyGdxGame game;
    private World world;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tmr;
    private float tileSize;
    private int tileMapWidth;
    private int tileMapHeight;
    private MyContactListener cl;
    // UI
    private Stage uiStage;
    private Dialog dialog;
    private Table dialogRoot;
    private DialogBox dialogBox;
    private OptionBox optionBox;
    private Table selectionRoot;
    private SelectionBox selectionBox;
    private OptionBoxController obc;
    private DialogController dcontroller;
    // END UI
    private BattleScreenController bcontroller;
    private InputMultiplexer multiplexer;
    private BattleEvent currentEvent;
    private Queue<BattleEvent> queue = new ArrayDeque<BattleEvent>();
    private Battle battle;
    private BattleRenderer battleRenderer;
    private Boss boss;
    private Texture tex;
    private Texture texEnemy;

    public BattleState2(GameStateManager gsm) {
        super(gsm);
        world = new World(new Vector2(0, 0), true);
        cl = new MyContactListener();
        world.setContactListener(cl);
        game = gsm.game();
        multiplexer = new InputMultiplexer();

        cam.setBounds(0, 4864, 0, 2688);

        tex = MyGdxGame.res.getTexture("gnomik");
        tex = MyGdxGame.res.getTexture("enemy");
        battle = new Battle(BattleEntity.generateEntity("player", tex, game.getStepDatabase()),
                BattleEntity.generateEntity("enemy", texEnemy, game.getStepDatabase()));
        battle.setEventPlayer(this);

        //battleRenderer = new BattleRenderer(game.getAssetManager());

        initUI();
        createLayers();
        createEnemy();

        bcontroller = new BattleScreenController(battle, queue, dialogBox, optionBox, selectionBox);

        battle.beginBattle();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
        //System.out.println(battle.getState() + " " + currentEvent);
        world.step(dt, 6, 2);
        //dcontroller.update(dt);
        //bcontroller.update(dt); <----- only selectionBox
        while (currentEvent == null || currentEvent.finished()) {
            if (queue.peek() == null) {
                currentEvent = null;
                if(battle.getState() == Battle.STATE.READY_TO_PROGRESS){
                    bcontroller.restart();
                } else if (battle.getState() == Battle.STATE.RUN) {
                    gsm.setState(GameStateManager.PLAY);
                } else if (battle.getState() == Battle.STATE.WIN) {
                    gsm.setState(GameStateManager.PLAY);
                } else if (battle.getState() == Battle.STATE.LOSE) {
                    gsm.setState(GameStateManager.PLAY);
                } else if (battle.getState() == Battle.STATE.RUN) {
                    gsm.setState(GameStateManager.PLAY);
                }
                break;
            } else {
                currentEvent = queue.poll();
                currentEvent.begin(this);
            }
        }

        if (currentEvent != null) {
            currentEvent.update(dt);
        }

        uiStage.act(dt);
        boss.update(dt);
    }

    @Override
    public void render() {
        Gdx.input.setInputProcessor(bcontroller);
        Gdx.gl20.glClearColor(0,0,0,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.setPosition(0, 0);
        cam.update();

        //draw map
        tmr.setView(cam);
        tmr.render();
        //draw enemy
        boss.render(sb);

        /*sb.begin();
        battleRenderer.render(sb);
        sb.end();*/

        sb.setProjectionMatrix(cam.combined);

        uiStage.draw();
    }

    private void initUI() {
        uiStage = new Stage(new ScreenViewport());
        uiStage.getViewport().update(1215, 672, true);

        dialogRoot = new Table();
        dialogRoot.setFillParent(true);
        uiStage.addActor(dialogRoot);

        selectionRoot = new Table();
        selectionRoot.setFillParent(true);
        uiStage.addActor(selectionRoot);

        dialogBox = new DialogBox(game.getSkin());
        dialogBox.setVisible(false);

        optionBox = new OptionBox(game.getSkin());
        optionBox.setVisible(false);

        selectionBox = new SelectionBox(game.getSkin());
        selectionBox.setVisible(false);

        Table dialogTable = new Table();
        dialogTable.add(optionBox)
                .expand().align(Align.right)
                .space(8f)
                .row();
        dialogTable.add(dialogBox)
                .expand().align(Align.bottom)
                .space(8f)
                .row();

        selectionRoot.add(selectionBox).expand().align(Align.bottom).pad(5f);
        dialogRoot.add(dialogTable).expand().align(Align.bottom).pad(15f);

        /*obc = new OptionBoxController(optionBox);
        dcontroller = new DialogController(dialogBox, optionBox);
        multiplexer.addProcessor(obc);
        multiplexer.addProcessor(dcontroller);
        Gdx.input.setInputProcessor(multiplexer);*/

        /*dialog = new Dialog();
        DialogNode node1 = new DialogNode("Привет! Это первая фраза", 0);
        DialogNode node2 = new DialogNode("И это вторая?", 1);
        DialogNode node3 = new DialogNode("Да, ты прав", 2);
        DialogNode node4 = new DialogNode("Неа, не угадал :(", 3);

        node1.makeLinear(node2.getId());
        node2.addChoice("Да", 2);
        node2.addChoice("Нет", 3);

        dialog.addNode(node1);
        dialog.addNode(node2);
        dialog.addNode(node3);
        dialog.addNode(node4);
        dcontroller.startDialog(dialog);*/
    }

    private void createLayers() {
        tiledMap = new TmxMapLoader().load("sprites/mystic_woods_free_2.1/fightmap.tmx");
        tmr = new OrthogonalTiledMapRenderer(tiledMap, 4); // !!!
        tileSize = (int) tiledMap.getProperties().get("tilewidth");

        tileMapWidth = (int) tiledMap.getProperties().get("width");
        tileMapHeight = (int) tiledMap.getProperties().get("height");
    }

    private void createEnemy() {
        PolygonShape ps = new PolygonShape();
        MapLayer layer = tiledMap.getLayers().get("enemy");
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set(600f / PPM, 530f / PPM);
        Body body = world.createBody(bdef);

        boss = new Boss(body);
        body.setUserData(boss);
    }

    @Override
    public void dispose() {

    }

    @Override
    public DialogBox getDialogBox() {
        return dialogBox;
    }

    @Override
    public void queueEvent(BattleEvent event) {
        queue.add(event);
    }
}
