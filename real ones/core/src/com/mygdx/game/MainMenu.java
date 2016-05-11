package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MainMenu implements Screen {
	 Game game;
	 Stage stage;
	 SpriteBatch batch;
	 Texture bg = new Texture ("background.jpg");
	 
	 public MainMenu(Game game){
		 this.game = game;
		 create();
	 }
	 
	 public void create(){
		 
		 stage = new Stage();
		 Texture pB = new Texture ("playbutton.png");
		 Texture qB = new Texture ("quit.png");
		 Skin skin = new Skin();
		 Pixmap pix = new Pixmap (100,100,Format.RGBA8888);
		 
		 Texture title = new Texture ("title.png");
		 Image ttl = new Image (title);
		 ttl.setWidth(900);
		 ttl.setHeight(400);
		 ttl.setPosition(175, 440);
		 stage.addActor (ttl);
		 
		 Image bG = new Image (bg);
		 bG.setWidth (640);
		 bG.setHeight(500);
		 stage.addActor(bG);
		 
		 Gdx.input.setInputProcessor(stage);
		 
		 pix.setColor(Color.RED);
		 pix.fill();
		 skin.add("red",pB );
		 skin.add("overlay", qB);
		 skin.add("pbover", new Texture ("playbutton.png"));
		 skin.add("qover", new Texture ("quit.png"));

		 TextButtonStyle tb = new TextButtonStyle();
		 TextButtonStyle tb1 = new TextButtonStyle();
		 
		 tb.up = skin.newDrawable("red", Color.LIGHT_GRAY);
		 tb.down = skin.newDrawable("red", Color.DARK_GRAY);
		 tb.over = skin.newDrawable ("pbover");
		 tb.font = new BitmapFont();
		 
		 tb1.up = skin.newDrawable("overlay",Color.LIGHT_GRAY);
		 tb1.down = skin.newDrawable ("overlay", Color.DARK_GRAY);
		 tb1.over = skin.newDrawable("qover");
		 tb1.font = new BitmapFont();
		 
		 
		 final TextButton playButton = new TextButton ("", tb);
		 final TextButton quitButton = new TextButton ("",tb1);
		 
		 playButton.setPosition(513, 400);
		 playButton.setWidth(175f);
		 playButton.setHeight(105f);
		 
		 quitButton.setPosition (513,250);
		 quitButton.setWidth(175f);
		 quitButton.setHeight(105f);
		 
		 
		 playButton.addCaptureListener(new ChangeListener(){
			 public void changed (ChangeEvent event, Actor actor){
				 playButton.setText("");
			 }
		 });
		 
		 quitButton.addCaptureListener (new ChangeListener(){
			 public void changed (ChangeEvent event, Actor actor){
				 stage.dispose();
				 System.exit(0);
			 }
		 });
		 stage.addActor(playButton);
		 stage.addActor(quitButton);
	 }
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(.8f, .8f, .8f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
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