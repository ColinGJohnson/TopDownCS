package net.colinjohnson.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import net.colinjohnson.game.MainClass;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 720;
		config.fullscreen = false;
		config.vSyncEnabled = true;
		config.title = "\"Squar DOT Jar\"";
		config.resizable = false;
		new LwjglApplication(new MainClass(), config);
	}
}
