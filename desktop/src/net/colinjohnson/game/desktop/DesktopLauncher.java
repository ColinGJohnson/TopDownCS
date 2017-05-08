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
		config.vSyncEnabled = false;
		//config.addIcon("icon.png", Files.FileType.Internal);
		config.title = "squar DOT jar";
		config.resizable = false;
		new LwjglApplication(new MainClass(), config);
	}
}
