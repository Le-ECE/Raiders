package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MainGame;

import managers.GameStateManager;

/**
 * An abstract class that once implemented, represents a GameState that is
 * sorted using the GameStateManager class.
 * 
 * @author Brian Tran
 * @version 1.0 13/05/2016
 *
 *          <p>
 *          <b>gsm</b> Instance of GameStateManager used to manage game states
 *          <p>
 *          <b>game</b> Instance of MainGame used to manage current screen of
 *          game
 *          <p>
 *          <b>batch</b> Instance of SpriteBatch used as a container for all
 *          screen elements
 */
public abstract class GameState {

	GameStateManager gsm;
	MainGame game;

	protected SpriteBatch batch;

	/**
	 * The GameState constructor sets the GameStateManager, game, and batch to
	 * ones that are passed in.
	 * 
	 * @param gsm
	 *            Manages game states
	 */
	protected GameState(GameStateManager gsm) {
		this.gsm = gsm;
		game = gsm.getGame();
		// batch = game.getSpriteBatch();
	}

	/**
	 * Unused abstract method.
	 */
	public abstract void handleInput();

	/**
	 * Unused abstract method.
	 * 
	 * @param dt
	 *            Delta time of frames
	 */
	public abstract void update(float dt);

	/**
	 * Unused abstract method.
	 */
	public abstract void render();

	/**
	 * Unused abstract method.
	 */
	public abstract void dispose();

}
