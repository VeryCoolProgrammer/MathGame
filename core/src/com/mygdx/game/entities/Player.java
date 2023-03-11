package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.MyGdxGame;

import static com.mygdx.game.handlers.B2DVars.PPM;

public class Player extends B2DSprite{

    public Player(Body body) {
        super(body);
        Texture tex = MyGdxGame.res.getTexture("gnomik");
        TextureRegion[] sprites = TextureRegion.split(tex, 110,145)[0];

        this.speed = 40f;
        setAnimation(sprites, 1/6f);
    }
    public void updatePL(){
        x = body.getPosition().x * PPM;
        y = body.getPosition().y * PPM;
        checkUserInput();
    }

    private void checkUserInput() {
        velx = 0;
        vely = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            velx = 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            velx = -1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            vely = 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            vely = -1;
        }
        body.setLinearVelocity(velx*speed, vely*speed);
    }
}

