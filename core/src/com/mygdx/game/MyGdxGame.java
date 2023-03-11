package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.handlers.Content;
import com.mygdx.game.handlers.GameKeys;
import com.mygdx.game.handlers.GameStateManager;
import com.mygdx.game.handlers.MyInputProcessor;

import java.awt.*;
import java.util.Vector;

public class MyGdxGame implements ApplicationListener {
	public static final int V_WIDTH = 1216;
	public static final int V_HEIGHT = 672;
	public static final int SCALE = 2;
	SpriteBatch sb;
	private OrthographicCamera cam;
	private OrthographicCamera hudcam;
	private GameStateManager gsm;
	public static final float STEP = 1 / 60f;
	private float accum;
	public static Content res;

	public void create () {
		Gdx.input.setInputProcessor(new MyInputProcessor());

		res = new Content();
		res.loadTexture("gnom1rowP1.png", "gnomik"); // !!!

		sb = new SpriteBatch();
		cam = new OrthographicCamera();
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
	public SpriteBatch getSb() {
		return sb;
	}
	public OrthographicCamera getCam() {
		return cam;
	}
	public OrthographicCamera getHudcam() {
		return hudcam;
	}
}