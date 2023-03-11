package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.MyGdxGame;

public class Player extends B2DSprite{
    public Player(Body body) {
        super(body);
        Texture tex = MyGdxGame.res.getTexture("gnomik");
        TextureRegion[] sprites = TextureRegion.split(tex, 110,145)[0];

        setAnimation(sprites, 1/6f);
    }

}

