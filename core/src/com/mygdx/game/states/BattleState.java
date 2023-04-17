package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Battle.Battle;
import com.mygdx.game.Battle.Events.BattleEvent;
import com.mygdx.game.Battle.Events.BattleEventPlayer;
import com.mygdx.game.Dialog.DialogBox;
import com.mygdx.game.Dialog.OptionBox;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.entities.Player;
import com.mygdx.game.handlers.GameStateManager;

import java.util.ArrayDeque;
import java.util.Queue;

public class BattleState extends GameState implements BattleEventPlayer {
    private MyGdxGame game;
    private BattleEvent currentEvent;
    private Queue<BattleEvent> queue = new ArrayDeque<BattleEvent>();

    private Battle battle;

    private Viewport gameViewport;

    private Stage uiStage;
    private Table dialogRoot;
    private DialogBox dialogBox;
    private OptionBox optionBox;

    public BattleState(GameStateManager gsm) {
        super(gsm);
        gameViewport = new ScreenViewport();
        game = gsm.game();

        battle = new Battle();
        battle.setEventPlayer(this);

        initUI();

        battle.beginBattle();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
        uiStage.act(dt);
    }

    @Override
    public void render() {
        Gdx.gl20.glClearColor(0,0,0,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameViewport.apply();

        sb.setProjectionMatrix(cam.combined);

        uiStage.draw();
    }

    private void initUI() {
        uiStage = new Stage(new ScreenViewport());
        uiStage.getViewport().update(1215, 675, true);

        dialogRoot = new Table();
        dialogRoot.setFillParent(true);
        uiStage.addActor(dialogRoot);

        dialogBox = new DialogBox(game.getSkin());
        dialogBox.setVisible(false);

        optionBox = new OptionBox(game.getSkin());
        optionBox.setVisible(false);

        Table dialogTable = new Table();
        dialogTable.add(optionBox)
                .expand().align(Align.right)
                .space(8f)
                .row();
        dialogTable.add(dialogBox)
                .expand().align(Align.bottom)
                .space(8f)
                .row();

        dialogRoot.add(dialogTable).expand().align(Align.bottom).pad(15f);
    }
    @Override
    public void dispose() {

    }

    @Override
    public DialogBox getDialogBox() {
        return null;
    }

    @Override
    public void queueEvent(BattleEvent event) {

    }
}
