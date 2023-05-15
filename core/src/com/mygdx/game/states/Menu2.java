package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Dialog.Dialog;
import com.mygdx.game.Dialog.DialogController;
import com.mygdx.game.Dialog.DialogNode;
import com.mygdx.game.Dialog.OptionBoxController;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.UI.*;
import com.mygdx.game.battle.Battle;
import com.mygdx.game.battle.events.BattleEvent;
import com.mygdx.game.battle.render_controller.BattleRenderer;
import com.mygdx.game.battle.render_controller.BattleScreenController;
import com.mygdx.game.entities.Boss;
import com.mygdx.game.handlers.GameStateManager;
import com.mygdx.game.handlers.MyContactListener;

import java.util.ArrayDeque;
import java.util.Queue;

import static com.mygdx.game.handlers.B2DVars.PPM;
import static com.mygdx.game.handlers.GameStateManager.MENU;
import static com.mygdx.game.handlers.GameStateManager.PLAY;

public class Menu2 extends GameState{
    private MyGdxGame game;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tmr;
    private float tileSize;
    private int tileMapWidth;
    private int tileMapHeight;
    private InputMultiplexer multiplexer;
    // UI
    private Table root;
    private Stage uiStage;
    private MenuOptionBox optionBox;
    private BitmapFont font = new BitmapFont(Gdx.files.internal("mcRus.fnt"));
    // END UI
    public Menu2(GameStateManager gsm) {
        super(gsm);
        game = gsm.game();
        multiplexer = new InputMultiplexer();
        cam.setBounds(0, 4864, 0, 2688);

        init();
        createLayers();
    }

    private void createLayers() {
        tiledMap = new TmxMapLoader().load("sprites/mystic_woods_free_2.1/menu.tmx");
        tmr = new OrthogonalTiledMapRenderer(tiledMap, 3.82f); // !!!
        tileSize = (int) tiledMap.getProperties().get("tilewidth");

        tileMapWidth = (int) tiledMap.getProperties().get("width");
        tileMapHeight = (int) tiledMap.getProperties().get("height");
    }

    private void init(){
        uiStage = new Stage(new ScreenViewport());
        uiStage.getViewport().update(1215, 675, true);

        root = new Table();
        root.setFillParent(true);
        uiStage.addActor(root);

        optionBox = new MenuOptionBox(game.getSkin());
        optionBox.setVisible(true);
        optionBox.addOption("   Начать  ");
        optionBox.addOption("   Выход   ");

        Table table = new Table();
        table.add(optionBox)
                .expand().align(Align.bottomRight)
                .space(8f)
                .row();

        root.add(table).expand().align(Align.bottom).padBottom(150f);
    }

    @Override
    public void handleInput() {
    }

    @Override
    public void update(float dt) {
        uiStage.act(dt);
        if (Gdx.input.isKeyPressed(Input.Keys.Q)){
            optionBox.moveUp();
        } else if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            optionBox.moveDown();
        } else if (Gdx.input.isKeyPressed(Input.Keys.X)) {
            if (optionBox.getIndex() == 0) {
                gsm.setState(PLAY);
            } else if (optionBox.getIndex() == 1) {
                System.exit(0);
            }
        }
    }

    @Override
    public void render() {
        Gdx.gl20.glClearColor(0,0,0,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.setPosition(0, 0);
        cam.update();

        tmr.setView(cam);
        tmr.render();

        sb.setProjectionMatrix(cam.combined);

        sb.begin();
        font.getData().setScale(2);
        font.setColor(Color.BLACK);
        font.draw(sb, "MathsGame", 415, 435);
        sb.end();

        uiStage.draw();
    }

    @Override
    public void dispose() {

    }
}
