package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.handlers.B2DVars;
import com.mygdx.game.inputs.MyInputProcessor;

import static com.mygdx.game.handlers.B2DVars.*;
import static com.mygdx.game.handlers.B2DVars.PlayerAnim.*;

public class Player2 extends B2DSprite {
    private TextureRegion[] sprites;
    private Texture tex;
    private int dir = -1;

    public Player2(Body body) {
        super(body);
        tex = MyGdxGame.res.getTexture("gnomik");
        sprites = TextureRegion.split(tex, 80, 88)[0];
        speed = 40f;
        setAnimation(sprites, 1 / 12f);
    }

    public void updatePL() {
        x = body.getPosition().x * PPM;
        y = body.getPosition().y * PPM;
        checkUserInput();
    }

    /*private void loadAnim(int array){
        //sprites = new TextureRegion[8][10];
        sprites[array] = TextureRegion.split(tex, 120, 140)[array];
    }*/

    private void checkUserInput() {
        velx = 0;
        vely = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            setDir(RIGHT);
            velx = 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            setDir(LEFT);
            velx = -1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            setDir(UP);
            vely = 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            setDir(DOWN);
            vely = -1;
        }
        body.setLinearVelocity(velx * speed, vely * speed);
        if(Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)){
            checkAnim();
            setAnimation(sprites, 1/12f);
        }
    }

    private void checkAnim(){
        switch (dir){
            case RIGHT:
                sprites = TextureRegion.split(tex, 80, 86)[3];
                break;
            case LEFT:
                sprites = TextureRegion.split(tex, 80, 86)[1];
                break;
            case UP:
                sprites = TextureRegion.split(tex, 80, 88)[2];
                break;
            case DOWN:
                sprites = TextureRegion.split(tex, 80, 88)[0];
                break;
            default:
                return;
        }
    }

    public void setDir(int dir){
        this.dir = dir;
    }
}

