package com.mygdx.game.handlers;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.states.GameState;
import com.mygdx.game.states.Menu;
import com.mygdx.game.states.Play;

import java.util.Stack;

public class GameStateManager {
        private MyGdxGame game;
    private Stack<GameState> gameStates;
    public static final int PLAY = 912837;
    public static final int MENU = 0;
    public static final int QUIT = 1;

    public GameStateManager(MyGdxGame game) {
        this.game = game;
        gameStates = new Stack<GameState>();
        pushState(MENU);
    }

    public MyGdxGame game(){
        return game;
    }

    public void update(float dt){
        gameStates.peek().update(dt);
    }
    public void render(){
        gameStates.peek().render();
    }

    private GameState getState(int state){
        if (state == PLAY) {
            return new Play(this);
        } else if (state == MENU) {
            return new Menu(this);
        }
        return null;
    }
    public void setState(int state){
        popState();
        pushState(state);
    }
    public void popState() {
        GameState gs = gameStates.pop();
        gs.dispose();
    }
    public void pushState(int state) {
        gameStates.push(getState(state));
    }

}
