package com.game.megaman;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.game.megaman.Megaman;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Megaman - Java");
		config.setWindowIcon("megaman/megaman_life.png");
    config.setWindowedMode(800, 480);
//    config.setWindowIcon("");
		new Lwjgl3Application(new Megaman(), config);
	}
}
