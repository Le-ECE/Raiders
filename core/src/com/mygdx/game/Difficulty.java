package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Difficulty implements Screen{
	
	Texture easy;
	Texture background;
	
	Sprite easySprite;
	Sprite backgroundSprite;
	
	private SpriteBatch batch;
	
	
	private Game game;
	
	public Difficulty (SpriteBatch batch, Game game){
		this.batch = batch;
		this.game = game;
	}

	@Override
	public void show() {
		//easy button create
		easy = new Texture ("easy.png");
		easySprite = new Sprite (easy);
		easySprite.setPosition((Gdx.graphics.getWidth()/2)-(easySprite.getWidth()/2), 300);
		
		//background create
		background = new Texture ("splash.png");
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(background,0,0,1200f,768f);
		easySprite.draw(batch);
		batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
