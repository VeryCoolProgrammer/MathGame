package com.mygdx.game.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class SkinManager {
    public static Skin generateSkin(AssetManager assetManager) {
        Skin skin = new Skin();
        TextureAtlas uiAtlas = assetManager.get("testAtlas.atlas"); //uipack
        TextureAtlas uiAtlas2 = assetManager.get("testAtlas2.atlas");
        TextureAtlas uiAtlas3 = assetManager.get("uipack.atlas");

        NinePatch dialog = new NinePatch(uiAtlas.findRegion("background"), 10, 10, 5, 5);//dialoguebox
        skin.add("GUI_img", dialog);
        NinePatch dialog2 = new NinePatch(uiAtlas2.findRegion("back"), 10, 10, 5, 5);
        skin.add("GUI_2x", dialog2);
        NinePatch option = new NinePatch(uiAtlas3.findRegion("optionbox"),6, 6, 6, 6);
        skin.add("optionbox", option);
        skin.add("arrow", uiAtlas3.findRegion("arrow"), TextureRegion.class);

        BitmapFont font = assetManager.get("mcRus.fnt", BitmapFont.class);
        skin.add("font", font);
        //skin.add("defJson", Gdx.files.internal("uiskin.json"));

        return skin;
    }
}
