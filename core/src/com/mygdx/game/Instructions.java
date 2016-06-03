package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Instructions implements Screen {
	
	private SpriteBatch batch;
	
	private MainGame game;

	private Texture screen1Text;
	private Texture screen2Text;
	private Texture screen3Text;
	private Texture screen4Text;
	private Texture screen5Text;
	private Texture nextText;
	private Texture prevText;
	private Texture nextDarkText;
	private Texture prevDarkText;
	private Texture backgroundText;
	private Texture backText;
	private Texture backDarkText;
	
	private Sprite screen1Sprite;
	private Sprite screen2Sprite;
	private Sprite screen3Sprite;
	private Sprite screen4Sprite;
	private Sprite screen5Sprite;
	private Sprite nextSprite;
	private Sprite prevSprite;
	private Sprite nextDarkSprite;
	private Sprite prevDarkSprite;
	private Sprite backgroundSprite;
	private Sprite backSprite;
	private Sprite backDarkSprite;
	
	private Rectangle nextRect;
	private Rectangle prevRect;
	private Rectangle backRect;
	
	private int pageNum;
	
	private boolean leftKey;
	private boolean rightKey;
	private boolean escKey;
	
	private Sprite [] screenList;
	
	public Instructions (SpriteBatch batch, MainGame game){
		this.batch = batch;
		this.game = game;
	}
	
	@Override
	public void show() {
		//initialize array
		screenList = new Sprite [5];
		
		//create background texture
		backgroundText = new Texture ("splash.png");
		backgroundSprite = new Sprite (backgroundText);
		backgroundSprite.setSize(1200f, 768f);
		
		//set page number
		pageNum = 0;
		
		//create back button
		backText = new Texture ("backbutton.png");
		backSprite = new Sprite (backText);
		backSprite.setSize(290f, 110f);
		backSprite.setPosition(Gdx.graphics.getWidth()/2-backSprite.getWidth()/2, 90f);
		
		//create back dark button
		backDarkText = new Texture ("back_dark.png");
		backDarkSprite = new Sprite (backDarkText);
		backDarkSprite.setSize(290f, 110f);
		backDarkSprite.setPosition(Gdx.graphics.getWidth()/2-backDarkSprite.getWidth()/2, 90f);
		
		//create screen 1
		screen1Text = new Texture ("screen1.png");
		screen1Sprite = new Sprite (screen1Text);
		screenList [0] = screen1Sprite;
		
		//create screen 2
		screen2Text = new Texture ("screen2.png");
		screen2Sprite = new Sprite (screen2Text);
		screenList [1] = screen2Sprite;
		
		//create screen 3
		screen3Text = new Texture ("screen3.png");
		screen3Sprite = new Sprite (screen3Text);
		screenList [2] = screen3Sprite;
		
		//create screen 4
		screen4Text = new Texture ("screen4.png");
		screen4Sprite = new Sprite (screen4Text);
		screenList [3] = screen4Sprite;
		
		//create screen 5
		screen5Text = new Texture ("screen5.png");
		screen5Sprite = new Sprite (screen5Text);
		screenList [4] = screen5Sprite;
		
		//create next button
		nextText = new Texture ("next_arrow.png");
		nextSprite = new Sprite (nextText);
		nextSprite.setPosition(800f, 80f);
		nextSprite.setSize(252f, 140f);
		
		//create dark next button
		nextDarkText = new Texture ("next_arrow_dark.png");
		nextDarkSprite = new Sprite (nextDarkText);
		nextDarkSprite.setPosition(800f, 80f);
		nextDarkSprite.setSize(252f, 140f);
		
		//create previous button
		prevText = new Texture ("prev_arrow.png");
		prevSprite = new Sprite (prevText);
		prevSprite.setPosition(148f, 80f);
		prevSprite.setSize(252f, 140f);
		
		//create dark previous button
		prevDarkText = new Texture ("prev_arrow_dark.png");
		prevDarkSprite = new Sprite (prevDarkText);
		prevDarkSprite.setPosition(148f, 80f);
		prevDarkSprite.setSize(252f, 140f);
		
		//create next button rectangle
		nextRect = nextDarkSprite.getBoundingRectangle();
		
		//create previous button rectangle
		prevRect = prevDarkSprite.getBoundingRectangle();
		
		//create back button rectangle
		backRect = backDarkSprite.getBoundingRectangle();
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		backgroundSprite.draw(batch);
		
		screenList [pageNum].draw(batch);
		
		nextDarkSprite.draw(batch);
		
		prevDarkSprite.draw(batch);
		
		backDarkSprite.draw(batch);
		
		if (Gdx.input.isKeyJustPressed(Keys.LEFT)){
			leftKey = true;
		}
		if (Gdx.input.isKeyJustPressed(Keys.RIGHT)){
			rightKey = true;
		}
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
			escKey = true;
		}
		
		if (nextRect.contains(Gdx.input.getX(),Gdx.input.getY()-430)||rightKey){
			nextSprite.draw(batch);
			if (Gdx.input.justTouched()||rightKey){
				rightKey = false;
				if (pageNum==4){
					pageNum = 0;
				}
				else{
					pageNum++;
				}
			}
		}
		if (prevRect.contains(Gdx.input.getX(),Gdx.input.getY()-430)||leftKey){
			prevSprite.draw(batch);
			if (Gdx.input.justTouched()||leftKey){
				leftKey = false;
				if (pageNum==0){
					pageNum = 4;
				}
				else{
					pageNum--;
				}
			}
		}
		if (backRect.contains(Gdx.input.getX(),Gdx.input.getY()-440)||escKey){
			backSprite.draw(batch);
			if (Gdx.input.justTouched()||escKey){
				escKey = false;
				game.setScreen(new MainMenu (batch,game));
			}
		}
		
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
		dispose();		
	}

	@Override
	public void dispose() {
		screen1Text.dispose();
		screen2Text.dispose();
		screen3Text.dispose();
		screen4Text.dispose();
		screen5Text.dispose();
		nextText.dispose();
		prevText.dispose();
		nextDarkText.dispose();
		prevDarkText.dispose();
		backgroundText.dispose();
		backText.dispose();
		backDarkText.dispose();
	}

}
