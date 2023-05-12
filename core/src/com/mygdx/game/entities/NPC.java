package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.MyGdxGame;

public class NPC extends B2DSprite{
    public Texture tex;
    public TextureRegion[] sprites;

    public NPC(Body body) {
        super(body);
        tex = MyGdxGame.res.getTexture("npc");
        sprites = TextureRegion.split(tex, 50, 65)[0];
        setAnimation(sprites, 1 / 5f);
    }
}