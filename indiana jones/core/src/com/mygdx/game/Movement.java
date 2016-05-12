package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Movement implements Screen {
	Animation animation_right;
	Animation animation_left;
	Animation animation_up;
	Animation animation_down;
	TextureRegion[] frames_right;
	TextureRegion[] frames_left;
	TextureRegion[] frames_up;
	TextureRegion[]	frames_down;
	float time;
	Game game;
	SpriteBatch batch;
	Sprite indianaJones;
	Texture indianaText;
	Texture walk_right;
	Texture walk_left;
	Texture walk_up;
	Texture walk_down;
	
	float indianaX;
	float indianaY;
	float speed;
	
	public Movement (Game game){
		this.game=game;
		create();
	}
	
	public void create() {
		// TODO Auto-generated method stub
		System.out.println("created!");
		batch = new SpriteBatch();
		FileHandle indianaFileHandle = Gdx.files.internal("indianajones.png");
		indianaText = new Texture (indianaFileHandle);
		indianaJones = new Sprite (indianaText);
		indianaJones.setSize(48f, 96f);
		indianaX = 30;
		indianaY = 300;
		speed = 50.0f;
		walk_right = new Texture ("right_walk.png");
		walk_left = new Texture ("left_walk.png");
		walk_up = new Texture ("up_walk.png");
		walk_down = new Texture ("down_walk.png");
		TextureRegion[][] temp_right = TextureRegion.split(walk_right, 32, 64);
		frames_right = new TextureRegion[4];
		TextureRegion[][] temp_left = TextureRegion.split(walk_left, 32, 64);
		frames_left = new TextureRegion[4];
		TextureRegion[][] temp_up = TextureRegion.split(walk_up, 32, 64);
		frames_up = new TextureRegion[4];
		TextureRegion[][]temp_down = TextureRegion.split(walk_down, 32, 64);
		frames_down = new TextureRegion[4];
		int index = 0;
		for (int rows = 0;rows<2;rows++){
			for (int col = 0;col<2;col++){
				frames_right[index]=temp_right[rows][col];
				frames_left [index]=temp_left[rows][col];
				frames_up [index]=temp_up[rows][col];
				frames_down[index]=temp_down[rows][col];
				index++;
			}
		}
		animation_up = new Animation (0.25f, frames_up);
		animation_left = new Animation (0.25f,frames_left);
		animation_right = new Animation (0.25f, frames_right);
		animation_down = new Animation (0.25f, frames_down);
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
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		time += Gdx.graphics.getDeltaTime();
		batch.begin();
		// TODO Auto-generated method stub
		if (Gdx.input.isKeyPressed(Keys.D)){
			indianaX+=Gdx.graphics.getDeltaTime()*speed;
			batch.draw(animation_right.getKeyFrame(time,true),(int)indianaX,(int)indianaY,64f,128f);
		}
		else if (Gdx.input.isKeyPressed(Keys.A)){
			indianaX-=Gdx.graphics.getDeltaTime()*speed;
			batch.draw(animation_left.getKeyFrame (time,true), (int)indianaX, (int)indianaY, 64f,128f);
		}
		else if (Gdx.input.isKeyPressed(Keys.S)){
			indianaY-=Gdx.graphics.getDeltaTime()*speed;
			batch.draw (animation_down.getKeyFrame(time, true),(int)indianaX,(int)indianaY,64f,128f);
		}
		else if (Gdx.input.isKeyPressed(Keys.W)){
			indianaY+=Gdx.graphics.getDeltaTime()*speed;
			batch.draw(animation_up.getKeyFrame(time, true), (int)indianaX, (int)indianaY, 64f, 128f);
		}
		else{
			batch.draw(indianaJones, (int)indianaX, (int)indianaY, 64f, 128f);
		}
		Gdx.gl.glClearColor(.8f, .8f, .8f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.end();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	
}
