package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.inputs.MyInputProcessor;

import static com.mygdx.game.handlers.B2DVars.PPM;

public class Player2 extends B2DSprite {
    private TextureRegion[][] sprites;
    private Texture tex;

    public Player2(Body body) {
        super(body);
        tex = MyGdxGame.res.getTexture("gnomikFull");
        loadAnim(0);
        speed = 40f;
        //setPlayerAnimation(sprites, 1 / 12f);
    }

    public void updatePL() {
        x = body.getPosition().x * PPM;
        y = body.getPosition().y * PPM;
        checkUserInput();
    }

    private void loadAnim(int array){
        //sprites = new TextureRegion[8][10];
        sprites[array] = TextureRegion.split(tex, 120, 140)[array];
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
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            vely = -1;
        }
        body.setLinearVelocity(velx * speed, vely * speed);
    }
}

