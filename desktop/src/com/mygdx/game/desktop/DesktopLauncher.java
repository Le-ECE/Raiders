package com.mygdx.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MainGame;

/**
 * The DesktopLauncher class contains all of the information needed to run this
 * application on a desktop computer. Using the config class, information is
 * stored, such as resolution and other video options. The MainGame class is
 * called, which starts the program.
 * 
 * @author David Le
 * @version 4.0 09.06.2016
 */
public class DesktopLauncher {
	/**
	 * The main method of the driver class sets the game window's properties
	 * and creates a new instance of MainGame.
	 * @param arg Parameter for main method to be run by compiler. 
	 */
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1200;
		config.height = 768;
		config.title="Raiders of the Lost Marks";
		config.resizable = false;
		config.addIcon("assets/gameicon.png", Files.FileType.Internal);
		config.vSyncEnabled = false;
		config.backgroundFPS = 120;
		config.foregroundFPS = 120;
		new LwjglApplication(new MainGame(), config);
	}
}
