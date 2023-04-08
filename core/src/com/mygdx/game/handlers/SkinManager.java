package com.mygdx.game.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class SkinManager {
    public static Skin generateSkin(AssetManager assetManager) {
        Skin skin = new Skin();
        TextureAtlas uiAtlas = assetManager.get("testAtlas.atlas");

        NinePatch dialog = new NinePatch(uiAtlas.findRegion("GUI_img"), 100, 100, 100, 100);
        skin.add("GUI_img", dialog);

        BitmapFont font = assetManager.get("mcFont.fnt", BitmapFont.class);
        skin.add("font", font);
        skin.add("defJson", Gdx.files.internal("uiskin.json"));

        return skin;
    }
}
