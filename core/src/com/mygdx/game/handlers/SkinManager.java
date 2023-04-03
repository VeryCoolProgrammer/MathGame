package com.mygdx.game.handlers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class SkinManager {
    public static Skin generateSkin(AssetManager assetManager) {
        Skin skin = new Skin();
        TextureAtlas uiAtlas = assetManager.get("uipack.atlas");

        NinePatch dialog = new NinePatch(uiAtlas.findRegion("dialoguebox"), 10, 10, 5, 5);
        skin.add("dialoguebox", dialog);

        return skin;
    }
}
