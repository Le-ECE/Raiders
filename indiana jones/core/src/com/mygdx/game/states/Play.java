package com.mygdx.game.states;

import managers.GameStateManager;

/**
 * Once implemented, the Play class (which extends GameState) will represent
 * a 'play state' of the game.
 * 
 * @author Brian Tran
 * @version 1.0 13/05/2016
 */
public class Play extends GameState{
	
	/**
	 * The Play constructor calls upon the GameState constructor with a
	 * GameStateManager type.
	 * @param gsm Manages game states
	 */
	public Play (GameStateManager gsm){
		super(gsm);
	}
	
	/**
	 * Unused overridden method.
	 */
	@Override
	public void handleInput(){
		
	}
	
	/**
	 * Unused overridden method.
	 */
	@Override
	public void update(float dt){
		
	}
	
	/**
	 * Unused overridden method.
	 */
	@Override
	public void render(){
	}
	
	/**
	 * Unused overridden method.
	 */
	@Override
	public void dispose(){
		
	}
	
}
