package com.mygdx.game;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setWindowedMode(1216, 672);
		config.setTitle("Game");

		Graphics.DisplayMode displayMode = Lwjgl3ApplicationConfiguration.getDisplayMode();
		//config.setFullscreenMode(displayMode);
		//config.setWindowIcon("kotik.gif");
		new Lwjgl3Application(new MyGdxGame(), config);
	}
}
//config.setWindowedMode(displayMode.width, displayMode.height);
//config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());