package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.handlers.*;
import com.mygdx.game.inputs.GameKeys;

public class MyGdxGame implements ApplicationListener {
	public static final int V_WIDTH = 1216;
	public static final int V_HEIGHT = 672;
	public static final int SCALE = 2;
	SpriteBatch sb;
	private AssetManager assetManager;
	private BoundedCamera cam;
	private OrthographicCamera hudcam;
	private GameStateManager gsm;
	public static final float STEP = 1 / 60f;
	private float accum;
	public static Content res;
	private Skin skin;

	public void create () {
		//Gdx.input.setInputProcessor(new MyInputProcessor());

		res = new Content();
		res.loadTexture("gnomikS.png", "gnomik");
		res.loadTexture("allBtn.png", "btn");

		assetManager = new AssetManager();
		assetManager.load("testAtlas.atlas", TextureAtlas.class);
		assetManager.load("uipack.atlas", TextureAtlas.class);
		assetManager.load("mcRus.fnt", BitmapFont.class);
		assetManager.finishLoading();
		skin = SkinManager.generateSkin(assetManager);

		sb = new SpriteBatch();
		cam = new BoundedCamera();
		cam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		hudcam = new OrthographicCamera();
		hudcam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		gsm = new GameStateManager(this);
	}

	public void render () {
		//update();

		accum += Gdx.graphics.getDeltaTime();
		while(accum >= STEP){
			accum -= STEP;
			gsm.update(STEP);
			gsm.render();
			GameKeys.update();
		}
	}

	public void update(){

	}
	public void dispose () {
		sb.dispose();
	}
	public void resize(int w, int h){}
	public void pause(){}
	public void resume(){}
	public Skin getSkin() {
		return skin;
	}
	public SpriteBatch getSb() {
		return sb;
	}
	public BoundedCamera getCam() {
		return cam;
	}
	public OrthographicCamera getHudcam() {
		return hudcam;
	}
}