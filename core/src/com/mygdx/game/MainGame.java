package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;

/**
 * The MainGame, which extends the Game class, is currently responsible for
 * starting the execution of the application by calling upon the MainMenu class.
 * 
 * @author Brian Tran
 * @version 2.0 19/05/2016
 *
 *          <p>
 *          <b>Instance Variables</b>
 *          <p>
 *          <b>batch</b> Acts as a container for onscreen elements
 *          <p>
 *          <b>pm</b> Instance of Pixmap that allows for the image importing of the cursor image for custom cursors.
 *
 */
public class MainGame extends Game {

	Pixmap pm;

	/**
	 * create() is an overridden method that is responsible for setting the
	 * cursor of the game to a custom texture, as well as initializing the batch
	 * variable and calling upon the mainMenu() method.
	 */
	@Override
	public void create() {
		// sets the cursor to a custom image
		pm = new Pixmap(Gdx.files.internal("cursor.png"));
		Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
		pm.dispose();

		splashScreen();
	}

	/**
	 * Sets the current screen to the MainMenu screen.
	 */
	public void splashScreen() {
		setScreen(new SplashScreen(this));
	}

	/**
	 * Calls the constructor of the Game class.
	 */
	@Override
	public void render() {
		super.render();
	}

	/**
	 * Unused overridden method.
	 */
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	/**
	 * Unused overridden method.
	 */
	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	/**
	 * Unused overridden method.
	 */
	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	/**
	 * Unused overridden method.
	 */
	@Override
	public void dispose() {
	}

}
