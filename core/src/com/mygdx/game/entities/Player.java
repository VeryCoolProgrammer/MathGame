package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.handlers.Animation;
import com.mygdx.game.handlers.GameKeys;
import com.mygdx.game.handlers.MyInputProcessor;

import static com.mygdx.game.handlers.B2DVars.*;

public class Player extends B2DSprite {
    private TextureRegion[] sprites;
    private Texture tex;

    private boolean canGo = false;
    private MyInputProcessor mip;

    public Player(Body body) {
        super(body);
        tex = MyGdxGame.res.getTexture("gnomik");
        //sprites = TextureRegion.split(tex, 110, 130)[0]; //110 130 - 1row, 120 130 - step

        speed = 40f;
        animUpdate(); // ?????
        //setAnimation(sprites, 1 / 12f);
    }

    public void updatePL() {
        x = body.getPosition().x * PPM;
        y = body.getPosition().y * PPM;
        checkUserInput();
        checkAnim();
    }

    private void checkUserInput() {
        velx = 0;
        vely = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            velx = 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            velx = -1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            vely = 1;
            canGo = true;
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.W)) {
            canGo = false; // -------
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            vely = -1;
        }
        body.setLinearVelocity(velx * speed, vely * speed);
    }

    private void checkAnim(){ // -------
        if ((Gdx.input.isKeyJustPressed(Input.Keys.S) & canGo == true)){
            sprites = TextureRegion.split(tex, 120, 130)[0];
            setAnimation(sprites, 1 / 12f);
        }
        if ((!Gdx.input.isKeyJustPressed(Input.Keys.S) & canGo == false)){
            sprites = TextureRegion.split(tex, 120, 130)[0];
            setAnimation(sprites, 1 / 12f);
            System.out.println(canGo);
        }
        if ((Gdx.input.isKeyJustPressed(Input.Keys.W) & canGo == true)){
            sprites = TextureRegion.split(tex, 120, 129)[2];
            setAnimation(sprites, 1 / 12f);
        }
        if ((!Gdx.input.isKeyJustPressed(Input.Keys.W) & canGo == false)){
            sprites = TextureRegion.split(tex, 120, 129)[2];
            setAnimation(sprites, 1 / 12f);
        }
    }

    private void animUpdate(){
        //через myInpProccesor???
        sprites = TextureRegion.split(tex, 110, 130)[0];
        System.out.println("true");
        setAnimation(sprites, 1 / 12f);
    }
}

