package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.handlers.GameStateManager;

import java.util.Vector;

public class MyGdxGame implements ApplicationListener {
	public static final int V_WIDTH = 1215;
	public static final int V_HEIGHT = 675;
	public static final int SCALE = 2;
	SpriteBatch sb;
	private OrthographicCamera cam;
	private OrthographicCamera hudcam;
	private GameStateManager gsm;
	public static final float STEP = 1 / 60f;
	private float accum;

	public void create () {
		sb = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		hudcam = new OrthographicCamera();
		hudcam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		gsm = new GameStateManager(this);
	}

	public void render () {
		//update();

		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		accum += Gdx.graphics.getDeltaTime();
		while(accum >= STEP){
			accum -= STEP;
			gsm.update(STEP);
			gsm.render();
		}

		/*sb.begin();
		bg.render(sb);
		gg.render(sb);
		sb.end();*/
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