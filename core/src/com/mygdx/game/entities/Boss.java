package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.MyGdxGame;

public class Boss extends B2DSprite{
    public Texture tex;
    public TextureRegion[] sprites;

    public Boss(Body body) {
        super(body);
        tex = MyGdxGame.res.getTexture("gnomik");
        sprites = TextureRegion.split(tex, 80, 88)[0];
        setAnimation(sprites, 1 / 12f);
    }
}
